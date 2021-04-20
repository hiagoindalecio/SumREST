package io.swagger.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ApiExceptioTest {

	private ApiException apiExcImpl;
	
	@Test
	public void InstantiatingAndGetting() {
		
		//Instantiate
		apiExcImpl = new ApiException(500, "Internal Server Error.");
		//Testing format
		assertEquals(((Object)apiExcImpl.getCode()).getClass().getName(), "java.lang.Integer");
		assertTrue(apiExcImpl.getMessage() instanceof String);
		//Testing data
		assertEquals(apiExcImpl.getCode(), 500);
		assertEquals(apiExcImpl.getMessage(), "Internal Server Error.");
	}
}
