	package com.ejemplo.JDBCDavidChema.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.JDBCDavidChema.DBFactory.DBFactory;
import com.ejemplo.JDBCDavidChema.Entities.Order_Details;

public class Orders_DetailsModel {

	Connection conexion = null;
	
	public Orders_DetailsModel() throws SQLException {
		DataSource ds = DBFactory.getMySQLDataSource();
		conexion = ds.getConnection();
	}
	
	
	public Order_Details read(Integer id){
		Order_Details detallesPedidos = null;
		Statement statament = null;
		
		String sql = "SELECT "
				+ "`id`, "
				+ "`order_id`, "
				+ "`product_id`, "
				+ "`quantity`, "
				+ "`unit_price`, "
				+ "`discount`, "
				+ "`status_id`, "
				+ "`date_allocated`, "
				+ "`purchase_order_id`, "
				+ "`inventory_id` "
				+ "FROM order_details "
				+ "WHERE id=" + id;
		
		try {
			statament = conexion.createStatement();
			ResultSet resultado = statament.executeQuery(sql);
			
			while (resultado.next()) {
				detallesPedidos = new Order_Details(
					resultado.getInt("id"),
					resultado.getInt("order_id"),
					resultado.getInt("product_id"),
					resultado.getBigDecimal("quantity"),
					resultado.getBigDecimal("unit_price"),
					resultado.getDouble("discount"),
					resultado.getInt("status_id"),
					resultado.getDate("date_allocated"),
					resultado.getInt("purchase_order_id"),
					resultado.getInt("inventory_id")
				);
			}
				
		} catch (SQLException e) {
			System.err.println("Error al leer Detalle de Pedido: " + e.getMessage());
			return null;
		}
		
		return detallesPedidos;
	}
	
	
	public Integer insert(Order_Details detallesPedidos) {
		Integer id = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO order_details ("
				+ "`order_id`, "
				+ "`product_id`, "
				+ "`quantity`, "
				+ "`unit_price`, "
				+ "`discount`, "
				+ "`status_id`, "
				+ "`date_allocated`, "
				+ "`purchase_order_id`, "
				+ "`inventory_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, detallesPedidos.getOrder_id());
			ps.setInt(2, detallesPedidos.getProduct_id());
			ps.setBigDecimal(3, detallesPedidos.getQuantity());
			ps.setBigDecimal(4, detallesPedidos.getUnit_price());
			ps.setDouble(5, detallesPedidos.getDiscount());
			ps.setInt(6, detallesPedidos.getStatus_id());
			ps.setDate(7, detallesPedidos.getDate_allocated());
			ps.setInt(8, detallesPedidos.getPurchase_order_id());
			ps.setInt(9, detallesPedidos.getInventory_id());
			
			 if (ps.executeUpdate() > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
					    id = rs.getInt(1);
					}
			 }
		
		} catch(SQLException e) {
			System.err.println("Error al insertar Detalle de Pedido: " + e.getMessage());
		}
		
		return id;
	}
	
	
	public Boolean delete(Integer idDetallesPedido) {
		Boolean resultado = false;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM order_details where id = ?";
		
		try {
			
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, idDetallesPedido);
			
			resultado = (ps.executeUpdate() > 0);
			
		} catch (SQLException e) {
			System.out.println("Error al borrar un Detalle de Pedido " + e.getMessage());
		}
		
		return resultado;
	}
	
	
	public Boolean update(Order_Details detallesPedidos) throws SQLException {
		Boolean resultado = false;
		PreparedStatement ps = null;
		
		String sql = "UPDATE order_details SET "
				+ "order_id = ?, "
				+ "product_id = ?, "
				+ "quantity = ?, "
				+ "unit_price = ?, "
				+ "discount = ?, "
				+ "status_id = ?, "
				+ "date_allocated = ?, "
				+ "purchase_order_id = ?, "
				+ "inventory_id = ?  "
				+ "WHERE id = ?";
		
		try {
			
			ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, detallesPedidos.getOrder_id());
			ps.setInt(2, detallesPedidos.getProduct_id());
			ps.setBigDecimal(3, detallesPedidos.getQuantity());
			ps.setBigDecimal(4, detallesPedidos.getUnit_price());
			ps.setDouble(5, detallesPedidos.getDiscount());
			ps.setInt(6, detallesPedidos.getStatus_id());
			ps.setDate(7, detallesPedidos.getDate_allocated());
			ps.setInt(8, detallesPedidos.getPurchase_order_id());
			ps.setInt(9, detallesPedidos.getInventory_id());
			ps.setInt(10, detallesPedidos.getId());
			
			resultado = (ps.executeUpdate() > 0);
			
		} catch (SQLException e) {
			System.err.println("Error al actualizar Detalle de Pedido: " + e.getMessage());
			throw e;
		}
		return resultado;
	}
	
	
	public ArrayList<Order_Details> lista(String filtro, Integer limite, Integer offset) {
		
		ArrayList<Order_Details> detallesPedidos = new ArrayList<Order_Details>();
		Statement statement = null;
		
		String sql = "SELECT "
				+ "`id`, "
				+ "`order_id`, "
				+ "`product_id`, "
				+ "`quantity`, "
				+ "`unit_price`, "
				+ "`discount`, "
				+ "`status_id`, "
				+ "`date_allocated`, "
				+ "`purchase_order_id`, "
				+ "`inventory_id` "
				+ "FROM order_details ";
		
		try {
			
			if(filtro != null)
				sql += "WHERE " + filtro;
			if(limite != null)
				sql += " LIMIT " + limite;
			if(offset != null)
				sql += " OFFSET " + offset;
			
			statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(sql);
			
			while(resultado.next()) {
				detallesPedidos.add(new Order_Details(
					resultado.getInt("id"),
					resultado.getInt("order_id"),
					resultado.getInt("product_id"),
					resultado.getBigDecimal("quantity"),
					resultado.getBigDecimal("unit_price"),
					resultado.getDouble("discount"),
					resultado.getInt("status_id"),
					resultado.getDate("date_allocated"),
					resultado.getInt("purchase_order_id"),
					resultado.getInt("inventory_id")
				));
			}
			
		} catch(SQLException e) {
			System.err.println("Error al listar Detalle de Pedido: " + e.getMessage());
			return null;
		}
		
		return detallesPedidos;
	}
	
	
	public ArrayList<Order_Details> lista(String filtro) {
		ArrayList<Order_Details> detallesPedidos = new ArrayList<Order_Details>();
		Statement statement = null;
		
		String sql = "SELECT "
				+ "`order_id`, "
				+ "`product_id`, "
				+ "`product_code`, "
				+ "`product_name`, "
				+ "`unit_price`, "
				+ "`category`, "
				+ "`standard_cost` "
				+ "from order_details as o "
				+ "join products as p on  o.id=p.id";
		
		try {
			if(filtro != null)
				sql += " WHERE " + filtro;
			
			statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(sql);
		
			while(resultado.next()) {
				detallesPedidos.add(new Order_Details(
					resultado.getInt("order_id"),
					resultado.getInt("product_id"),
					resultado.getBigDecimal("unit_price"),
					resultado.getString("product_code"),
					resultado.getString("category"),
					resultado.getBigDecimal("standard_cost"),
					resultado.getString("product_name")
				));
			}
			
		} catch(SQLException e) {
			System.err.println("Error al listar Detalle de Pedido: " + e.getMessage());
			return null;
		}
		return detallesPedidos;
	}
}
