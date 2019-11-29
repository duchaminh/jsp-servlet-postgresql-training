package com.hust.util;

public class ConditionForAggregate {
	private String tableName;
	
	private String columnName;
	
	private String condition;

	public String getTableName() {
		return tableName;
	}
	
	
	
	public ConditionForAggregate(String tableName, String columnName, String condition) {
		super();
		this.tableName = tableName;
		this.columnName = columnName;
		this.condition = condition;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
