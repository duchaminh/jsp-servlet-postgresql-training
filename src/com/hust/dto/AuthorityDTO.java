package com.hust.dto;

public class AuthorityDTO {
	private int authorityId;
	
	private String authorityName;

	@Override
	public String toString() {
		return "AuthorityDTO [authorityId=" + authorityId + ", authorityName=" + authorityName + "]";
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
}
