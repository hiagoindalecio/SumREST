package io.swagger.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.swagger.controller.NumbersApiController;
import io.swagger.exception.NumberException;
import io.swagger.exception.NumberException.NumberEnum;
import io.swagger.model.Sum;

@Component
public class NumberDAO implements INumberDAO {
	
	JdbcTemplate jdbc;
	
	private static final Logger log = LoggerFactory.getLogger(NumbersApiController.class);
	
	@Autowired
	public NumberDAO (DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(Sum sum) throws Exception {
		try {
			jdbc.update("INSERT INTO sums(first_value, second_value, result) VALUES"+
					"(?,?,?)",
					sum.getFirstNumber(),
					sum.getSecondNumber(),
					sum.getResult());
		} catch (Exception ex) {
			log.error("Error - An exception has ocurred: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public List<Sum> getNumbers(Double min, Double max) throws Exception {
		List<Sum> ret = new ArrayList<>();
		try {
			if(min != null && max != null) {
				ret = jdbc.query("SELECT * FROM sums WHERE result BETWEEN " + min + " AND " + max, new SumRowMapper());
			} else if (min == null && max != null) {
				ret = jdbc.query("SELECT * FROM sums WHERE result <= " + max, new SumRowMapper());
			} else if (min != null && max == null) {
				ret = jdbc.query("SELECT * FROM sums WHERE result >= " + min, new SumRowMapper());
			} else {
				ret = jdbc.query("SELECT * FROM sums", new SumRowMapper());
			}
		} catch (Exception ex) {
			log.error("Error - An exception has ocurred: " + ex.getMessage());
			ex.printStackTrace();
			if (ex instanceof DataAccessException) {
				 throw new NumberException(NumberEnum.DATA_ACCESS_FAILURE, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		if(ret.size() == 0) {
			throw new NumberException(NumberEnum.RESULT_NOT_FOUND_ON_QUERY, "Nothing has found.", HttpStatus.NOT_FOUND);
		}
		return ret;
	}
}
