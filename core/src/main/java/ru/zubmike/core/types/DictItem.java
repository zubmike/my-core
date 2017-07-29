package ru.zubmike.core.types;

import java.io.Serializable;

public interface DictItem<I extends Serializable> extends EntityItem<I> {

	String getName();

	void setName(String name);

}
