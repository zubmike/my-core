package ru.zubmike.core.types;

import java.io.Serializable;

public interface DictItemNode<I extends Serializable, T extends DictItemNode<I, T>> extends DictItem<I>, Node<T> {

}
