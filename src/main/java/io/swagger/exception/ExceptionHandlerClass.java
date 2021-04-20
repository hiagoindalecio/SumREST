package io.swagger.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.model.NumberEnum;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler  {
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<NumberException> handleNumberException (Exception ex) throws Exception {
		Map<String, Object> ret = getNumberException(ex.getMessage());
		NumberException numEx = (NumberException) ret.get("response");
		HttpStatus stat = (HttpStatus) ret.get("status");
		return new ResponseEntity<>(numEx, stat);
	}
	
	public Map<String, Object> getNumberException(String message) {
		NumberException ex = null;
		HttpStatus status = null;
		Map<String, Object> ret = new LinkedHashMap<>();
		
		switch(message) {
			case "RESULT_NOT_FOUND_ON_QUERY":
				ex = new NumberException(NumberEnum.RESULT_NOT_FOUND_ON_QUERY, "Nothing has found", HttpStatus.NOT_FOUND);
				status = HttpStatus.NOT_FOUND;
				break;
			case "DATA_ACCESS_FAILURE":
				ex = new NumberException(NumberEnum.DATA_ACCESS_FAILURE, "Failure executing queries", HttpStatus.INTERNAL_SERVER_ERROR);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case "NULL_POINTER_EXCEPTION":
				ex = new NumberException(NumberEnum.NULL_POINTER, "Some object was not instantiated", HttpStatus.INTERNAL_SERVER_ERROR);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			default:
				ex = new NumberException(NumberEnum.UNKNOWN, "Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
		}
		
		ret.put("response", ex);
		ret.put("status", status);
		return ret;
	}
	
}
