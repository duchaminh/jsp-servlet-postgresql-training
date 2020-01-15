package com.hust.dao;

import java.util.List;

import com.hust.dto.AuthorityDTO;
import com.hust.dto.AuthorityWithName;
import com.hust.dto.GenderDTO;

public interface BaseDAO {
	List<AuthorityDTO> getListAuthority();
	List<AuthorityWithName> getListAuthorityName();
	AuthorityDTO getAuthorityByName(String authorityName);
	List<GenderDTO> getListGender();
}
