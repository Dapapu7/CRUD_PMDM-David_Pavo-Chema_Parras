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

import com.ejemplo.JDBCDavidChema.Entities.Order;
import com.ejemplo.JDBCDavidChema.Models.OrdersModel;

@Path("pedidos")
public class OrderRest {
	static OrdersModel orders;
	
	public OrderRest() {

		try {
			orders = new OrdersModel();
		} catch (SQLException e) {
			
			System.out.println("No puedo abrir la conexión con 'Orders': " + e.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam("filter") String filter,
						 @QueryParam("limit") Integer limit,
						 @QueryParam("offset") Integer offset) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	
		if (orders != null) {
			ArrayList<Order> listaPedidos = orders.lista(filter, limit, offset);
			if (listaPedidos != null) {
				respuesta = Response.status(Response.Status.OK).entity(listaPedidos).build();
			}
		}
		return respuesta;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") Integer id) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No encontrado").build();
		
		if (orders != null) {
			Order pedido = orders.read(id);
			respuesta = Response.status(Response.Status.OK).entity(pedido).build();
		}
		return respuesta;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Order pedido) {
		Integer idPedido;
		Response respuesta;
		
		try {
			idPedido = orders.insert(pedido);
			respuesta = Response.status(Response.Status.CREATED).entity(idPedido).build();
		} catch(Exception e) {
			respuesta = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		return respuesta;
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Order pedido) {
		Boolean pedidoActualizado;
		Response respuesta;
		
		try {
			pedidoActualizado = orders.update(pedido);
			respuesta = Response.status(Response.Status.OK).entity(pedidoActualizado).build();
		} catch(Exception e) {
			respuesta = Response.status(Response.Status.NOT_MODIFIED).entity("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		return respuesta;
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Boolean pedidoActualizado;
		Response respuesta;
		
		try {
			pedidoActualizado = orders.delete(id);
			respuesta = Response.status(Response.Status.OK).entity(pedidoActualizado).build();
		} catch(Exception e) {
			respuesta = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		return respuesta;
	}

}