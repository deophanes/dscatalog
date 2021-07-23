package com.dslearn.dscatalog.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dslearn.dscatalog.services.exceptions.EntityNotfoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityNotfoundException.class)
	public ResponseEntity<StandardError> entityNotFound( EntityNotfoundException e, HttpServletRequest request ) {
		
		StandardError error = new StandardError();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Recurso não Encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status( HttpStatus.NOT_FOUND).body(error);
		
	}
	
}
