package com.hust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hust.dto.UserDTO;
import com.hust.util.DBConnectionUtil;

import model.User;

public class UserDAOImpl implements UserDAO {
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	@Override
	public boolean check(String username, String password) {
		try {
			String sql = "select * from mst_user where user_id=? and password=?";
			connection = (Connection) DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserDTO> get() {
		List<UserDTO> users = null;
		UserDTO user = null;
		
		try {
			users = new ArrayList<UserDTO>();
			String sql = "SELECT user_id, first_name, family_name, age, authority_name, gender_name FROM mst_user, mst_role,mst_gender where mst_user.gender_id =mst_gender.gender_id and mst_user.authority_id = mst_role.authority_id";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				user = new UserDTO();
				
				user.setUserId(resultSet.getString("user_id"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setAge(resultSet.getInt("age"));
				user.setAuthorityName(resultSet.getString("authority_name"));
				user.setGenderName(resultSet.getString("gender_name"));
			
				users.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
