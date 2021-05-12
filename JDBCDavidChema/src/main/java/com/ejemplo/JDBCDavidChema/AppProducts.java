package com.ejemplo.JDBCDavidChema;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejemplo.JDBCDavidChema.Entities.Product;
import com.ejemplo.JDBCDavidChema.Models.ProductsModel;

public class AppProducts {

	public static void main(String[] args) {
		
		try {
			Product producto;
			ProductsModel misProductos = new ProductsModel();  //Creamos un objeto de OrdersModel
		
			System.out.println("Conexion a la BBDD con éxito");
			
//			Esto es el read de pedidos {
			producto = misProductos.read(1);  //Leemos el pedido con id = 30
			if(producto != null)  //Si el pedido es distinto de null
				System.out.println(producto.toString());  //Convertimos el pedido a String
//			}
	
//			Esto es el insert de los pedidos {
			Integer id = misProductos.insert(producto);  //Usamos la funcion insert para insertar pedido
			System.out.println("Acabo de insertar el pedido: "+ id);
//			}

//			Esto es el update de los pedidos {
			producto = misProductos.read(id);  //Leemos el pedido mediante su id
			producto.setCategory("hola"); //Acutalizamos el valor de payment_type a hola
			
			if(misProductos.update(producto)) {
                System.out.println("Pedido actualizado correctamente, veamos si es verdad: ");
                producto = misProductos.read(id);
                System.out.println(producto.toString());
            }
            else
                System.err.println("No he podido leer el pedido");
//			}

//			Esto es el delete de los pedidos {
			boolean idpedido = misProductos.delete(100);
			System.out.println("Acabo de borrar un pedido");
	
			System.out.println();
			System.out.println();
			
			System.out.println("Ahora vamos a leer el cliente");
			
			ArrayList<Product> productos = misProductos.lista("id>40", 10, 2);
			listaProductos(productos);

		} catch(SQLException e) {
			System.err.println("No se ha podido conectar a la BBDD " + e.getMessage());
			System.exit(1);
		}
	}

	static void listaProductos(ArrayList<Product> productos) {
    	if(productos == null) {
    		System.out.println("No hay elementos en la lista");
    		return;
    	}
 
		for(Product producto : productos) {
			System.out.println(producto.toString());
		}
	}

}


