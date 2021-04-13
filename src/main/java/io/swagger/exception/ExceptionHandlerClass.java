package io.swagger.exception;

import javax.naming.NameNotFoundException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.exception.NumberException.NumberEnum;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ NumberFormatException.class })
	public ResponseEntity<NumberException> handleBadRequestException(Exception ex) {
        return new ResponseEntity<NumberException>(new NumberException(NumberEnum.BAD_QUERY_PARAMS, ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ DataAccessException.class, NullPointerException.class })
	public ResponseEntity<NumberException> handleInternalErrorException (Exception ex) {
		if(ex instanceof NullPointerException) {
			return new ResponseEntity<NumberException>(new NumberException(NumberEnum.NULL_POINTER, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<NumberException>(new NumberException(NumberEnum.DATA_ACCESS_FAILURE, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ExceptionHandler({ NameNotFoundException.class })
	public ResponseEntity<NumberException> handleNotFoundException (Exception ex) {
		return new ResponseEntity<NumberException>(new NumberException(NumberEnum.RESULT_NOT_FOUND_ON_QUERY, ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
}
