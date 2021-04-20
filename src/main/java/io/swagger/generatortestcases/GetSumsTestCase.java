package io.swagger.generatortestcases;

public class GetSumsTestCase {
	private Double min, max, expectedResult;
	
	public Double getMin() {
		return this.min;
	}
	
	public Double getMax() {
		return this.max;
	}
	
	public Double getExpectedResult() {
		return this.expectedResult;
	}
	
	public GetSumsTestCase(Double min, Double max, Double expected) {
		this.min = min;
		this.max = max;
		this.expectedResult = expected;
	}
}
