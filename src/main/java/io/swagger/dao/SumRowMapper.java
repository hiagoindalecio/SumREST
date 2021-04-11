package io.swagger.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.Sum;

public class SumRowMapper implements RowMapper<Sum>, Serializable {

	@Override
	public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Sum.build(rs.getDouble("firstNumber"),
				         rs.getDouble("secondNumber"),
				         rs.getDouble("result"));
	}

}
