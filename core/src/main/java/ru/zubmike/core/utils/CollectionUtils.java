package ru.zubmike.core.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

	public static boolean isEmpty(Collection<?> items) {
		return items != null && !items.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> items) {
		return !isEmpty(items);
	}

	public static <T> List<T> getPageItems(List<T> items, Comparator<? super T> comparator, Integer page, Integer limit) {
		items.sort(comparator);
		return getPageItems(items, page, limit);
	}

	public static <T> List<T> getPageItems(List<T> items, Integer page, Integer limit) {
		return page != null && page > 0 && limit != null && limit > 0
				? getPageItems(items, page.intValue(), limit.intValue())
				: items;
	}

	public static <T> List<T> getPageItems(List<T> items, int page, int limit) {
		int size = items.size();
		int fromIndex = (page - 1) * limit;
		int toIndex = page * limit;
		if (fromIndex > size) {
			return Collections.emptyList();
		}
		if (toIndex > size) {
			toIndex = size;
		}
		return items.subList(fromIndex, toIndex);
	}
}
