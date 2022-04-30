package com.krugercorp.ec.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.krugercorp.ec.dto.ErrorDetails;

@RestControllerAdvice
public class ArgumentNotValidController {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return errors;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorDetails managerException(ResourceNotFoundException exception, WebRequest webRequest){
			
		return new ErrorDetails(exception.getMessage(),webRequest.getDescription(false));
	}
	
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(Exception.class)
	public ErrorDetails managerException(Exception exception, WebRequest webRequest){
			
		return new ErrorDetails(exception.getMessage(),webRequest.getDescription(false));
	}
	
}
