package io.swagger.exception;

import org.springframework.http.HttpStatus;

import io.swagger.model.NumberEnum;

public class NumberException {
	
	private String description;
	private NumberEnum cause;
	private HttpStatus status;
	
	public NumberException(NumberEnum cause, String description, HttpStatus status) {
		this.cause = cause;
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public NumberEnum getCause() {
		return cause;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
	
}
