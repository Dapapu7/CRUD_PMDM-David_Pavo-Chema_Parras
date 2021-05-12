package com.ejemplo.JDBCDavidChema;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejemplo.JDBCDavidChema.Entities.Order_Details;
import com.ejemplo.JDBCDavidChema.Models.Orders_DetailsModel;

public class AppOrders_Details {

	public static void main(String[] args) {
		
		try {
			Order_Details detallesPedidos;
			Orders_DetailsModel misDetallesPedidos = new Orders_DetailsModel();  //Creamos un objeto de OrdersModel
		
			System.out.println("Conexion a la BBDD con éxito");
			
//			Esto es el read de pedidos {
			detallesPedidos = misDetallesPedidos.read(45);  //Leemos el pedido con id = 30
			if(detallesPedidos != null)  //Si el pedido es distinto de null
				System.out.println(detallesPedidos.toString());  //Convertimos el pedido a String
//			}
			
//			Esto es el insert de los pedidos {
			Integer id = misDetallesPedidos.insert(detallesPedidos);  //Usamos la funcion insert para insertar pedido
			System.out.println("Acabo de insertar el pedido: "+ id);
//			}
			
//			Esto es el update de los pedidos {
			detallesPedidos = misDetallesPedidos.read(id);  //Leemos el pedido mediante su id
			detallesPedidos.setDiscount(100.3); //Acutalizamos el valor de payment_type a hola
			
			if(misDetallesPedidos.update(detallesPedidos)) {
                System.out.println("Pedido actualizado correctamente, veamos si es verdad: ");
                detallesPedidos = misDetallesPedidos.read(id);
                System.out.println(detallesPedidos.toString());
            }
            else
                System.err.println("No he podido leer el pedido");
//			}

//			Esto es el delete de los pedidos {
			boolean idpedido = misDetallesPedidos.delete(95);
			System.out.println("Acabo de borrar un pedido");
//			}

			System.out.println();
			System.out.println();
			
			System.out.println("Ahora vamos a leer el cliente");
			
			ArrayList<Order_Details> detallePedido = misDetallesPedidos.lista("id>40", 10, 2);
			listaDetallesPedidos(detallePedido);
			
		} catch(SQLException e) {
			System.err.println("No se ha podido conectar a la BBDD " + e.getMessage());
			System.exit(1);
		}

	}
	
	static void listaDetallesPedidos(ArrayList<Order_Details> detallePedido) {
    	if(detallePedido == null) {
    		System.out.println("No hay elementos en la lista");
    		return;
    	}
 
		for(Order_Details detallesPedidos : detallePedido) {
			System.out.println(detallesPedidos.toString());
		}
	}

}
