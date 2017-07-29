package ru.zubmike.core.models;

import ru.zubmike.core.types.TreeEntityItem;

import java.io.Serializable;
import java.util.Set;

public interface TreeItemModel<I extends Serializable, T extends TreeEntityItem<I>> extends ItemModel<I, T> {

	Set<I> getRootIds();

	Set<I> getChildrenIds(I parentId);

	Set<I> getAllChildrenIds(I parentId);
}
