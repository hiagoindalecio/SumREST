package io.swagger.service;

import java.util.ArrayList;
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
		List<Sum> ret = new ArrayList<>();;
		try {
			ret = dao.getNumbers(min, max);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public SumResponse InsertSum(Numbers numbers) {
		Sum sum = new Sum(numbers, null);
		try {
			dao.insert(sum.CalcResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SumResponse resp = new SumResponse("The sum between " + sum.getFirstNumber() + " and " + sum.getSecondNumber() + " results in: " + sum.getResult(), sum.getResult());
		return resp;
	}
}
