package ru.zubmike.core.utils;

public class DuplicateException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public DuplicateException() {
	}

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateException(Throwable cause) {
		super(cause);
	}
}
