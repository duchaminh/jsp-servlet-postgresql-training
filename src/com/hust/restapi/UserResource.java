package com.hust.restapi;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Role;
import model.User;

@RolesAllowed(Role.ROLE_CUSTOMER)
@Path("users")
public class UserResource {
	UserRepository repo = new UserRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(){
		return Response.status(Status.OK)
				       .entity(repo.getUsers())
				       .build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		return 	Response.status(Status.OK)
			       .entity(repo.get(id))
			       .build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		if(repo.delete(id)) {
			return 	Response.status(Status.OK)
				       .build();
		}
		return 	Response.status(Status.NOT_FOUND)
			       .build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, User user) {
		if(repo.update(user)) {
			return 	Response.status(Status.OK)
				       .build();
		}
		return 	Response.status(Status.NOT_FOUND)
			       .build();
	}
	
	@POST
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		if(repo.isOverlap(user.getUserId()))
			return Response.status(Status.CONFLICT)
					.entity(user)
					.build();
		if(repo.createUser(user))
			return Response.status(Status.CREATED)
				           .entity(user)
				           .build();
		return null;
	}
	
	@GET
	@Path("/shuukei")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShuukei() {
		return Response.status(Status.OK)
			       .entity(repo.getShuukei())
			       .build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam("familyName") String familyName, 
				@QueryParam("firstName") String firstName, @QueryParam("authorityName") String authorityName) {
		//System.out.println(familyName);
		//System.out.println(firstName);
		//System.out.println(authorityName);
		if(repo.search(familyName, firstName, authorityName) == null || repo.search(familyName, firstName, authorityName).isEmpty())
			return Response.status(Status.NO_CONTENT)
						   .build();
		return  Response.status(Status.OK)
			            .entity(repo.search(familyName, firstName, authorityName))
			            .build();
	}
	
	@GET
	@Path("current_user/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCurrentUser(@PathParam("token") String token) {
		//System.out.println(repo.getCurrentUser(token));
		return Response.status(Status.OK)
			       .entity(repo.getCurrentUser(token))
			       .build();
	}
	
}
