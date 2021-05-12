package com.ejemplo.JDBCDavidChema.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.JDBCDavidChema.DBFactory.DBFactory;
import com.ejemplo.JDBCDavidChema.Entities.Product;

public class ProductsModel {

	Connection conexion = null;
	
	public ProductsModel() throws SQLException {
		DataSource ds = DBFactory.getMySQLDataSource();
		conexion = ds.getConnection();
	}
	
	
	public Product read(Integer id) {
		Product producto = null;
		Statement statement = null;
		
		String sql = "SELECT "
				+ "`supplier_ids`, "
				+ "`id`, "
				+ "`product_code`, "
				+ "`product_name`, "
				+ "`description`, "
				+ "`standard_cost`, "
				+ "`list_price`, "
				+ "`reorder_level`, "
				+ "`target_level`, "
				+ "`quantity_per_unit`, "
				+ "`discontinued`, "
				+ "`minimum_reorder_quantity`, "
				+ "`category`, "
				+ "`attachments` "
				+ "FROM products "
				+ "WHERE id=" + id;
		
		try {
			
			statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(sql);
			
			while(resultado.next()) {
				producto = new Product(
					resultado.getString("supplier_ids"),
					resultado.getInt("id"),
					resultado.getString("product_code"),
					resultado.getString("product_name"),
					resultado.getString("description"),
					resultado.getBigDecimal("standard_cost"),
					resultado.getBigDecimal("list_price"),
					resultado.getInt("reorder_level"),
					resultado.getInt("target_level"),
					resultado.getString("quantity_per_unit"),
					resultado.getBoolean("discontinued"),
					resultado.getInt("minimum_reorder_quantity"),
					resultado.getString("category"),
					resultado.getBlob("attachments")
				);
				
			}
			
		} catch (SQLException e) {
			System.err.println("Error al leer los productos " + e.getMessage());
			return null;
		}
		
		return producto;
	}
	
	public Integer insert(Product producto) throws SQLException {
		Integer id = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO products ("
				+ "`supplier_ids`, "
				+ "`product_code`, "
				+ "`product_name`, "
				+ "`description`, "
				+ "`standard_cost`, "
				+ "`list_price`, "
				+ "`reorder_level`, "
				+ "`target_level`, "
				+ "`quantity_per_unit`, "
				+ "`discontinued`, "
				+ "`minimum_reorder_quantity`, "
				+ "`category`, "
				+ "`attachments`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, producto.getSupplier_ids());
			ps.setString(2, producto.getProduct_code());
			ps.setString(3, producto.getProduct_name());
			ps.setString(4, producto.getDescription());
			ps.setBigDecimal(5, producto.getStandard_cost());
			ps.setBigDecimal(6, producto.getList_price());
			ps.setInt(7, producto.getReorder_level());
			ps.setInt(8, producto.getTarget_level());
			ps.setString(9, producto.getQuantity_per_unit());
			ps.setBoolean(10, producto.getDiscontinued());
			ps.setInt(11, producto.getMinimum_reorder_quantity());
			ps.setString(12, producto.getCategory());
			ps.setBlob(13, producto.getAttachments());
			
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
				    id = rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			System.err.println("Error al insertar producto: " + e.getMessage());
			throw e;
		}
		
		return id;
	}
	
	
	public Boolean update(Product producto ) {
		Boolean resultado = false;
		PreparedStatement ps = null;
		
		String sql = "UPDATE products SET "
				+ "supplier_ids = ?, "
				+ "product_code = ?, "
				+ "product_name = ?, "
				+ "description = ?, "
				+ "standard_cost = ?, "
				+ "list_price = ?, "
				+ "reorder_level = ?, "
				+ "target_level = ?, "
				+ "quantity_per_unit = ?, "
				+ "discontinued = ?, "
				+ "minimum_reorder_quantity = ?, "
				+ "category = ?, "
				+ "attachments = ? "
				+ "WHERE id = ?";
		
		try {
			
			ps = conexion.prepareStatement(sql);
			ps.setString(1, producto.getSupplier_ids());
			ps.setString(2, producto.getProduct_code());
			ps.setString(3, producto.getProduct_name());
			ps.setString(4, producto.getDescription());
			ps.setBigDecimal(5, producto.getStandard_cost());
			ps.setBigDecimal(6, producto.getList_price());
			ps.setInt(7, producto.getReorder_level());
			ps.setInt(8, producto.getTarget_level());
			ps.setString(9, producto.getQuantity_per_unit());
			ps.setBoolean(10, producto.getDiscontinued());
			ps.setInt(11, producto.getMinimum_reorder_quantity());
			ps.setString(12, producto.getCategory());
			ps.setBlob(13, producto.getAttachments());
			ps.setInt(14, producto.getId());
			
			resultado = (ps.executeUpdate() > 0);
			
		} catch(SQLException e) {
			System.err.println("Error al actualizar un producto: " + e.getMessage());
		}
		
		return resultado;
	}
	
	
	public Boolean delete(Integer idProducto) {
		Boolean resultado = false;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM products WHERE id=?";
		
		try {
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, idProducto);
			
			resultado = (ps.executeUpdate() > 0);
			
		} catch(SQLException e) {
			System.out.println("Error al borrar producto: " + e.getMessage());
		}
		
		return resultado;
	}
	
	
	public ArrayList<Product> lista(String filtro, Integer limite, Integer offset){
		ArrayList<Product> productos = new ArrayList<Product>();
		Statement statement = null;
		
		String sql = "SELECT "
				+ "`supplier_ids`, "
				+ "`id`, "
				+ "`product_code`, "
				+ "`product_name`, "
				+ "`description`, "
				+ "`standard_cost`, "
				+ "`list_price`, "
				+ "`reorder_level`, "
				+ "`target_level`, "
				+ "`quantity_per_unit`, "
				+ "`discontinued`, "
				+ "`minimum_reorder_quantity`, "
				+ "`category`, "
				+ "`attachments` "
				+ "FROM products ";
		
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
				productos.add(new Product(
					resultado.getString("supplier_ids"),
					resultado.getInt("id"),
					resultado.getString("product_code"),
					resultado.getString("product_name"),
					resultado.getString("description"),
					resultado.getBigDecimal("standard_cost"),
					resultado.getBigDecimal("list_price"),
					resultado.getInt("reorder_level"),
					resultado.getInt("target_level"),
					resultado.getString("quantity_per_unit"),
					resultado.getBoolean("discontinued"),
					resultado.getInt("minimum_reorder_quantity"),
					resultado.getString("category"),
					resultado.getBlob("attachments")
				));
			}
			
		} catch(SQLException e) {
			System.err.println("Error al listar productos: " + e.getMessage());
		}
		
		return productos;
	}
}
