package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.dao.NumberDAO;
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
	
	@Autowired
	private NumberDAO dao;
	
	@Autowired
	DataSource dataSource;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected = Exception.class)
	public void InsertSumDataAccessException() throws Exception {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:h2:mem:mydb");
		driverManagerDataSource.setUsername("sa");
		driverManagerDataSource.setPassword("wrongpassword");
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
		dao.setJdbc(new JdbcTemplate(driverManagerDataSource));
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(4.0, 4.0), 8.0);
		serviceImpl.InsertSum(insertCase.getNumbers());
		driverManagerDataSource.setPassword("");
		dao.setJdbc(new JdbcTemplate(driverManagerDataSource));
		thrown.expectMessage("DATA_ACCESS_FAILURE");
	}
	
	@Test
	public void InsertSum() throws Exception {
		
		//Sending data normally
		dao.setJdbc(new JdbcTemplate(dataSource));
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(4.0, 4.0), 8.0);
		SumResponse sumResp = serviceImpl.InsertSum(insertCase.getNumbers());
		//4.0 + 4.0 -> The result should be 8.0
		assertEquals(insertCase.getExpectedResult(), sumResp.getResult(), 0.0001);

	}
	
	@Test(expected = Exception.class)
	public void InsertSumNullPointer() throws Exception {
		
		//Sending wrong data
		InsertSumTestCase insertCase = new InsertSumTestCase(new Numbers(null, null), null);
		serviceImpl.InsertSum(insertCase.getNumbers());
		
	}
	
	@Test
	public void getSums() throws Exception {
		
		dao.setJdbc(new JdbcTemplate(dataSource));
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
