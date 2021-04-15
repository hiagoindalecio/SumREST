package io.swagger.generatortestcases;

public class GetSumsTestCase {
	private Double min, max, expectedResult;
	
	public Double getMin() {
		return this.min;
	}
	
	public void setMin(Double min) {
		this.min = min;
	}
	
	public Double getMax() {
		return this.max;
	}
	
	public void setMax(Double max) {
		this.max = max;
	}
	
	public Double getExpectedResult() {
		return this.expectedResult;
	}
	
	public void setExpectedResult(Double expectedResult) {
		this.expectedResult = expectedResult;
	}
	
	public GetSumsTestCase(Double min, Double max, Double expected) {
		this.min = min;
		this.max = max;
		this.expectedResult = expected;
	}
}
