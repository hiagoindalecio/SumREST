package io.swagger.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NumberDAO {
	
	JdbcTemplate jdbc;
	
	@Autowired
	public NumberDAO (DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
		
	}
}
