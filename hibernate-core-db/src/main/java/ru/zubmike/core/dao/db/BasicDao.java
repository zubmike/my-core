package ru.zubmike.core.dao.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class BasicDao {

	protected final SessionFactory sessionFactory;

	public BasicDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected <R> R doReturning(Function<Session, R> action, Function<Exception, RuntimeException> handle) {
		Session session = null;
		try {
			session = openSession();
			return action.apply(session);
		} catch (Exception e) {
			throw handle.apply(e);
		} finally {
			closeSession(session);
		}
	}

	protected <R> R doReturningTransaction(Function<Session, R> action, Function<Exception, RuntimeException> handle) {
		Session session = null;
		try {
			session = openSession();
			beginTransaction(session);
			R result = action.apply(session);
			commitTransaction(session);
			return result;
		} catch (Exception e) {
			rollbackTransaction(session);
			throw handle.apply(e);
		} finally {
			closeSession(session);
		}
	}

	protected void doVoid(Consumer<Session> action, Function<Exception, RuntimeException> handle) {
		Session session = null;
		try {
			session = openSession();
			action.accept(session);
		} catch (Exception e) {
			throw handle.apply(e);
		} finally {
			closeSession(session);
		}
	}

	protected void doVoidTransaction(Consumer<Session> action, Function<Exception, RuntimeException> handle) {
		Session session = null;
		try {
			session = openSession();
			beginTransaction(session);
			action.accept(session);
			commitTransaction(session);
		} catch (Exception e) {
			rollbackTransaction(session);
			throw handle.apply(e);
		} finally {
			closeSession(session);
		}
	}

	protected Session openSession() {
		return sessionFactory.openSession();
	}

	protected static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	protected static void beginTransaction(Session session) {
		if (session != null) {
			session.getTransaction().begin();
		}
	}

	protected static void commitTransaction(Session session) {
		if (session != null) {
			session.getTransaction().commit();
		}
	}

	protected static void rollbackTransaction(Session session) {
		if (session != null) {
			session.getTransaction().rollback();
		}
	}

}
