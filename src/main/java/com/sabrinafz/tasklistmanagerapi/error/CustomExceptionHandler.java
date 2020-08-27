package com.sabrinafz.tasklistmanagerapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<CustomResponseError> handleUserNotFoundException(CustomNotFoundException exc) {

		CustomResponseError error = new CustomResponseError();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<CustomResponseError>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CustomResponseError> handleException(Exception exc) {

		CustomResponseError error = new CustomResponseError();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<CustomResponseError>(error, HttpStatus.BAD_REQUEST);
	}


}
