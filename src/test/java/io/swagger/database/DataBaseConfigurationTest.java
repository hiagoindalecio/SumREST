package io.swagger.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.controller.NumbersApiController;

/*@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration*/
public class DataBaseConfigurationTest {
	
	private static final Logger log = LoggerFactory.getLogger(NumbersApiController.class);

	/*DataBaseConfiguration dtBaseConfigIplm = new DataBaseConfiguration();
	
	@Test
	public void dataSource() throws SQLException {
		DataSource dt = dtBaseConfigIplm.dataSource();
		log.info("Catalog:" + dt.getConnection().getCatalog());
	}*/
}
