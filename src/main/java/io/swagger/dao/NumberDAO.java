package io.swagger.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.swagger.model.Sum;
import io.swagger.dao.SumRowMapper;

@Component
public class NumberDAO implements INumberDAO {
	
	JdbcTemplate jdbc;
	
	@Autowired
	public NumberDAO (DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(Sum sum) {
		jdbc.update("INSERT INTO sums(first_value, second_value, result) VALUES"+
					"(?,?,?)",
					sum.getFirstNumber(),
					sum.getSecondNumber(),
					sum.getResult());
	}

	@Override
	public List<Sum> getNumbers(Double min, Double max) {
		List<Sum> ret = new ArrayList<>();
		//System.out.println("min: " + min + " | max: " + max);
		if(min != null && max != null) {
			ret = jdbc.query("SELECT * FROM sums WHERE result BETWEEN " + min + " AND " + max, new SumRowMapper());
		} else if (min == null && max != null) {
			ret = jdbc.query("SELECT * FROM sums WHERE result <= " + max, new SumRowMapper());
		} else if (min != null && max == null) {
			ret = jdbc.query("SELECT * FROM sums WHERE result >= " + min, new SumRowMapper());
		} else {
			ret = jdbc.query("SELECT * FROM sums", new SumRowMapper());
		}
		return ret;
	}
}
