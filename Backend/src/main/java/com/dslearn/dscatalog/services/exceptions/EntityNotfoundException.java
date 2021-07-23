package com.dslearn.dscatalog.services.exceptions;

public class EntityNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotfoundException(String msg) {
		super(msg);
	}
}
