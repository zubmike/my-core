package ru.zubmike.core.models;

import ru.zubmike.core.types.EntityItem;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ItemModel<I extends Serializable, T extends EntityItem<I>> {

	void fill(Collection<T> items);

	void add(T item);

	void update(T item);

	void remove(I id);

	Optional<T> get(I id);

	Set<I> getIds();

	List<T> getItems();

}
