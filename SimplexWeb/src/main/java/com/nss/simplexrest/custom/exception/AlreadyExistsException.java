package com.nss.simplexrest.custom.exception;

public class AlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String ENTITY, String MAIN_MSG, String ADDITIONAL_MSG) {
		super(ENTITY + " with " + MAIN_MSG + " already exists");
	}
}
