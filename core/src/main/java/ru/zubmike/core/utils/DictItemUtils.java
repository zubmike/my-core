package ru.zubmike.core.utils;

import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.primitives.Longs;
import ru.zubmike.core.types.BasicDictItem;
import ru.zubmike.core.types.DictItem;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DictItemUtils {

	public static final Comparator<DictItem<? extends Number>> COMPARATOR_BY_NUMBER_ID = (o1, o2) ->
			Longs.compare(o1.getId().longValue(), o2.getId().longValue());

	public static final Comparator<DictItem<? extends Comparable>> COMPARATOR_BY_NAME = (o1, o2) ->
			ComparisonChain.start()
					.compare(Strings.nullToEmpty(o1.getName()), Strings.nullToEmpty(o2.getName()))
					.result();

	public static <T extends DictItem<Integer>> List<DictItem<Integer>> createIntItems(Collection<T> items) {
		return items.stream()
				.map(item -> new BasicDictItem<>(item.getId(), item.getName()))
				.sorted(COMPARATOR_BY_NUMBER_ID)
				.collect(Collectors.toList());
	}

}
