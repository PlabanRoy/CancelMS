package com.cg.cap.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//This class is NOT a controller, but an Assistant to ALL controller within Application
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	/** 
	  * Capture all Exceptions of type ApplicationException,
	  * and return a new HttpResponse
	  */
	
	private long currentTimeMillis = System.currentTimeMillis();
	private String errorMsg = "Some thing went wrong!";
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex){
		
		ErrorMessage exceptionResponse =
				new ErrorMessage(ex.getMessage(), 
						errorMsg,currentTimeMillis);
		return new ResponseEntity<ErrorMessage>(exceptionResponse,
				new HttpHeaders(),HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(NullParameterException.class)
	public final ResponseEntity<ErrorMessage> nullParameter(NullParameterException ex){

		ErrorMessage exceptionResponse =
				new ErrorMessage(ex.getMessage(), 
						errorMsg,currentTimeMillis);
		return new ResponseEntity<ErrorMessage>(exceptionResponse,
				new HttpHeaders(),HttpStatus.NOT_FOUND);
	}


}
