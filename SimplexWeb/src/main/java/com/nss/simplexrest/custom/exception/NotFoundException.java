package com.nss.simplexrest.custom.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String ENTITY, String MAIN_MSG, String ADDITIONAL_MSG) {
		super(ENTITY + " with " + MAIN_MSG + " not found");
	}
}
