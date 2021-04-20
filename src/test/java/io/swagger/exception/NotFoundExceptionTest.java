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
public class NotFoundExceptionTest {

	private NotFoundException notFoundImpl;
	
	@Test
	public void InstantiatingAndGetting() {
		
		//Instantiate
		notFoundImpl = new NotFoundException(404, "Not Found.");
		//Testing format
		assertEquals(((Object)notFoundImpl.getCode()).getClass().getName(), "java.lang.Integer");
		assertTrue(notFoundImpl.getMessage() instanceof String);
		//Testing data
		assertEquals(notFoundImpl.getNotFoundCode(), 404);
		assertEquals(notFoundImpl.getMessage(), "Not Found.");
	}
}
