package hieu.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLService {
	Connection conn = null;
	
	public Connection getConnect(String server, String database) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://" + server 
					+ ":1433;databaseName=" + database
					+ ";integratedSecurity=true;";
			conn = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
}
