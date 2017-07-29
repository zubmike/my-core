package ru.zubmike.core.types;

import java.io.Serializable;

public interface EntityItem<I extends Serializable> extends Serializable {

	I getId();

	void setId(I id);

}
