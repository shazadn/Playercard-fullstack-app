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

import com.qa.model.Character;
import com.qa.repository.CharacterRepository;

@Path("/")
public class CharacterEndpoint {
	
	@Inject
	private CharacterRepository charRepository;

	@GET
	@Path("/character/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOne(@PathParam("id") int id) {
		if (charRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		Character pCard = charRepository.read(id);
		return Response.ok(pCard).build();
	}
	
	@GET
	@Path("/character")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Character> list = charRepository.readAll();
		if (list.size() == 0) {
			return Response.noContent().build();
		}
		return Response.ok(list).build();
	}
	
	@POST
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/character")
	public Response addCharacter(Character pCardRS, @Context UriInfo uriInfo) {
		pCardRS = charRepository.create(pCardRS);
		URI createdURI = uriInfo.getBaseUriBuilder().path(""+pCardRS.getId()).build();
		System.out.println(createdURI);
		return Response.ok(createdURI.toString()).status(Status.CREATED).build();
	}
	
	
	//Update?
	@PUT
	@Path("/character/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({"application/json"})
	public Response updateCharacter(Character pCard, @PathParam("id") int id) {
		if (charRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		Character userRS2 = charRepository.update(id, pCard);
		return Response.ok(userRS2).build();
	}
	
	@DELETE
	@Path("/character/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		if (charRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		charRepository.delete(id);
		return Response.noContent().build();
	}

}
