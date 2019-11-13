package com.hust.dao;

import java.util.List;

import com.hust.dto.UserDTO;

public interface UserDAO {
	boolean check(String username, String password);
	List<UserDTO> get();
}
