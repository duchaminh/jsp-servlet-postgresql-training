package com.hust.dao;

import java.util.List;

import com.hust.dto.AggregateByAuthority;
import com.hust.dto.UserDTO;
import com.hust.dto.UserDTOEdit;
import com.hust.dto.UserDTOFromAuthorityId;

import model.User;

public interface UserDAO {
	boolean check(String username, String password);
	List<UserDTO> get();
	boolean save(User user);
	boolean isOverLapUserId(String userId);
	UserDTOEdit get(String userid);
	boolean update(User user);
	boolean delete(String userid);
	List<UserDTO> search(String column, String key);
	List<UserDTO> search(String column1, String column2, String key1, String key2);
	List<UserDTO> search(String column1, String column2, String column3,String key1, String key2, String key3 );
	List<AggregateByAuthority> listAggregateByAuthority();
	void clusteringUserDtoByAuthorityId(List<AggregateByAuthority> list);
	void clusteringComplete(List<AggregateByAuthority> list);
}
