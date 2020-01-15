package com.hust.restapi;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Role;

@RolesAllowed(Role.ROLE_CUSTOMER)
@Path("list_authority")
public class AuthorityResource {
	AuthorityRepository repo = new AuthorityRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListAuthority(){
		return Response.status(Status.OK)
				       .entity(repo.getListAuthority())
				       .build();
	}
	
	@GET
	@Path("authority_by_name/{authorityName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAuthorityByName(@PathParam("authorityName") String authorityName){
		return Response.status(Status.OK)
				       .entity(repo.getAuthorityByName(authorityName))
				       .build();
	}
	
}
