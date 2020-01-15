package com.hust.restapi;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.helper.JwTokenHelper;

import model.Role;
import model.UserForAPI;
 
@Path("/auth")
public class AuthService {
	
	UserDAO userDAO = null;
 
    public AuthService() {
		userDAO = new UserDAOImpl();
	}

	/**
     * Authenticating a user with their username/ password and issuing a token
     * 
     * @param username
     * @param password
     * @return JSON Web Token (JWT)
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
 
    	UserDAO userDAO = new UserDAOImpl();
        UserForAPI user = userDAO.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            return Response.status(Response.Status.FORBIDDEN) // 403 Forbidden
                    .entity("Wrong username or password") // the response entity
                    .build();
        }
        user.setRoles(Arrays.asList(Role.ROLE_CUSTOMER));
        // Issue a token for the user
        String token = JwTokenHelper.createJWT(user);
        
        // Return the token on the response
        return Response.ok(token).build();
    }
}
