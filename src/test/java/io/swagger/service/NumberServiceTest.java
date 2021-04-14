package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
		Numbers num = new Numbers();
		num.setFirstNumber(4.0);
		num.setSecondNumber(4.0);
		SumResponse sumResp = serviceImpl.InsertSum(num);
		//4.0 + 4.0 -> The result should be 8.0
		assertEquals(8.0d, sumResp.getResult(), 0.0001);
	}
	
	@Test
	public void getSums() throws Exception {
		
		//Testing min and max
		List<Sum> sumsList = serviceImpl.getSums(4.0, 4.0);
		
		for(Sum sum : sumsList) {
			assertEquals(4.0d, sum.getResult(), 0.0001);
		}
		
		//Testing only min
		sumsList = serviceImpl.getSums(4.0, null);
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() >= 4);
		}
		
		//Testing only max
		sumsList = serviceImpl.getSums(null, 10.0);
		
		for(Sum sum : sumsList) {
			assertTrue(sum.getResult() <= 10.0);
		}
	}
}
