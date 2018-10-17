package hieu.service;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class GetConnect {
	static Connection conn = null;
	public static Connection getConnect(String strServer, String strDatabase) {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://" + strServer 
					+ ":1433;databaseName=" + strDatabase
					+ ";integratedSecurity=true;";
			conn = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.toString());
		} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: " + cE.toString());
		}
		return conn;
	}

}
