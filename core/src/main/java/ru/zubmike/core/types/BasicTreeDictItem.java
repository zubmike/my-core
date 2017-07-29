package ru.zubmike.core.types;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class BasicTreeDictItem<I extends Serializable> extends BasicDictItem<I> implements TreeDictItem<I> {

	private static final long serialVersionUID = 1L;

	private I parentId;

	public BasicTreeDictItem() {

	}

	public BasicTreeDictItem(I id, String name) {
		this(id, name, null);
	}

	public BasicTreeDictItem(I id, String name, I parentId) {
		super(id, name);
		this.parentId = parentId;
	}

	@Override
	public I getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(I parentId) {
		this.parentId = parentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BasicTreeDictItem)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		BasicTreeDictItem<?> that = (BasicTreeDictItem<?>) o;
		return Objects.equals(parentId, that.parentId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), parentId);
	}

	@Override
	public String toString() {
		return super.toString() + " " + parentId;
	}
}
