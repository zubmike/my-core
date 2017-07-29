package ru.zubmike.core.dao;

import java.io.Serializable;
import java.util.Collection;

public interface EntityItemDao<I extends Serializable, T> extends ItemDao<I, T> {

	I add(T item);

	void addAll(Collection<T> items);

	void update(T item);

	void updateAll(Collection<T> items);

	void remove(I id);

	void remove(T item);

	void removeAll();
}
