package ru.zubmike.core.models;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import ru.zubmike.core.types.TreeDictItem;

import java.io.Serializable;
import java.util.*;

public class BasicTreeDictModel<I extends Serializable, T extends TreeDictItem<I>>
		extends BasicDictItemModel<I, T> implements TreeDictItemModel<I, T> {

	private final Multimap<I, I> parentMultimap;
	private final Set<I> rootIds;

	public BasicTreeDictModel() {
		this(Maps.newConcurrentMap(), LinkedHashMultimap.create(), Sets.newConcurrentHashSet());
	}

	public BasicTreeDictModel(Map<I, T> itemMap, Multimap<I, I> parentMultimap, Set<I> rootIds) {
		super(itemMap);
		this.parentMultimap = parentMultimap;
		this.rootIds = rootIds;
	}

	@Override
	public void fill(Collection<T> items) {
		super.fill(items);
	}

	@Override
	public void add(T item) {
		super.add(item);
		addRelation(item);
	}

	@Override
	public void remove(I id) {
		super.remove(id);
		removeAllRelation(id);
	}

	@Override
	public void update(T item) {
		I id = item.getId();
		Optional<T> prevItemOpt = get(id);
		if (prevItemOpt.isPresent()) {
			super.update(item);
			T prevItem = prevItemOpt.get();
			updateRelation(prevItem, item);
		}
	}

	protected void addRelation(T item) {
		I id = item.getId();
		I parentId = item.getParentId();
		if (parentId == null || id.equals(parentId)) {
			rootIds.add(id);
		} else {
			parentMultimap.put(parentId, id);
		}
	}

	protected void updateRelation(T prevItem, T item) {
		if (!Objects.equals(prevItem.getParentId(), item.getParentId())) {
			removeRelation(prevItem);
			addRelation(item);
		}
	}

	protected void removeRelation(T item) {
		I id = item.getId();
		I parentId = item.getParentId();
		if (parentId == null || id.equals(parentId)) {
			rootIds.remove(id);
		} else {
			parentMultimap.get(parentId).remove(id);
		}
	}

	protected void removeAllRelation(I id) {
		rootIds.remove(id);
		if (parentMultimap.containsValue(id)) {
			parentMultimap.values().remove(id);
		}
		parentMultimap.removeAll(id);
	}

	@Override
	public Set<I> getRootIds() {
		return Sets.newLinkedHashSet(rootIds);
	}

	@Override
	public Set<I> getChildrenIds(I parentId) {
		return Sets.newLinkedHashSet(parentMultimap.get(parentId));
	}

	@Override
	public Set<I> getAllChildrenIds(I parentId) {
		Set<I> ids = Sets.newLinkedHashSet();
		for (I id : parentMultimap.get(parentId)) {
			ids.add(id);
			ids.addAll(getAllChildrenIds(parentId));
		}
		return ids;
	}
}
