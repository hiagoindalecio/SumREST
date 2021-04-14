package io.swagger.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SumRowMapper implements RowMapper<Sum>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Sum.Builder(
				rs.getDouble("first_value"),
				rs.getDouble("second_value"),
				rs.getDouble("result"))
				.build();
	}

}
