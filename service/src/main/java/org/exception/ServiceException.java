package org.exception;

public class ServiceException extends Exception {

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable arg) {
		super(message, arg);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable arg) {
		super(arg);
	}

}