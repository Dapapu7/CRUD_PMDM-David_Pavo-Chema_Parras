package com.ejemplo.JDBCDavidChema;

import java.sql.SQLException;

import com.ejemplo.JDBCDavidChema.Entities.Order;
import com.ejemplo.JDBCDavidChema.Models.OrdersModel;

public class App {

	public static void main(String[] args) {
		
		try {
			Order pedido;
			OrdersModel misPedidos = new OrdersModel();  //Creamos un objeto
		
			System.out.println("Conexion a la BBDD con éxito");
			
			pedido = misPedidos.read(4);  //Leemos el pedido con id = 11
			
			if(pedido != null)  //Si el pedido es distinto de null
				System.out.println(pedido.toString());  //Convertimos el pedido a String

		} catch(SQLException e) {
			System.err.println("No se ha podido conectar a la BBDD " + e.getMessage());
			System.exit(1);
		}
	}

}
