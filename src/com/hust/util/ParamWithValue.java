package com.hust.util;

public class ParamWithValue {
	public ParamWithValue(String column, String value) {
		super();
		this.column = column;
		this.value = value;
	}

	private String column;
	
	private String value;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
