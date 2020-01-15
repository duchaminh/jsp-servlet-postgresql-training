package com.hust.restapi;

import java.util.ArrayList;
import java.util.List;


import com.hust.dao.AggregateDAO;
import com.hust.dao.AggregateDAOImpl;
import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.dto.AggregateByAuthority;
import com.hust.dto.UserDTO;
import com.hust.dto.UserDTOEdit;
import com.hust.helper.JwTokenHelper;
import com.hust.util.ParamWithValue;

import model.User;
import model.UserForAPI;

public class UserRepository {
	UserDAO userDAO = null;
	public UserRepository() {
        userDAO = new UserDAOImpl();
    }
	
	public List<UserDTO> getUsers(){
		return userDAO.get();
	}
	
	public UserDTOEdit get(String id) {
		return userDAO.get(id);
	}
	
	public boolean delete(String id) {
		if(get(id) != null)
			return userDAO.delete(id);
		return false;
	}
	
	public boolean update(User user) {
		if(get(user.getUserId()) != null)
			return userDAO.update(user);
		return false;
	}
	
	public boolean createUser(User user) {
		return userDAO.save(user);
	}
	public boolean isOverlap(String id) {
		return userDAO.isOverLapUserId(id);
	}
	public List<AggregateByAuthority> getShuukei(){
		AggregateDAO aggregateDAO = new AggregateDAOImpl();
		return aggregateDAO.aggregateFor("authority_id");
	}
	
	public List<UserDTO> search(String familyName, String firstName, String authorityName){
		if(familyName.isEmpty() && firstName.isEmpty() && authorityName.isEmpty()) {
			  return null;
		  }
		else {
			List<UserDTO> results = null; 
			List<ParamWithValue> listColumn = new ArrayList<ParamWithValue>();
			
			listColumn.add(new ParamWithValue("family_name", familyName));
			listColumn.add(new ParamWithValue("first_name", firstName));
			listColumn.add(new ParamWithValue("authority_name", authorityName));
			  
			results = userDAO.search(listColumn);
			if(results.isEmpty())
				return null;
			return results;
		}
	}

	public String getCurrentUser(String token) {
		// TODO Auto-generated method stub
		return JwTokenHelper.getUserFromToken(token).getUsername();
	}
}
