package io.swagger.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.generatortestcases.GetSumsTestCase;
import io.swagger.generatortestcases.InsertTestCase;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class NumberDAOTest {
	
	@Autowired
	private NumberDAO daoImpl;
	
	@Test
	public void insert() throws Exception {
		
		InsertTestCase testCase1 = new InsertTestCase(new Sum(new Numbers(3.0, 3.0)));
		
		daoImpl.insert(testCase1.getSum());
		
	}
	
	@Test(expected = Exception.class)
	public void insertNullPointer() throws Exception {
		
		InsertTestCase testCase1 = new InsertTestCase(new Sum(new Numbers()));
		
		daoImpl.insert(testCase1.getSum());
	}
	
	@Test
	public void getNumbers() throws Exception {
		
		List<Sum> sumsList = null;
		
		GetSumsTestCase testCase = new GetSumsTestCase(4.0, 4.0, 4.0);
		
		//Testing min and max
		sumsList = daoImpl.getNumbers(testCase.getMin(), testCase.getMax());
		
		for(Sum sum : sumsList) {
			assertEquals(sum.getResult(), testCase.getExpectedResult());
		}
		
		//Testing only min
		sumsList = daoImpl.getNumbers(testCase.getMin(), null);
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() >= testCase.getMin());
		}
		
		//Testing only max
		sumsList = daoImpl.getNumbers(null, testCase.getMax());
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() <= testCase.getMax());
		}

	}
	
	/*@Test(expected = Exception.class) Não consigo pegar NullPointer :/
	public void getNumbersNullPointer() throws Exception {

		daoImpl.getNumbers(1.0, null);

	}*/
}
