package io.swagger.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.model.NumberEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class NumberExceptionTest {
	
	private NumberException numExImpl;
	
	@Test
	public void InstantiatingAndGetting() {
		
		//Instantiate
		numExImpl = new NumberException(NumberEnum.BAD_REQUEST, "BAD_REQUEST case", HttpStatus.BAD_REQUEST);
		//Testing format
		assertTrue(numExImpl.getStatus() instanceof HttpStatus);
		assertTrue(numExImpl.getDescription() instanceof String);
		assertTrue(numExImpl.getCause() instanceof NumberEnum);
		//Testing data
		assertEquals(numExImpl.getStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(numExImpl.getDescription(), "BAD_REQUEST case");
		assertEquals(numExImpl.getCause(), NumberEnum.BAD_REQUEST);
	}
}
