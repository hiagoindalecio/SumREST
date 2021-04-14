package io.swagger.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.swagger.controller.NumbersApiController;
import io.swagger.model.NumberEnum;
import io.swagger.model.Sum;
import io.swagger.model.SumRowMapper;

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
			if (ex instanceof DataAccessException) {
				 throw new Exception(NumberEnum.DATA_ACCESS_FAILURE.toString());
			} else if (ex instanceof NullPointerException) {
				throw new Exception(NumberEnum.NULL_POINTER.toString());
			}
		}
	}

	@Override
	public List<Sum> getNumbers(Double min, Double max) throws Exception {
		
		log.info("Initializing numberdao.getNumbers()");
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
				 throw new Exception(NumberEnum.DATA_ACCESS_FAILURE.toString());
			} else if (ex instanceof NullPointerException) {
				throw new Exception(NumberEnum.NULL_POINTER.toString());
			}
		}
		if(ret.size() == 0) {
			throw new Exception(NumberEnum.RESULT_NOT_FOUND_ON_QUERY.toString());
		} 
		return ret;
	}
}
