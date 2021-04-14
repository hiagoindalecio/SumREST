package io.swagger.model;

public enum NumberEnum {
	NULL_POINTER("NULL_POINTER"),
	RESULT_NOT_FOUND_ON_QUERY("RESULT_NOT_FOUND_ON_QUERY"),
	DATA_ACCESS_FAILURE("DATA_ACCESS_FAILURE"),
	ENTITY_NOT_FOUND_EXEPTION("ENTITY_NOT_FOUND_EXEPTION"),
	UNKNOWN("UNKNOWN");

	private final String value;
	
	NumberEnum(String currentValue) {
	   this.value = currentValue;
	}
	
	public String getValue() {
	   return value;
	}
}