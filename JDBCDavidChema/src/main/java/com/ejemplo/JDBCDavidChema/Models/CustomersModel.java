package com.ejemplo.JDBCDavidChema.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.JDBCDavidChema.DBFactory.DBFactory;
import com.ejemplo.JDBCDavidChema.Entities.Customer;



public class CustomersModel {
    
    Connection conexion = null;

    public CustomersModel() throws SQLException {
	DataSource ds = DBFactory.getMySQLDataSource();
	conexion = ds.getConnection();
    }

    public Customer read(Integer id) {
	Customer cliente = null;
	Statement sentencia = null;

	String sql = "SELECT `id`, `company`, `last_name`, `first_name`, "
		+ "`email_address`, `job_title`, `business_phone` , `home_phone`,"
		+ "`mobile_phone`, `fax_number`, `address`, `city`, `state_province`, "
		+ "`zip_postal_code`, `country_region`, `web_page`, `notes`, `attachments` " 
		+ "FROM customers "
		+ "WHERE id = " + id;

	try {
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si hay un cliente que existe
		cliente = new Customer(
			rs.getInt("id"),
			rs.getString("company"),
			rs.getString("last_name"),
			rs.getString("first_name"),
			rs.getString("email_address"),
			rs.getString("job_title"),
			rs.getString("business_phone"),
			rs.getString("home_phone"),
			rs.getString("fax_number"),
			rs.getString("mobile_phone"),
			rs.getString("address"),
			rs.getString("city"),
			rs.getString("state_province"),
			rs.getString("zip_postal_code"),
			rs.getString("country_region"),
			rs.getString("web_page"),
			rs.getString("notes"),
			rs.getBlob("attachments"));
	    };
	    
	} catch (SQLException e) {
	    System.err.println("Error en read de Clientes: " + e.getMessage());
	    return null;
	}

	return cliente;
    }

    /**
     * 
     * @param cliente
     * @return Devuelve el id del registro recien insertado
     */
    public Integer insert(Customer cliente) throws  SQLException {
	Integer id = null;
	PreparedStatement ps = null;
	String sql = "INSERT INTO customers ( "
		+ "`company`, `last_name`, `first_name`, "
		+ "`email_address`, `job_title`, `business_phone` , `home_phone`,"
		+ "`mobile_phone`, `fax_number`, `address`, `city`, `state_province`, "
		+ "`zip_postal_code`, `country_region`, `web_page`, `notes`, `attachments`) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	try {
	    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1, cliente.getCompany());
	    ps.setString(2, cliente.getLastName());
	    ps.setString(3, cliente.getFirstName());
	    ps.setString(4, cliente.getEmailAddress());
	    ps.setString(5, cliente.getJobTitle());
	    ps.setString(6, cliente.getBusinessPhone());
	    ps.setString(7, cliente.getHomePhone());
	    ps.setString(8, cliente.getMobilePhone());
	    ps.setString(9, cliente.getFaxNumber());
	    ps.setString(10, cliente.getAddress());
	    ps.setString(11, cliente.getCity());
	    ps.setString(12, cliente.getStateProvince());
	    ps.setString(13, cliente.getZipPostalCode());
	    ps.setString(14, cliente.getCountryRegion());
	    ps.setString(15, cliente.getWebPage());
	    ps.setString(16, cliente.getNotes());
	    ps.setBlob(17, cliente.getAttachment());
	    if (ps.executeUpdate() > 0) {
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getInt(1);
		}
	    }

	} catch (SQLException e) {
	    System.err.println("Error al insertar Customer: " + e.getMessage());
	    throw e;
	}

	return id;
    }

    public Boolean delete(Integer idcliente) throws SQLException {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "DELETE FROM customers where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);

	    ps.setInt(1, idcliente);

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al borrar Cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public Boolean update(Customer cliente) throws SQLException  {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "UPDATE customers set "
		+ "company = ?, "
		+ "last_name = ?, "
		+ "first_name = ?, "
		+ "email_address = ?, "
		+ "job_title = ?, "
		+ "business_phone  = ?, "
		+ "home_phone = ?, "
		+ "mobile_phone = ?, "
		+ "fax_number = ?, "
		+ "address = ?, "
		+ "city = ?, "
		+ "state_province = ?, "
		+ "zip_postal_code = ?, "
		+ "country_region = ?, "
		+ "web_page = ?, "
		+ "notes = ?, "
		+ "attachments = ? "
		+ "where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);
	    ps.setString(1, cliente.getCompany());
	    ps.setString(2, cliente.getLastName());
	    ps.setString(3, cliente.getFirstName());
	    ps.setString(4, cliente.getEmailAddress());
	    ps.setString(5, cliente.getJobTitle());
	    ps.setString(6, cliente.getBusinessPhone());
	    ps.setString(7, cliente.getHomePhone());
	    ps.setString(8, cliente.getMobilePhone());
	    ps.setString(9, cliente.getFaxNumber());
	    ps.setString(10, cliente.getAddress());
	    ps.setString(11, cliente.getCity());
	    ps.setString(12, cliente.getStateProvince());
	    ps.setString(13, cliente.getZipPostalCode());
	    ps.setString(14, cliente.getCountryRegion());
	    ps.setString(15, cliente.getWebPage());
	    ps.setString(16, cliente.getNotes());
	    ps.setBlob(17, cliente.getAttachment());
	    ps.setInt(18, cliente.getId());

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al actualizar Cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public ArrayList<Customer> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<Customer> clientes = new ArrayList<Customer>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "
		+ "`company`, "
		+ "`last_name`, "
		+ "`first_name`, "
		+ "`email_address`, "
		+ "`job_title`, "
		+ "`business_phone` , "
		+ "`home_phone`,"
		+ "`mobile_phone`, "
		+ "`fax_number`, "
		+ "`address`, "
		+ "`city`, "
		+ "`state_province`, "
		+ "`zip_postal_code`, "
		+ "`country_region`, "
		+ "`web_page`, "
		+ "`notes`, "
		+ "`attachments` " 
		+ "FROM customers ";

	try {
	    if (filtro != null)
		sql += " WHERE " + filtro;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si todavía hay un cliente lo añado al array
		clientes.add(new Customer(
			rs.getInt("id"),
			rs.getString("company"),
			rs.getString("last_name"),
			rs.getString("first_name"),
			rs.getString("email_address"),
			rs.getString("job_title"),
			rs.getString("business_phone"),
			rs.getString("home_phone"),
			rs.getString("fax_number"),
			rs.getString("mobile_phone"),
			rs.getString("address"),
			rs.getString("city"),
			rs.getString("state_province"),
			rs.getString("zip_postal_code"),
			rs.getString("country_region"),
			rs.getString("web_page"),
			rs.getString("notes"),
			rs.getBlob("attachments")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de Clientes: " + e.getMessage());
	    return null;
	}

	return clientes;
    }

}
