package com.hust.dto;

public class UserDTOEdit {
	private String userId;
	
	private String familyName;
	
	private String firstName;
	
	private String password;
	
	private int genderId;
	
	private String genderName;
	
	private int age;
	
	private int authorityId;
	
	private String authorityName;
	
	private int admin;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	
}
