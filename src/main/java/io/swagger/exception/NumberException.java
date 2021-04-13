package io.swagger.exception;

import org.springframework.http.HttpStatus;
import java.lang.Exception;

public class NumberException extends Exception {

	private static final long serialVersionUID = -2908714035211214719L;
	
	private String description;
	private NumberEnum cause;
	private HttpStatus status;
	
	public NumberException(NumberEnum cause, String description, HttpStatus status) {
		this.cause = cause;
		this.description = description;
		this.status = status;
	}
	
	public enum NumberEnum {
		BAD_QUERY_PARAMS("BAD_QUERY_PARAMS"),
		NULL_POINTER("NULL_POINTER"),
		RESULT_NOT_FOUND_ON_QUERY("RESULT_NOT_FOUND_ON_QUERY"),
		DATA_ACCESS_FAILURE("DATA_ACCESS_FAILURE");

		private final String value;
		
		NumberEnum(String currentValue) {
		   this.value = currentValue;
		}
		
		public String getValue() {
		   return value;
		}
	}
	
	
}
