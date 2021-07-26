package com.dslearn.dscatalog.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dslearn.dscatalog.services.exceptions.DatabaseException;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ServiceNotfoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ServiceNotfoundException e, HttpServletRequest request) {

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(httpStatus.value());
		error.setError("Recurso não Encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);

	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(httpStatus.value());
		error.setError("Database Exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);

	}

}
