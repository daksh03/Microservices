package com.microservices.practice.microservices_project.exceptions;

import org.springframework.http.HttpHeaders;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservices.practice.microservices_project.controller.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)	
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex,WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)	
	public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex,WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),"No of errors" + ex.getErrorCount() + " "+ ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		
	}

}
