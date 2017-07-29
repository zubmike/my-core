package ru.zubmike.core.utils;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;

public class DateTimeUtils {

	public static LocalDateTime getDateOrNull(String date) {
		return date != null ? DateTimeFormat.DD_MM_YYYY_HH_MM.parse(date) : null;
	}

	public static String toString(LocalDateTime date) {
		return date != null ? DateTimeFormat.DD_MM_YYYY_HH_MM.toString(date) : null;
	}

	public static LocalDateTime getStartDateOfQuarter(int year, int quarter) {
		int firstMonth = 3 * quarter - 2;
		return LocalDateTime.of(year, firstMonth, 1, 0, 0, 0, 0);
	}

	public static LocalDateTime getFinishDateOfQuarter(int year, int quarter) {
		int lastMonth = quarter * 3;
		return LocalDateTime.of(year, lastMonth, 1, 23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());
	}

	public static int getQuarter(LocalDateTime date) {
		return date.getMonth().get(IsoFields.QUARTER_OF_YEAR);
	}

}
