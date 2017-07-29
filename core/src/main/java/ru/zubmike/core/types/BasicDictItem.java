package ru.zubmike.core.types;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class BasicDictItem<I extends Serializable> implements DictItem<I> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private I id;

	@Column(name = "name")
	private String name;

	public BasicDictItem() {
	}

	public BasicDictItem(I id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public I getId() {
		return id;
	}

	@Override
	public void setId(I id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BasicDictItem)) {
			return false;
		}
		BasicDictItem<?> that = (BasicDictItem<?>) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return id + " " + name;
	}
}
