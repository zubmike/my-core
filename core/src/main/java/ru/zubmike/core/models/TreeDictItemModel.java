package ru.zubmike.core.models;

import ru.zubmike.core.types.TreeDictItem;

import java.io.Serializable;

public interface TreeDictItemModel<I extends Serializable, T extends TreeDictItem<I>>
		extends DictItemModel<I, T>, TreeItemModel<I, T> {

}
