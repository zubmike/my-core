package ru.zubmike.core.models;

import ru.zubmike.core.types.DictItem;

import java.io.Serializable;

public interface DictItemModel<I extends Serializable, T extends DictItem<I>> extends ItemModel<I, T> {

	String getName(I id);

	String getName(I id, String defaultValue);

}
