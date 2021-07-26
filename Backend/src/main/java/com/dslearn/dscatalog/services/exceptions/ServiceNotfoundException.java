package com.dslearn.dscatalog.services.exceptions;

public class ServiceNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceNotfoundException(String msg) {
		super(msg);
	}
}
