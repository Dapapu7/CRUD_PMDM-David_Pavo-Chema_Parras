package com.ejemplo.JDBCDavidChema.DBFactory;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBFactory {
    static final String JDBCHOST = "jdbc:mysql://localhost:3306/northwind?useSSL=false";
    static final String JDBCUSER = "northwind";
    static final String JDBCPASSWD = "northwind";

    public static DataSource getMySQLDataSource() {

	MysqlDataSource mysqlDS = null;

	mysqlDS = new MysqlDataSource();
	mysqlDS.setURL(JDBCHOST);
	mysqlDS.setUser(JDBCUSER);
	mysqlDS.setPassword(JDBCPASSWD);

	return mysqlDS;
    }

}
