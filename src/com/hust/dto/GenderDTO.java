package com.hust.dto;

public class GenderDTO {
	private int genderId;
	
	private String genderName;

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Override
	public String toString() {
		return "GenderDTO [genderId=" + genderId + ", genderName=" + genderName + "]";
	}
	
	
}
