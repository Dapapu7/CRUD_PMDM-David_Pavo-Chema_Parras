package com.ejemplo.JDBCDavidChema;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejemplo.JDBCDavidChema.Entities.Order;
import com.ejemplo.JDBCDavidChema.Models.OrdersModel;

public class AppPedidos {

	public static void main(String[] args) {
		
		try {
			Order pedido;
			OrdersModel misPedidos = new OrdersModel();  //Creamos un objeto de OrdersModel
		
			System.out.println("Conexion a la BBDD con éxito");
			
//			Esto es el read de pedidos {
			pedido = misPedidos.read(30);  //Leemos el pedido con id = 30
			if(pedido != null)  //Si el pedido es distinto de null
				System.out.println(pedido.toString());  //Convertimos el pedido a String
//			}
			
//			Esto es el insert de los pedidos {
			Integer id = misPedidos.insert(pedido);  //Usamos la funcion insert para insertar pedido
			System.out.println("Acabo de insertar el pedido: "+ id);
//			}
			
//			Esto es el update de los pedidos {
			pedido = misPedidos.read(id);  //Leemos el pedido mediante su id
			pedido.setPayment_type("hola"); //Acutalizamos el valor de payment_type a hola
			
			if(misPedidos.update(pedido)) {
                System.out.println("Pedido actualizado correctamente, veamos si es verdad: ");
                pedido = misPedidos.read(id);
                System.out.println(pedido.toString());
            }
            else
                System.err.println("No he podido leer el pedido");
//			}
			
//			Esto es el delete de los pedidos {
			boolean idpedido = misPedidos.delete(86);
			System.out.println("Acabo de borrar un pedido");
			
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
