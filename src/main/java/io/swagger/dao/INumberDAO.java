package io.swagger.dao;

import io.swagger.model.Sum;

import java.util.List;

public interface INumberDAO {
	public void insert(Sum sum) throws Exception;
	public List<Sum> getNumbers(Double min, Double max) throws Exception;
}