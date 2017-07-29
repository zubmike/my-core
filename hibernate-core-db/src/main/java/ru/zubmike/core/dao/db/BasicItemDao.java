package ru.zubmike.core.dao.db;

import org.hibernate.SessionFactory;
import ru.zubmike.core.dao.ItemDao;
import ru.zubmike.core.utils.CollectionUtils;
import ru.zubmike.core.utils.DataSourceException;
import ru.zubmike.core.utils.QueryUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BasicItemDao<I extends Serializable, T> extends BasicDao implements ItemDao<I, T> {

	protected final Class<T> clazz;

	public BasicItemDao(SessionFactory sessionFactory, Class<T> clazz) {
		super(sessionFactory);
		this.clazz = clazz;
	}

	@Override
	public Optional<T> get(I id) {
		return doReturning(session ->
						Optional.ofNullable(session.get(clazz, id)),
				e -> new DataSourceException("can't get item by id " + id));
	}

	@Override
	public List<T> getAll(Collection ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return doReturning(session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(clazz);
			Root<T> root = getRootAndSelect(query);
			query.where(QueryUtils.createInPredicate(builder, root.get("id"), ids));
			return session.createQuery(query).list();
		}, e -> new DataSourceException("can't get items by ids", e));
	}

	@Override
	public List<T> getAll() {
		return doReturning(session ->
						session.createQuery("from " + clazz.getSimpleName()).list(),
				e -> new DataSourceException("can't get items", e));
	}

	protected Root<T> getRootAndSelect(CriteriaQuery<T> query) {
		Root<T> root = query.from(clazz);
		query.select(root);
		return root;
	}

}
