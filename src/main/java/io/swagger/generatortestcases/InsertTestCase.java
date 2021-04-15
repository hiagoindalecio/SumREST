package io.swagger.generatortestcases;

import io.swagger.model.Sum;

public class InsertTestCase {
	private Sum sum;

	public Sum getSum() {
		return sum;
	}

	public void setSum(Sum sum) {
		this.sum = sum;
	}
	
	public InsertTestCase(Sum sum) {
		this.sum = sum;
	}
}
