package io.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Numbers;
import io.swagger.model.Sum;
import io.swagger.model.SumResponse;
import io.swagger.dao.NumberDAO;

@Service
public class NumberService {
	
	@Autowired
	private NumberDAO dao;
	
	public List<Sum> getSums(Double min, Double max) {
		List<Sum> ret = dao.getNumbers(min, max);
		return ret;
	}
	
	public SumResponse InsertSum(Numbers numbers) {
		Sum sum = new Sum(numbers);
		dao.insert(sum);
		SumResponse resp = new SumResponse("The sum between " + sum.getFirstNumber() + " and " + sum.getSecondNumber() + " results in: " + sum.getResult(), sum.getResult());
		return resp;
	}
}
