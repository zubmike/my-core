package ru.zubmike.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum  DateTimeFormat {

	DD_MM_YYYY("dd.MM.yyyy"),
	DD_MM_YYYY_HH_MM("dd.MM.yyyy HH:mm"),
	DD_MM_YYYY_HH_MM_SS("dd.MM.yyyy HH:mm:ss"),
	ISO();

	private final DateTimeFormatter formatter;

	DateTimeFormat() {
		this.formatter = java.time.format.DateTimeFormatter.ISO_DATE_TIME;
	}

	DateTimeFormat(String pattern) {
		this.formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
	}

	public String toString(LocalDateTime date) {
		return date != null ? date.format(formatter) : null;
	}

	public LocalDateTime parse(String date) {
		return LocalDateTime.parse(date, formatter);
	}

}
