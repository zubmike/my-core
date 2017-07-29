package ru.zubmike.core.types;

import java.io.Serializable;

public interface TreeEntityItem<I extends Serializable> extends EntityItem<I> {

	I getParentId();

	void setParentId(I id);
}
