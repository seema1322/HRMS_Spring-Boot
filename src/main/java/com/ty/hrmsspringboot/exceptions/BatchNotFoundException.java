package com.ty.hrmsspringboot.exceptions;

public class BatchNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The requested batch is not found";
	}
}
