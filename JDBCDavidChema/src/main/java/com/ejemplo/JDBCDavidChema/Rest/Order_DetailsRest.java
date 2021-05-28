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
import com.ejemplo.JDBCDavidChema.Entities.Order_Details;
import com.ejemplo.JDBCDavidChema.Models.Orders_DetailsModel;

@Path("detallesPedidos")
public class Order_DetailsRest {
	static Orders_DetailsModel orders_details;
	
	public Order_DetailsRest() {
		try {
			orders_details = new Orders_DetailsModel();
		} catch (SQLException e) {
			System.err.println("No puedo abrir la conexión con 'Order_Details' " + e.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam("filter") String filter,
						 @QueryParam("limit") Integer limit,
						 @QueryParam("offset") Integer offset) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
		
		if(orders_details != null) {
			ArrayList<Order_Details> listarDetallePedido = orders_details.lista(filter, limit, offset);
			if(listarDetallePedido != null)
				respuesta = Response.status(Response.Status.OK).entity(listarDetallePedido).build();
		}
		
		return respuesta;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") Integer id) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No encontrado").build();
		
		if(orders_details != null) {
			Order_Details detallePedidos = orders_details.read(id);
			if(detallePedidos != null)
				respuesta = Response.status(Response.Status.OK).entity(detallePedidos).build();
		}
		
		return respuesta;
	}
	
	@GET
	@Path("/pedido/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@PathParam("id") Integer id) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("NO encontrado").build();
		
		if (orders_details != null) {
			ArrayList<Order_Details> listarDetallePedido = orders_details.lista("order_id = "+id);
			if (listarDetallePedido != null) {
				respuesta = Response.status(Response.Status.OK).entity(listarDetallePedido).build();
			}
		}
		return respuesta;
	}
	
	@GET
	@Path("/pedido")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("NO encontrado").build();
		
		if (orders_details != null) {
			ArrayList<Order_Details> listarDetallePedido = orders_details.lista(null);
			if (listarDetallePedido != null) {
				respuesta = Response.status(Response.Status.OK).entity(listarDetallePedido).build();
			}
		}
		return respuesta;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Order_Details detallePedido) {
		Integer idDetallePedido;
		Response respuesta;
		
		try {
			idDetallePedido = orders_details.insert(detallePedido);
			respuesta = Response.status(Response.Status.CREATED).entity(idDetallePedido).build();
		} catch (Exception e) {
			respuesta = Response.status(Response.Status.CONFLICT).encoding("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		
		return respuesta;
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Order_Details detallePedido) {
		Boolean detallePedidoActualizado;
		Response respuesta;
		
		try {
			detallePedidoActualizado = orders_details.update(detallePedido);
			respuesta = Response.status(Response.Status.OK).entity(detallePedidoActualizado).build();
		} catch (Exception e) {
			respuesta = Response.status(Response.Status.NOT_MODIFIED).encoding("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		
		return respuesta;
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Boolean detallePedidoActualizado;
		Response respuesta;

		try {
			detallePedidoActualizado = orders_details.delete(id);
			respuesta = Response.status(Response.Status.OK).entity(detallePedidoActualizado).build();
		} catch (Exception e) {
			respuesta = Response.status(Response.Status.NOT_FOUND).encoding("ERROR: " + e.getCause() + " " + e.getMessage()).build();
		}
		
		return respuesta;
	}
	
}
