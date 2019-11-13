package com.hust.dto;

public class UserDTO {
	private String userId;
	
	private String familyName;
	
	private String firstName;
	
	private String genderName;
	
	private int age;
	
	private String authorityName;

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", familyName=" + familyName + ", firstName=" + firstName + ", genderName="
				+ genderName + ", age=" + age + ", authorityName=" + authorityName + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
}
