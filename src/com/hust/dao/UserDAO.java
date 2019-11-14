package com.hust.dao;

import java.util.List;

import com.hust.dto.UserDTO;

import model.User;

public interface UserDAO {
	boolean check(String username, String password);
	List<UserDTO> get();
	boolean save(User user);
	boolean isOverLapUserId(String userId);
}
