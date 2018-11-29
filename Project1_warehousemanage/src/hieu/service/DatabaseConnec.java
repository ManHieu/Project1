package hieu.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnec {
	public static Connection conn = null;
	private final static String user = "root";
	private final static String password = "2708";
	private final static String db = "dbqlkh";
	
	public static Connection getConnect() {
		
		if(conn == null) {
			try {
//				Class.forName("com.mysql.jdbc.Driver");
				String connURL = "jdbc:mysql://localhost:3306/" + db;
				conn = DriverManager.getConnection(connURL, user, password);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
//	public static void main(String[] args) {
//		Connection conn = DatabaseConnec.getConnect();
//		if(conn != null) System.out.println("Thành công!");
//		else System.out.println("Thất bại!");
//	}
}









