package ru.zubmike.core.dao.db;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import ru.zubmike.core.dao.EntityItemDao;
import ru.zubmike.core.utils.DataSourceException;
import ru.zubmike.core.utils.DuplicateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;

public class BasicEntityItemDao<I extends Serializable, T> extends BasicItemDao<I, T> implements EntityItemDao<I, T> {

	public BasicEntityItemDao(SessionFactory sessionFactory, Class<T> clazz) {
		super(sessionFactory, clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public I add(T item) {
		return doReturningTransaction(session -> (I) session.save(item),
				e -> createException(e, "can't add item", item.toString()));
	}

	@Override
	public void addAll(Collection<T> items) {
		doVoidTransaction(session -> {
			for (T item : items) {
				session.save(item);
			}
		}, e -> createException(e, "can't add items", null));
	}

	@Override
	public void update(T item) {
		doVoidTransaction(session -> session.saveOrUpdate(item),
				e -> createException(e, "can't update item", item.toString()));
	}

	@Override
	public void updateAll(Collection<T> items) {
		doVoidTransaction(session -> {
			for (T item : items) {
				session.saveOrUpdate(item);
			}
		}, e -> createException(e, "can't update items", ""));
	}

	@Override
	public void remove(T item) {
		doVoidTransaction(session -> session.delete(item),
				e -> createException(e, "can't remove item", item.toString()));
	}

	@Override
	public void remove(I id) {
		doVoidTransaction(session ->{
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<T> query = builder.createCriteriaDelete(clazz);
			Root<T> root = query.from(clazz);
			query.where(builder.equal(root.get("id"), id));
			session.createQuery(query).executeUpdate();
		}, e -> createException(e, "can't remove item by id", id.toString()));
	}

	@Override
	public void removeAll() {
		doVoidTransaction(session -> session.createQuery("delete from " + clazz.getSimpleName()),
				e -> createException(e, "can't remove all items", ""));
	}

	protected RuntimeException createException(Exception e, String message, String itemDetail) {
		if (e.getCause() instanceof ConstraintViolationException) {
			return isDuplicateException((ConstraintViolationException) e.getCause())
					? new DuplicateException("duplicate item " + itemDetail, e)
					: new IllegalArgumentException();
		} else {
			return new DataSourceException(message + " " + itemDetail, e);
		}
	}

	protected boolean isDuplicateException(ConstraintViolationException e) {
		String sqlState = e.getSQLState();
		return sqlState.equals("23000") && e.getErrorCode() == 1 // Oracle
				|| sqlState.equals("23505"); // PostgreSQL
	}
}
