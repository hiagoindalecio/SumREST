package io.swagger.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.model.NumberEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ExceptionHandlerClassTest {

	@Autowired
	private ExceptionHandlerClass excHandlerImpl;
	
	@Test
	public void handleNumberException() throws Exception {
		
		ResponseEntity<NumberException> resp;
		
		//RESULT_NOT_FOUND_ON_QUERY
		resp = excHandlerImpl.handleNumberException(new Exception("RESULT_NOT_FOUND_ON_QUERY"));
		//Testing response
		assertEquals(resp.getBody().getDescription(), "Nothing has found");
		assertEquals(resp.getBody().getCause(), NumberEnum.RESULT_NOT_FOUND_ON_QUERY);
		assertEquals(resp.getBody().getStatus(), HttpStatus.NOT_FOUND);
		
		//DATA_ACCESS_FAILURE
		resp = excHandlerImpl.handleNumberException(new Exception("DATA_ACCESS_FAILURE"));
		//Testing response
		assertEquals(resp.getBody().getDescription(), "Failure executing queries");
		assertEquals(resp.getBody().getCause(), NumberEnum.DATA_ACCESS_FAILURE);
		assertEquals(resp.getBody().getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		//NULL_POINTER_EXCEPTION
		resp = excHandlerImpl.handleNumberException(new Exception("NULL_POINTER_EXCEPTION"));
		//Testing response
		assertEquals(resp.getBody().getDescription(), "Some object was not instantiated");
		assertEquals(resp.getBody().getCause(), NumberEnum.NULL_POINTER);
		assertEquals(resp.getBody().getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		//default
		resp = excHandlerImpl.handleNumberException(new Exception("UNKNOWN"));
		//Testing response
		assertEquals(resp.getBody().getDescription(), "Unknown error");
		assertEquals(resp.getBody().getCause(), NumberEnum.UNKNOWN);
		assertEquals(resp.getBody().getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
