package com.ejemplo.JDBCDavidChema.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.ejemplo.JDBCDavidChema.DBFactory.DBFactory;
import com.ejemplo.JDBCDavidChema.Entities.Order;

public class OrdersModel {

	Connection conexion = null; // Creamos la conexion y la iniciamos a null

	public OrdersModel() throws SQLException {
		DataSource ds = DBFactory.getMySQLDataSource(); // Crea un DataSource llamado ds y coge los datos de nuestra BBDD de la funcion getMySQLDataSource()
		conexion = ds.getConnection(); // Lo que hace getConnection() es para entablar una conexión con la BBDD
	}

	public Order read(Integer id) {
		Order pedido = null;
		Statement statement = null;

		String sql = "SELECT `id`, "
				+ "`employee_id`, "
				+ "`customer_id`, "
				+ "`order_date`, "
				+ "`shipped_date`, "
				+ "`shipper_id`, "
				+ "`ship_name`, "
				+ "`ship_address`, "
				+ "`ship_city`, "
				+ "`ship_state_province`, "
				+ "`ship_zip_postal_code`, "
				+ "`ship_country_region`, "
				+ "`shipping_fee`, "
				+ "`taxes`, "
				+ "`payment_type`, "
				+ "`paid_date`, "
				+ "`notes`, "
				+ "`tax_rate`, "
				+ "`tax_status_id`, "
				+ "`status_id`"
				+ " FROM orders WHERE " + id;

		try {
			statement = conexion.createStatement();  //Creamos la sentencia
			ResultSet myResultSet = statement.executeQuery(sql);  //Ejecutamos la sentencia y la guardamos en forma de "tabla"
			while (myResultSet.next()) {
					myResultSet.getInt("id");
					myResultSet.getInt("employee_id");
					myResultSet.getInt("customer_id");
					myResultSet.getDate("order_date");
					myResultSet.getDate("shipped_date");
					myResultSet.getInt("shipper_id");
					myResultSet.getString("ship_name");
					myResultSet.getString("ship_address");
					myResultSet.getString("ship_city");
					myResultSet.getString("ship_state_province");
					myResultSet.getString("ship_zip_postal_code");
					myResultSet.getString("ship_country_region");
					myResultSet.getBigDecimal("shipping_fee");
					myResultSet.getBigDecimal("taxes");
					myResultSet.getString("payment_type");
					myResultSet.getDate("paid_date");
					myResultSet.getString("notes");
					myResultSet.getDouble("tax_rate");
					myResultSet.getBoolean("tax_status_id");
					myResultSet.getBoolean("status_id");
			}
		} catch (SQLException e) {
			System.err.println("Error en read ORDERS " + e.getMessage());
			return null;
		}
		
		return pedido;
	}

	public Integer insert(Order pedido) {
		Integer id = null;

		return id;
	}

	public Boolean update(Order pedido) {
		Boolean resultado = false;

		return resultado;
	}

	public Boolean delete(Order pedido) {
		Boolean resultado = false;

		return resultado;
	}
}