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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.qa.model.PlayerCard;
import com.qa.repository.PlayerCardRepository;

@Path("/")
public class PlayerCardEndpoint {

	@Inject
	private PlayerCardRepository pCardRepository;

	@GET
	@Path("/playercard/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOne(@PathParam("id") int id) {
		if (pCardRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		PlayerCard pCard = pCardRepository.read(id);
		return Response.ok(pCard).build();
	}
	
	@GET
	@Path("/playercard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<PlayerCard> list = pCardRepository.readAll();
		if (list.size() == 0) {
			return Response.noContent().build();
		}
		return Response.ok(list).build();
	}
	
	@POST
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/playercard")
	public Response addPlayerCard(PlayerCard pCardRS, @Context UriInfo uriInfo) {
		pCardRS = pCardRepository.create(pCardRS);
		URI createdURI = uriInfo.getBaseUriBuilder().path(""+pCardRS.getId()).build();
		System.out.println(createdURI);
		return Response.ok(createdURI.toString()).status(Status.CREATED).build();
	}
	
	
	//Update?
	@PUT
	@Path("/playercard/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({"application/json"})
	public Response updatePlayerCard(PlayerCard pCard, @PathParam("id") int id) {
		if (pCardRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		PlayerCard userRS2 = pCardRepository.update(id, pCard);
		return Response.ok(userRS2).build();
	}
	
	@DELETE
	@Path("/playercard/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		if (pCardRepository.read(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		pCardRepository.delete(id);
		return Response.noContent().build();
	}
}
