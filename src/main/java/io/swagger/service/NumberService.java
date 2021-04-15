package io.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import io.swagger.model.NumberEnum;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;
import io.swagger.model.SumResponse;
import io.swagger.dao.NumberDAO;
import io.swagger.exception.NumberException;

@Service
public class NumberService {
	
	@Autowired
	private NumberDAO dao;
	
	public List<Sum> getSums(Double min, Double max) throws Exception {
		
		List<Sum> ret = new ArrayList<>();
		ret = dao.getNumbers(min, max);
		
		return ret;
	}
	
	public SumResponse InsertSum(Numbers numbers) throws Exception {
		
		SumResponse resp = null;
		
		try {
			Sum sum = new Sum(numbers);
			dao.insert(sum);
			resp = new SumResponse("The sum between " + sum.getFirstNumber() + " and " + sum.getSecondNumber() + " results in: " + sum.getResult(), sum.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("NumberService.InsertSum() - Exception: " + e.getMessage());
			if (e instanceof DataAccessException) {
				 throw new Exception(NumberEnum.DATA_ACCESS_FAILURE.toString());
			} else if (e instanceof NullPointerException) {
				System.out.println("Caiu NullPointer");
				throw new Exception(NumberEnum.NULL_POINTER.toString());
			}
		}
		
		return resp;
	}
}
