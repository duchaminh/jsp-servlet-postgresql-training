package com.hust.dao;

import java.util.List;

import com.hust.dto.AggregateByAuthority;
import com.hust.dto.UserDTO;
import com.hust.dto.UserDTOEdit;
import com.hust.dto.UserDTOFromAuthorityId;
import com.hust.util.ConditionForAggregate;
import com.hust.util.ParamWithValue;

import model.User;

public interface UserDAO {
	boolean check(String username, String password);
	List<UserDTO> get();
	boolean save(User user);
	boolean isOverLapUserId(String userId);
	UserDTOEdit get(String userid);
	boolean update(User user);
	boolean delete(String userid);
	
	List<UserDTO> search(List<ParamWithValue> listParam);
	
}
