package com.ty.hrmsspringboot.exceptions;

public class UserNotfoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UserNotfoundException() {
		
	}

	public UserNotfoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	
}
