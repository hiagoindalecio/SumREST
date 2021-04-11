package io.swagger.dao;

import io.swagger.model.Sum;

import java.util.List;

public interface INumberDAO {
	public void insert(Sum sum);
	public List<Sum> getNumbers(Double min, Double max);
}