package ru.zubmike.core.utils;

public class DataSourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataSourceException(String message) {
		super(message);
	}

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}
}
