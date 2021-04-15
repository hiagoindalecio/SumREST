package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.generatortestcases.GetSumsTestCase;
import io.swagger.generatortestcases.InsertSumTestCase;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;
import io.swagger.model.SumResponse;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class NumberServiceTest {
	
	@Autowired
	private NumberService serviceImpl;
	
	@Test
	public void InsertSum() throws Exception {
		
		//Sending data normally
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(4.0, 4.0), 8.0);
		SumResponse sumResp = serviceImpl.InsertSum(insertCase.getNumbers());
		//4.0 + 4.0 -> The result should be 8.0
		assertEquals(insertCase.getExpectedResult(), sumResp.getResult(), 0.0001);

	}
	
	@Test(expected = Exception.class)
	public void InsertSumNullPointer() throws Exception {
		
		//Sending wrong data
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(null, null), null);
		SumResponse sumResp = serviceImpl.InsertSum(insertCase.getNumbers());
		//4.0 + 4.0 -> The result should be 8.0
		assertEquals(insertCase.getExpectedResult(), sumResp.getResult(), 0.0001);
		
	}
	
	//TO-DO: Função que cai em Exception de DataAccessException
	
	@Test
	public void getSums() throws Exception {
		
		List<Sum> sumsList = null;
		
		GetSumsTestCase testCase = new GetSumsTestCase(4.0, 4.0, 4.0);
		
		//Testing min and max
		sumsList = serviceImpl.getSums(testCase.getMin(), testCase.getMax());
		
		for(Sum sum : sumsList) {
			assertEquals(sum.getResult(), testCase.getExpectedResult());
		}
		
		//Testing only min
		sumsList = serviceImpl.getSums(testCase.getMin(), null);
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() >= testCase.getMin());
		}
		
		//Testing only max
		sumsList = serviceImpl.getSums(null, testCase.getMax());
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() <= testCase.getMax());
		}

	}
}
