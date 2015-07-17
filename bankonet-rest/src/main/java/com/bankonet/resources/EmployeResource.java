package com.bankonet.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bankonet.model.Employe;
import com.bankonet.service.EmployeService;

@Path("/employes")
public class EmployeResource {
	
	@EJB EmployeService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployes(){
		List<Employe> employes = service.findAll();
		return Response.ok(employes).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getEmployeById(@PathParam("id") Integer id){
		Employe employe = service.findById(id);
		if(employe != null){
			//return Response.ok(employe).header("Access-Control-Allow-Origin", "*").build();
			// plus besoin vu qu'on a créé le cors filter
			return Response.ok(employe).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postEmploye(@FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("fonction") String fonction){
		if((nom == null) || (prenom == null) || (fonction == null)){
			return Response.status(400).build();
		} else {
			Employe employe = service.createEmploye(nom, prenom, fonction);
			return Response.status(201).entity(employe).build();
		}
	}	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmploye(@FormParam("id") int id, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("fonction") String fonction){
		Employe employe = service.findById(id);
		if((employe == null) || (nom == null) || (prenom == null) || (fonction == null)){
			return Response.status(400).build();
		} else {
			employe.setNom(nom);
			employe.setPrenom(prenom);
			employe.setFonction(fonction);
			service.updateEmploye(employe);
			return Response.status(200).entity(employe).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteEmploye(@PathParam("id") int id){
		Employe employe = service.findById(id);
		if(employe == null){
			return Response.status(400).build();
		} else {
			service.deleteEmploye(id);
			return Response.status(200).build();
		}
	}
}
