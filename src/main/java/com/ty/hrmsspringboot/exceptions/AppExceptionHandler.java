package com.ty.hrmsspringboot.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.hrmsspringboot.dto.ResponseStructure;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotfoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotfoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData(exception.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;

	}

	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> batchNotFoundException(BatchNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData(exception.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
	}

	@ExceptionHandler(AttendanceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> attendanceNotFoundException(
			AttendanceNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData(exception.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseStructure<String>> dataIntegrityViolationException(DataIntegrityViolationException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData("Please avoid duplicate entries");

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseStructure<String>> nullPointerException(NullPointerException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData("Don't deal with null values");

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
}
