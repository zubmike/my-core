package ru.zubmike.core.types;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BasicDictItemNode<I extends Serializable, T extends BasicDictItemNode<I, T>>
		extends BasicDictItem<I> implements DictItemNode<I, T> {

	private static final long serialVersionUID = 1L;

	private List<T> children;

	public BasicDictItemNode() {

	}

	public BasicDictItemNode(I id, String name) {
		this(id, name, Collections.emptyList());
	}

	public BasicDictItemNode(I id, String name, List<T> children) {
		super(id, name);
		this.children = children;
	}

	@Override
	public List<T> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<T> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BasicDictItemNode)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		BasicDictItemNode<?, ?> that = (BasicDictItemNode<?, ?>) o;
		return Objects.equals(children, that.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), children);
	}

}
