package com.hust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	@Override
	public boolean save(User user) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO mst_user(user_id, password, family_name, first_name, gender_id, authority_id,age, admin, create_user_id, update_user_id, create_date, update_date)"
			  + "VALUES('"+user.getUserId()+"', '"+user.getPassword()+"', '"+user.getFamilyName()+"', '"+user.getFirstName()+"', "
			  + "'"+user.getGenderId()+"', '"+user.getAuthorityId()+"', '"+user.getAge()+"', '"+user.getAdmin()+"', "
			  + "'"+user.getCreateUserId()+"', '"+user.getUpdateUserID()+"', '"+user.getCreateDate()+"', '"+user.getUpdateDate()+"')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isOverLapUserId(String userId) {
		List<String> userIds = null;
		
		try {
			userIds = new ArrayList<String>();
			String sql = "SELECT user_id FROM mst_user";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {		
				userIds.add(resultSet.getString("user_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(userIds.indexOf(userId) > -1)
			return true;
		return false;
		
	}

	@Override
	public User get(String userid) {
		User user = null;
		try {
			user = new User();
			String sql = "select * from mst_user where user_id LIKE \'"+userid +"\'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				user.setUserId(resultSet.getString("user_id"));
				user.setPassword(resultSet.getString("password"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setGenderId(resultSet.getInt("gender_id"));
				user.setAuthorityId(resultSet.getInt("authority_id"));
				user.setAge(resultSet.getInt("age"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setCreateUserId(resultSet.getString("create_user_id"));
				user.setUpdateUserID(resultSet.getString("update_user_id"));
				user.setCreateDate(resultSet.getLong("create_date"));
				user.setUpdateDate(resultSet.getLong("update_date"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	
		return user;
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		try {
			String sql = "UPDATE mst_user SET password = '"+user.getPassword()+"', family_name = '"+user.getFamilyName()+"', first_name = '"+user.getFirstName()+"', gender_id = '"+user.getGenderId()+"',age = '"+user.getAge()+"', admin = '"+user.getAdmin()+"', create_user_id = '"+user.getCreateUserId()+"', "
					+ "update_user_id = '"+user.getUpdateUserID()+"',create_date = '"+user.getCreateDate()+"', "
							+ "update_date = '"+user.getUpdateDate()+"', authority_id = '"+user.getAuthorityId()+"' where user_id LIKE \'"+user.getUserId()+"\'";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String userid){
		boolean flag = false;
		try {
			String sql = "DELETE from mst_user where user_id LIKE \'"+userid+"\'";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
