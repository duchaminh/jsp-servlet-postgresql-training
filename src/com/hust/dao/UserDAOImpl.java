package com.hust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hust.dto.AggregateByAuthority;
import com.hust.dto.UserDTO;
import com.hust.dto.UserDTOEdit;
import com.hust.dto.UserDTOFromAuthorityId;
import com.hust.util.DBConnectionUtil;

import model.User;

public class UserDAOImpl implements UserDAO {
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	
	public static final int ZERO_YEARS_OLD = 0;
	public static final int ONE_YEARS_OLD = 1;
	public static final int NINETEEN_YEARS_OLD = 19;
	public static final int TWENTY_YEARS_OLD = 20;
	
	public static final int IS_MAN = 1;
	public static final int IS_WOMEN = 0;
	
	@Override
	public boolean check(String username, String password){
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
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public List<UserDTO> get(){
		List<UserDTO> users = null;
		UserDTO user = null;
		
		try { 
			users = new ArrayList<UserDTO>();
			String sql = "SELECT user_id, admin, first_name, family_name, age, authority_name, gender_name FROM mst_user, mst_role,mst_gender where mst_user.gender_id =mst_gender.gender_id and mst_user.authority_id = mst_role.authority_id";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				user = new UserDTO();
				
				user.setUserId(resultSet.getString("user_id"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setAge(resultSet.getInt("age"));
				user.setAuthorityName(resultSet.getString("authority_name"));
				user.setGenderName(resultSet.getString("gender_name"));
			
				users.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
	
	@Override
	public boolean save(User user){
		boolean flag = false;
		try {
			String sql = "INSERT INTO mst_user(user_id, password, family_name, first_name, gender_id, authority_id,age, admin, create_user_id, update_user_id, create_date, update_date) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFamilyName());
			preparedStatement.setString(4, user.getFirstName());
			preparedStatement.setInt(5, user.getGenderId());
			preparedStatement.setInt(6, user.getAuthorityId());
			preparedStatement.setInt(7, user.getAge());
			preparedStatement.setInt(8, user.getAdmin());
			preparedStatement.setString(9, user.getCreateUserId());
			preparedStatement.setString(10, user.getUpdateUserID());
			preparedStatement.setLong(11, user.getCreateDate());
			preparedStatement.setLong(12, user.getUpdateDate());
			
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(userIds.indexOf(userId) > -1)
			return true;
		return false;
		
	}

	@Override
	public UserDTOEdit get(String userid) {
		UserDTOEdit user = null;
		try {
			user = new UserDTOEdit();
			String sql = "SELECT u.user_id,u.password, u.first_name, u.family_name, u.age, role.authority_name, gender.gender_name, u.authority_id, u.gender_id, u.admin "
					+ "FROM mst_user u, mst_role role,mst_gender gender where u.user_id LIKE ? and u.gender_id =gender.gender_id and u.authority_id = role.authority_id";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.setUserId(resultSet.getString("user_id"));
				user.setPassword(resultSet.getString("password"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setGenderId(resultSet.getInt("gender_id"));
				user.setAuthorityId(resultSet.getInt("authority_id"));
				user.setAge(resultSet.getInt("age"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setGenderName(resultSet.getString("gender_name"));
				user.setAuthorityName(resultSet.getString("authority_name"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
		return user;
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		try {
			String sql = "UPDATE mst_user SET password = ?, family_name = ?, first_name = ?, gender_id = ?,age = ?, admin = ?, "
					+ "update_user_id = ?, update_date = ?, authority_id = ? where user_id LIKE ?";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getFamilyName());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setInt(4, user.getGenderId());
			preparedStatement.setInt(5, user.getAge());
			preparedStatement.setInt(6, user.getAdmin());
			
			preparedStatement.setString(7, user.getUpdateUserID());
			
			preparedStatement.setLong(8, user.getUpdateDate());
			preparedStatement.setInt(9, user.getAuthorityId());
			preparedStatement.setString(10, user.getUserId());
			
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public boolean delete(String userid){
		boolean flag = false;
		try {
			String sql = "DELETE from mst_user where user_id LIKE ?";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 集計機能は役職に関係するデータを取ってからこのclassを設計しました。
	 * AggregateByAuthoritｙ｛
	 * 		String authority_id, authority_name;　　（＊）
	 * 		UserDTOFromAuthorityId　[];　//list user with specified authority_id
	 * 		年齢や性など関係したデータ；
	 * ｝
	 * mst_roleテーブルから全てのauthority_idを取ってからAggregateByAuthoritｙのauthority_idに保存
	 * */
	@Override
	public List<AggregateByAuthority> listAggregateByAuthority() {
		List<AggregateByAuthority> aggregateByAuthoritys = null;
		AggregateByAuthority aggregateByAuthority = null;
		
		try {
			aggregateByAuthoritys = new ArrayList<AggregateByAuthority>();
			String sql = "SELECT authority_id, authority_name from mst_role";
			connection = DBConnectionUtil.openConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				aggregateByAuthority = new AggregateByAuthority();
				
				aggregateByAuthority.setAuthorityId(resultSet.getInt("authority_id"));
				aggregateByAuthority.setAuthorityName(resultSet.getString("authority_name"));
				
				aggregateByAuthoritys.add(aggregateByAuthority);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return aggregateByAuthoritys;
		
	}
	
	/**
	 * 集計機能は役職に関係するデータを取ってからこのclassを設計しました。
	 * AggregateByAuthoritｙ｛
	 * 		String authority_id;
	 * 		UserDTOFromAuthorityId　[];　//list user with specified authority_id　（＊）
	 * 		年齢や性など関係したデータ；
	 * ｝
	 * mst_userテーブルから取ったauthority_idを基づいて全てのuserをAggregateByAuthoritｙのUserDTOFromAuthorityIdに保存する。
	 */
	
	@Override
	public void clusteringUserDtoByAuthorityId(List<AggregateByAuthority> list) {
		List<UserDTOFromAuthorityId> listUserDTOFromAuthorityId = null;
		UserDTOFromAuthorityId userDTOFromAuthorityId = null;
		
		for (AggregateByAuthority i : list) {
			try {
				listUserDTOFromAuthorityId = new ArrayList<UserDTOFromAuthorityId>();
				String sql = "SELECT user_id, gender_id, age from mst_user where authority_id = ?";
				connection = DBConnectionUtil.openConnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, i.getAuthorityId());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					userDTOFromAuthorityId = new UserDTOFromAuthorityId();
					
					userDTOFromAuthorityId.setUserId(resultSet.getString("user_id"));
					userDTOFromAuthorityId.setGenderId(resultSet.getInt("gender_id"));
					userDTOFromAuthorityId.setAge(resultSet.getInt("age"));
					
					listUserDTOFromAuthorityId.add(userDTOFromAuthorityId);
				}
				i.setListUserDTOFromAuthorityId(listUserDTOFromAuthorityId);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 集計機能は役職に関係するデータを取ってからこのclassを設計しました。
	 * AggregateByAuthoritｙ｛
	 * 		String authority_id;
	 * 		UserDTOFromAuthorityId　[];　//list user with specified authority_id　
	 * 		年齢や性など関係したデータ； （＊）
	 * ｝
	 * 年齢や性を数える
	 */
	@Override
	public void clusteringComplete(List<AggregateByAuthority> list) {
		
		for (AggregateByAuthority i : list) {
			i.init();
			if(!i.getListUserDTOFromAuthorityId().isEmpty()) {
				for(UserDTOFromAuthorityId j : i.getListUserDTOFromAuthorityId()) {
					if(j.getGenderId() == IS_MAN)
						i.increaseMan();
					else if(j.getGenderId() == IS_WOMEN)
						i.increaseWoMen();
					else
						i.increaseGenderNone();
					
					int age = j.getAge();
					if(age > ZERO_YEARS_OLD && age <= NINETEEN_YEARS_OLD )
						i.increaseAgeBeetweenZeroToNineTeen();
					else if(age >= TWENTY_YEARS_OLD)
						i.increaseAgeMoreThanTwenty();
					else
						i.increaseAgeNone();
				}
			}
		}
		
	}
	
	//search with a param
	@Override
	public List<UserDTO> search(String column, String key) {
		List<UserDTO> results = null;
		UserDTO user = null;
		try {
			results = new ArrayList<UserDTO>();
			String sql = "SELECT user_id, admin, first_name, family_name, age, authority_name, gender_name FROM mst_user, mst_role,mst_gender where "+ column+ " LIKE ? and mst_user.gender_id =mst_gender.gender_id and mst_user.authority_id = mst_role.authority_id";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			//preparedStatement.setString(2, key);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new UserDTO();
				
				user.setUserId(resultSet.getString("user_id"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setAge(resultSet.getInt("age"));
				user.setAuthorityName(resultSet.getString("authority_name"));
				user.setGenderName(resultSet.getString("gender_name"));
			
				results.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	//search with 3 param
	@Override
	public List<UserDTO> search(String column1, String column2, String column3, String key1, String key2, String key3) {
		List<UserDTO> results = null;
		UserDTO user = null;
		try {
			results = new ArrayList<UserDTO>();
			String sql = "SELECT user_id, admin, first_name, family_name, age, authority_name, gender_name FROM mst_user, mst_role,mst_gender where "+ column1+ " LIKE ? and " +column2+" LIKE ? and "+column3+" LIKE ? and mst_user.gender_id =mst_gender.gender_id and mst_user.authority_id = mst_role.authority_id";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, key1);
			preparedStatement.setString(2, key2);
			preparedStatement.setString(3, key3);
			//preparedStatement.setString(2, key);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new UserDTO();
				
				user.setUserId(resultSet.getString("user_id"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setAge(resultSet.getInt("age"));
				user.setAuthorityName(resultSet.getString("authority_name"));
				user.setGenderName(resultSet.getString("gender_name"));
			
				results.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	//search with 2 param
	@Override
	public List<UserDTO> search(String column1, String column2, String key1, String key2) {
		List<UserDTO> results = null;
		UserDTO user = null;
		try {
			results = new ArrayList<UserDTO>();
			String sql = "SELECT user_id, admin, first_name, family_name, age, authority_name, gender_name FROM mst_user, mst_role,mst_gender where "+ column1+ " LIKE ? and " +column2+" LIKE ? and mst_user.gender_id =mst_gender.gender_id and mst_user.authority_id = mst_role.authority_id";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, key1);
			preparedStatement.setString(2, key2);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new UserDTO();
				
				user.setUserId(resultSet.getString("user_id"));
				user.setAdmin(resultSet.getInt("admin"));
				user.setFamilyName(resultSet.getString("family_name"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setAge(resultSet.getInt("age"));
				user.setAuthorityName(resultSet.getString("authority_name"));
				user.setGenderName(resultSet.getString("gender_name"));
			
				results.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
}
