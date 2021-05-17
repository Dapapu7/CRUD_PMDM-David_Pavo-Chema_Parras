package com.ejemplo.JDBCDavidChema.Rest;

import java.sql.SQLException;
import java.util.ArrayList;

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

import com.ejemplo.JDBCDavidChema.Entities.Customer;
import com.ejemplo.JDBCDavidChema.Models.CustomersModel;

@Path("clientes")
public class CustomerRest {
    static CustomersModel customers;

    public CustomerRest() {

	try {
	    customers = new CustomersModel();
	} catch (SQLException e) {
	    System.err.println("No puedo abrir la conexion con 'Customers': " + e.getMessage());
	}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("filter") String filter, 
	                 @QueryParam("limit") Integer limit, 
	                 @QueryParam("offset") Integer offset) {
	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	
	if (customers != null) {
	    ArrayList<Customer> listaClientes = customers.lista(filter, limit, offset);
	    if (listaClientes != null) {
		respuesta = Response.status(Response.Status.OK).entity(listaClientes).build();
	    }

	}
	return respuesta;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
	
	Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
	
	if (customers != null) {
	    Customer cliente = customers.read(id);
	    if (cliente != null) {
		respuesta = Response.status(Response.Status.OK).entity(cliente).build();
	    }
	}
	return respuesta;
    }
   

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Customer cliente) {
	Integer idcliente;
	Response response;
	try {
	    idcliente = customers.insert(cliente);
	    response = Response.status(Response.Status.CREATED).entity(idcliente).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage()).build();
	}
	return response;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Customer cliente) {
	Boolean clienteactualizado;
	Response response;
	try {
	    clienteactualizado = customers.update(cliente);
	    response = Response.status(Response.Status.OK).entity(clienteactualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_MODIFIED).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
	Boolean clienteactualizado;
	Response response;
	try {
	    clienteactualizado = customers.delete(id);
	    response = Response.status(Response.Status.OK).entity(clienteactualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }
    
}