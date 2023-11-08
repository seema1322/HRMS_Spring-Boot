package com.ty.hrmsspringboot.exceptions;

public class AttendanceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The attendance details are not found";
	}
}
