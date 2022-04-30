package com.krugercorp.ec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String field;
	private String value;
	
	public ResourceNotFoundException(String field, String value) {
		super(String.format("Resource not found by %s = %s", field, value));
		this.field = field;
		this.value = value;
	}
	
}
