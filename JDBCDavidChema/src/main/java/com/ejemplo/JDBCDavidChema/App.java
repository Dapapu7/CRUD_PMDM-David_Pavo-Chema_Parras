package com.ejemplo.JDBCDavidChema;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejemplo.JDBCDavidChema.Entities.Order;
import com.ejemplo.JDBCDavidChema.Models.OrdersModel;

public class App {

	public static void main(String[] args) {
		
		try {
			Order pedido;
			OrdersModel misPedidos = new OrdersModel();  //Creamos un objeto
		
			System.out.println("Conexion a la BBDD con éxito");
			
//			Esto es el read de pedidos {
			pedido = misPedidos.read(30);  //Leemos el pedido con id = 30
			if(pedido != null)  //Si el pedido es distinto de null
				System.out.println(pedido.toString());  //Convertimos el pedido a String
//			}
			
			Integer id = misPedidos.insert(pedido);
			System.out.println("Acabo de insertar el pedido: "+ id);
			
			//Ahora leo el cliente recien insertado y le cambio la compañia
			pedido = misPedidos.read(id);
			pedido.setPayment_type("hola");
			
			System.out.println();
			System.out.println();
			System.out.println("Ahora vamos a leer el cliente");
			
			ArrayList<Order> pedidos = misPedidos.lista("id>40", 10, 2);
			listaPedidos(pedidos);
			
		} catch(SQLException e) {
			System.err.println("No se ha podido conectar a la BBDD " + e.getMessage());
			System.exit(1);
		}
	}
	
	static void listaPedidos(ArrayList<Order> pedidos) {
    	if(pedidos == null) {
    		System.out.println("No hay elementos en la lista");
    		return;
    	}
 
		for(Order pedido : pedidos) {
			System.out.println(pedido.toString());
		}
	}

}
