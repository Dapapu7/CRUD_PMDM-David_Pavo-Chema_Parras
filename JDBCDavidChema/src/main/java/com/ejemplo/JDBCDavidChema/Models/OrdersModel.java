package com.ejemplo.JDBCDavidChema.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				+ " FROM orders WHERE id=" + id;

		try {
			statement = conexion.createStatement();  //Creamos la sentencia
			ResultSet myResultSet = statement.executeQuery(sql);  //Ejecutamos la sentencia y la guardamos en forma de "tabla"
			//Se recorre el resultSet
			while (myResultSet.next()) {
				pedido = new Order(
					myResultSet.getInt("id"),
					myResultSet.getInt("employee_id"),
					myResultSet.getInt("customer_id"),
					myResultSet.getDate("order_date"),
					myResultSet.getDate("shipped_date"),
					myResultSet.getInt("shipper_id"),
					myResultSet.getString("ship_name"),
					myResultSet.getString("ship_address"),
					myResultSet.getString("ship_city"),
					myResultSet.getString("ship_state_province"),
					myResultSet.getString("ship_zip_postal_code"),
					myResultSet.getString("ship_country_region"),
					myResultSet.getBigDecimal("shipping_fee"),
					myResultSet.getBigDecimal("taxes"),
					myResultSet.getString("payment_type"),
					myResultSet.getDate("paid_date"),
					myResultSet.getString("notes"),
					myResultSet.getDouble("tax_rate"),
					myResultSet.getBoolean("tax_status_id"),
					myResultSet.getBoolean("status_id")
				);
			}
		} catch (SQLException e) {
			System.err.println("Error en read ORDERS " + e.getMessage());
			return null;
		}
		
		return pedido;
	}

	public Integer insert(Order pedido) throws SQLException {
		Integer id = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO orders ("
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
				+ "`status_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, pedido.getEmployee_id());
			ps.setInt(2, pedido.getCustomer_id());
			ps.setDate(3, pedido.getOrder_date());
			ps.setDate(4, pedido.getShipped_date());
			ps.setInt(5, pedido.getShipper_id());
			ps.setString(6, pedido.getShip_name());
			ps.setDate(7, pedido.getShipped_date());
			ps.setString(8, pedido.getShip_city());
			ps.setString(9, pedido.getShip_state_province());
			ps.setString(10, pedido.getShip_zip_postal_code());
			ps.setString(11, pedido.getShip_country_region());
			ps.setBigDecimal(12, pedido.getShipping_fee());
			ps.setBigDecimal(13, pedido.getTaxes());
			ps.setString(14, pedido.getPayment_type());
			ps.setDate(15, pedido.getPaid_date());
			ps.setString(16, pedido.getNotes());
			ps.setDouble(17, pedido.getTax_rate());
			ps.setBoolean(18, pedido.getTax_status_id());
			ps.setBoolean(19, pedido.getStatus_id());
			
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) 
				    id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			
			System.err.println("Fallo al insertar pedido " + e.getMessage());
			throw e;
			
		}
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
	
	public ArrayList<Order> lista(String filtro, Integer limite, Integer offset){
		
		ArrayList<Order> pedido = new ArrayList<Order>();
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
				+ " FROM orders ";
		
		try {
			if(filtro != null)
				sql += "WHERE " + filtro;
			if(limite != null)
				sql += " LIMIT " + limite;
			if(offset != null)
				sql += " OFFSET " + offset;
			
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				pedido.add(new Order(
					rs.getInt("id"),
					rs.getInt("employee_id"),
					rs.getInt("customer_id"),
					rs.getDate("order_date"),
					rs.getDate("shipped_date"),
					rs.getInt("shipper_id"),
					rs.getString("ship_name"),
					rs.getString("ship_address"),
					rs.getString("ship_city"),
					rs.getString("ship_state_province"),
					rs.getString("ship_zip_postal_code"),
					rs.getString("ship_country_region"),
					rs.getBigDecimal("shipping_fee"),
					rs.getBigDecimal("taxes"),
					rs.getString("payment_type"),
					rs.getDate("paid_date"),
					rs.getString("notes"),
					rs.getDouble("tax_rate"),
					rs.getBoolean("tax_status_id"),
					rs.getBoolean("status_id")
				));
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error al listar pedido " + e.getMessage());
			return null;
		}
		return pedido;
	}
}