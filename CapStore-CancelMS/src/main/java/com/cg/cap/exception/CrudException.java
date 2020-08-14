package com.cg.cap.exception;

@SuppressWarnings("serial")
public class CrudException extends RuntimeException  {
	
	public CrudException(String message) {
		super(message);
	}

}
