package com.hust.restapi;

import java.util.List;

import com.hust.dao.BaseDAO;
import com.hust.dao.BaseDAOImpl;
import com.hust.dto.AuthorityDTO;

public class AuthorityRepository {
	BaseDAO baseDAO = null;
	
	public AuthorityRepository() {
		baseDAO = new BaseDAOImpl();
	}
	
	public List<AuthorityDTO> getListAuthority(){
		return baseDAO.getListAuthority();
	}
	
	public AuthorityDTO getAuthorityByName(String authorityName) {
		return baseDAO.getAuthorityByName(authorityName);
	}
}
