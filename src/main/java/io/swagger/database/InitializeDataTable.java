package io.swagger.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class InitializeDataTable {
	
	JdbcTemplate jdbc;
	
	@Autowired
	public InitializeDataTable (DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
		/**
		 * Initialize database content
		 */
		this.jdbc.execute("DROP TABLE IF EXISTS sums");
		this.jdbc.execute("CREATE TABLE IF NOT EXISTS sums(" +
		        "first_value FLOAT NOT NULL," +
		        "second_value FLOAT NOT NULL," +
		        "result FLOAT NOT NULL" +
		        ")");
		
		//Populating for tests
		this.jdbc.update("INSERT INTO sums(first_value, second_value, result) VALUES"
				+ "(2, 2, 4)");
		this.jdbc.update("INSERT INTO sums(first_value, second_value, result) VALUES"
				+ "(5, 5, 10)");
		this.jdbc.update("INSERT INTO sums(first_value, second_value, result) VALUES"
				+ "(6, 5, 11)");
	}

}
