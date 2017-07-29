package ru.zubmike.core.types;

import java.io.Serializable;
import java.util.List;

public interface Node<T extends  Node> extends Serializable {

	List<T> getChildren();

	void setChildren(List<T> children);
}
