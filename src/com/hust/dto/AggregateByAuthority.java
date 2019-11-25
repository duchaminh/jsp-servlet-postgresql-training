package com.hust.dto;

import java.util.List;

public class AggregateByAuthority {
	private int authorityId;
	
	private String authorityName;
	
	public String getAuthorityName() {
		return authorityName;
	}

	private List<UserDTOFromAuthorityId> listUserDTOFromAuthorityId;
	
	private int countMan;
	
	private int countWomen;
	
	private int countGenderNone;
	
	private int countAgeBeetweenZeroToNineTeen;
	
	private int countAgeMoreThanTwenty;
	
	private int countAgeNone;

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public List<UserDTOFromAuthorityId> getListUserDTOFromAuthorityId() {
		return listUserDTOFromAuthorityId;
	}

	public void setListUserDTOFromAuthorityId(List<UserDTOFromAuthorityId> userDTOFromAuthorityId) {
		this.listUserDTOFromAuthorityId = userDTOFromAuthorityId;
	}

	public int getCountMan() {
		return countMan;
	}

	public void setCountMan(int countMan) {
		this.countMan = countMan;
	}

	public int getCountWomen() {
		return countWomen;
	}

	public void setCountWomen(int countWomen) {
		this.countWomen = countWomen;
	}

	public int getCountGenderNone() {
		return countGenderNone;
	}

	public void setCountGenderNone(int countGenderNone) {
		this.countGenderNone = countGenderNone;
	}

	public int getCountAgeBeetweenZeroToNineTeen() {
		return countAgeBeetweenZeroToNineTeen;
	}

	public void setCountAgeBeetweenZeroToNineTeen(int countAgeBeetweenZeroToNineTeen) {
		this.countAgeBeetweenZeroToNineTeen = countAgeBeetweenZeroToNineTeen;
	}

	public int getCountAgeMoreThanTwenty() {
		return countAgeMoreThanTwenty;
	}

	public void setCountAgeMoreThanTwenty(int countAgeMoreThanTwenty) {
		this.countAgeMoreThanTwenty = countAgeMoreThanTwenty;
	}

	public int getCountAgeNone() {
		return countAgeNone;
	}

	public void setCountAgeNone(int coundAgeNone) {
		this.countAgeNone = coundAgeNone;
	}
	
	public void init() {
		this.countMan = 0;
		this.countWomen = 0;
		this.countGenderNone = 0;
		
		this.countAgeBeetweenZeroToNineTeen = 0;
		
		this.countAgeMoreThanTwenty = 0;
		
		this.countAgeNone = 0;
	}
	
	public void increaseMan() {
		this.countMan++;
	}
	
	public void increaseWoMen() {
		this.countWomen++;
	}
	
	public void increaseGenderNone() {
		this.countGenderNone++;
	}
	
	public void increaseAgeBeetweenZeroToNineTeen() {
		this.countAgeBeetweenZeroToNineTeen++;
	}
	
	public void increaseAgeMoreThanTwenty() {
		this.countAgeMoreThanTwenty++;
	}
	
	public void increaseAgeNone() {
		this.countAgeNone++;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
		
	}
}
