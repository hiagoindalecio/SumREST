package io.swagger.generatortestcases;

import io.swagger.model.Numbers;

public class InsertSumTestCase {
	private Numbers numbers;
	private Double expectedResult;

	public Numbers getNumbers() {
		return numbers;
	}

	public Double getExpectedResult() {
		return expectedResult;
	}
	
	public InsertSumTestCase(Numbers numbers, Double expected) {
		this.numbers = numbers;
		this.expectedResult = expected;
	}
}
