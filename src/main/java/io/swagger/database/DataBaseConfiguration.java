package io.swagger.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@ComponentScan("com.journaldev.spring")
@PropertySource("classpath:application.properties")
public class DataBaseConfiguration {
	
	@Autowired
	Environment environment;

	private final String URL = "jdbc:h2:mem:mydb";
	private final String USER = "sa";
	private final String DRIVER = "org.h2.Driver";
	private final String PASSWORD = "";
	
	@Bean
	DataSource dataSource() {
		System.out.println("Caiu DataBaseConfiguration.dataSource()");
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
}
