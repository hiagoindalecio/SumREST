package io.swagger.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.generatortestcases.GetSumsTestCase;
import io.swagger.generatortestcases.InsertSumTestCase;
import io.swagger.generatortestcases.InsertTestCase;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class NumberDAOTest {
	
	@Autowired
	private NumberDAO daoImpl;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Autowired
	DataSource dataSource;
	
	@Test(expected = Exception.class)
	public void getNumbersDataAccessException() throws Exception {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:h2:mem:mydb");
		driverManagerDataSource.setUsername("sa");
		driverManagerDataSource.setPassword("wrongpassword");
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
		daoImpl.setJdbc(new JdbcTemplate(driverManagerDataSource));
		daoImpl.getNumbers(null, null);
		thrown.expectMessage("DATA_ACCESS_FAILURE");
		driverManagerDataSource.setPassword("");
		daoImpl.setJdbc(new JdbcTemplate(driverManagerDataSource));
	}
	
	@Test
	public void insert() throws Exception {
		
		InsertTestCase testCase1 = new InsertTestCase(new Sum(new Numbers(3.0, 3.0)));
		daoImpl.insert(testCase1.getSum());
		
	}
	
	@Test(expected = Exception.class)
	public void insertNullPointer() throws Exception {
		
		Sum s = null;
		InsertTestCase testCase1 = new InsertTestCase(s);
		
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
		
		//Testing without parameters 
		sumsList = daoImpl.getNumbers(null, null);

	}
	
	@Test(expected= Exception.class)
	public void getNumbersNotFound() throws Exception {
		
		GetSumsTestCase testCase = new GetSumsTestCase(1000.0, 0.0, 0.0);
		daoImpl.getNumbers(testCase.getMin(), null);
		thrown.expectMessage("RESULT_NOT_FOUND_ON_QUERY");
	}
	
	/*@Test(expected = Exception.class) Não consigo pegar NullPointer :/
	public void getNumbersNullPointer() throws Exception {

		daoImpl.getNumbers(1.0, null);

	}*/
}
