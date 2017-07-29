package ru.zubmike.core.models;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import ru.zubmike.core.types.DictItem;

import java.io.Serializable;
import java.util.*;

public class BasicDictItemModel<I extends Serializable, T extends DictItem<I>> implements DictItemModel<I, T> {

	private final Map<I, T> itemMap;

	public BasicDictItemModel() {
		this(Maps.newConcurrentMap());
	}

	public BasicDictItemModel(Map<I, T> itemMap) {
		this.itemMap = itemMap;
	}

	@Override
	public void fill(Collection<T> items) {
		items.forEach(this::add);
	}

	@Override
	public void add(T item) {
		itemMap.put(item.getId(), item);
	}

	@Override
	public void update(T item) {
		itemMap.replace(item.getId(), item);
	}

	@Override
	public void remove(I id) {
		itemMap.remove(id);
	}

	@Override
	public Optional<T> get(I id) {
		return Optional.ofNullable(id != null ? itemMap.get(id) : null);
	}

	@Override
	public String getName(I id) {
		return getName(id, null);
	}

	@Override
	public String getName(I id, String defaultValue) {
		return get(id).map(DictItem::getName).orElse(defaultValue);
	}

	@Override
	public Set<I> getIds() {
		return Sets.newLinkedHashSet(itemMap.keySet());
	}

	@Override
	public List<T> getItems() {
		return Lists.newArrayList(itemMap.values());
	}
}
