package com.qa.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.qa.model.User;
import com.qa.repository.UserRepository;

@Path("/")
public class UserEndpoint {
	@Inject
	private UserRepository userRepository;

	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOne(@PathParam("id") int id) {
		if (userRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		User user = userRepository.read(id);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<User> list = userRepository.readAll();
		if (list.size() == 0) {
			return Response.noContent().build();
		}
		return Response.ok(list).build();
	}
	
	@POST
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/users")
	public Response addUser(User userRS, @Context UriInfo uriInfo) {
		userRS = userRepository.create(userRS);
		URI createdURI = uriInfo.getBaseUriBuilder().path(""+userRS.getId()).build();
		System.out.println(createdURI);
		return Response.ok(createdURI.toString()).status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({"application/json"})
	public Response updateUser(User user, @PathParam("id") int id) {
		if (userRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		User userRS2 = userRepository.update(id, user);
		return Response.ok(userRS2).build();
	}
	
	@DELETE
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		if (userRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		userRepository.delete(id);
		return Response.noContent().build();
	}
	
}
