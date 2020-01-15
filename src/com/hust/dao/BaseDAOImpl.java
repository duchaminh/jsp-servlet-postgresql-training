package com.hust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hust.dto.AuthorityDTO;
import com.hust.dto.AuthorityWithName;
import com.hust.dto.GenderDTO;
import com.hust.util.DBConnectionUtil;

public class BaseDAOImpl implements BaseDAO {
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	@Override
	public List<AuthorityDTO> getListAuthority() {
		List<AuthorityDTO> list = null;
		AuthorityDTO author = null;
		try {
			list = new ArrayList<AuthorityDTO>();
			String sql = "SELECT authority_id, authority_name from mst_role";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				author = new AuthorityDTO();
				author.setAuthorityId(resultSet.getInt("authority_id"));
				author.setAuthorityName(resultSet.getString("authority_name"));
				
				list.add(author);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public List<AuthorityWithName> getListAuthorityName() {
		List<AuthorityWithName> list = null;
		AuthorityWithName author = null;
		try {
			list = new ArrayList<AuthorityWithName>();
			String sql = "SELECT authority_name from mst_role";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				author = new AuthorityWithName();
				author.setAuthorityName(resultSet.getString("authority_name"));
				list.add(author);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public List<GenderDTO> getListGender() {
		List<GenderDTO> list = null;
		GenderDTO gender = null;
		try {
			list = new ArrayList<GenderDTO>();
			String sql = "SELECT gender_id, gender_name from mst_gender";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				gender = new GenderDTO();
				gender.setGenderId(resultSet.getInt("gender_id"));
				gender.setGenderName(resultSet.getString("gender_name"));
				
				list.add(gender);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public AuthorityDTO getAuthorityByName(String authorityName) {
		AuthorityDTO auth = null;
		try {
			String sql = "SELECT authority_id from mst_role where authority_name = ?";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, authorityName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				auth = new AuthorityDTO();
				auth.setAuthorityId(resultSet.getInt("authority_id"));
				auth.setAuthorityName(authorityName);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return auth;
	}

}
