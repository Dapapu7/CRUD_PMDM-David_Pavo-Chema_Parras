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
import com.ejemplo.JDBCDavidChema.Entities.Product;
import com.ejemplo.JDBCDavidChema.Models.ProductsModel;


@Path("productos")
public class ProductRest {
	static ProductsModel products;

    public ProductRest() {
    	
	try {
	    products = new ProductsModel();
	} catch (SQLException e) {
	    System.err.println("No puedo abrir la conexion con 'Products': " + e.getMessage());
	}
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("filter") String filter,
    					 @QueryParam("limit") Integer limit,
    					 @QueryParam("offset") Integer offset) {
    	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
    	
    	if(products != null) {
    		ArrayList<Product> listaProductos = products.lista(filter, limit, offset);
    		if(listaProductos != null)
    			respuesta = Response.status(Response.Status.OK).entity(listaProductos).build();
    	}
    	
    	return respuesta;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
    	
    	Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
    	
    	if (products != null) {
    	    Product producto = products.read(id);
    	    if (producto != null) {
    		respuesta = Response.status(Response.Status.OK).entity(producto).build();
    	    }
    	}
    	return respuesta;
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product producto) {
	Integer idProducto;
	Response respuesta;
	try {
		idProducto = products.insert(producto);
		respuesta = Response.status(Response.Status.CREATED).entity(idProducto).build();
	} catch (Exception e) {
		respuesta = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage()).build();
	}
	return respuesta;
    }
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Product producto) {
	Boolean productoActualizado;
	Response respuesta;
	try {
		productoActualizado = products.update(producto);
	    respuesta = Response.status(Response.Status.OK).entity(productoActualizado).build();
	} catch (Exception e) {
		respuesta = Response.status(Response.Status.NOT_MODIFIED).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return respuesta;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
	Boolean productoActualizado;
	Response respuesta;
	try {
		productoActualizado = products.delete(id);
	    respuesta = Response.status(Response.Status.OK).entity(productoActualizado).build();
	} catch (Exception e) {
	    respuesta = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return respuesta;
    }
}
