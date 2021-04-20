package io.swagger.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.generatortestcases.GetSumsTestCase;
import io.swagger.generatortestcases.InsertSumTestCase;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;
import io.swagger.model.SumResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class NumbersApiControllerTest {

	@Autowired
	private NumbersApiController controllerImpl;
	
	@Test
	public void getSums() throws Exception {
		
		ResponseEntity<List<Sum>> sumsList = null;
		GetSumsTestCase testCase = new GetSumsTestCase(4.0, 4.0, 4.0);
		
		//Testing min and max
		sumsList = controllerImpl.getSums(testCase.getMin(), testCase.getMax());
		
		for(Sum sum : sumsList.getBody()) {
			assertEquals(sum.getResult(), testCase.getExpectedResult());
		}
		
		//Testing only min
		sumsList = controllerImpl.getSums(testCase.getMin(), null);
		
		for(Sum sum : sumsList.getBody()) {
			assertTrue(sum.getResult() >= testCase.getMin());
		}
		
		//Testing only max
		sumsList = controllerImpl.getSums(null, testCase.getMax());
		
		for(Sum sum : sumsList.getBody()) {
			assertTrue(sum.getResult() <= testCase.getMax());
		}
	}
	
	@Test
	public void insertSum() throws Exception {
		
		ResponseEntity<SumResponse> sumResp;
		
		sumResp = controllerImpl.insertSum(new Numbers(2.0, 2.0));
		assertTrue(sumResp.getBody().getResult() == 4.0);
	}
	
	@Test(expected = Exception.class)//Ta errado tem que entrar no NullPointer (Ta estourando aqui)
	public void insertSumNumberFormatException() throws Exception {
		
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(null, 1.0), null);
		controllerImpl.insertSum(insertCase.getNumbers());
	}
}
