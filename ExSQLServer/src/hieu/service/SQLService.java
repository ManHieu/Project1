package hieu.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLService {
	protected Connection conn=null;
	public SQLService () {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Mở driver 
			String connectionUrl=
					"jdbc:sqlserver://DESKTOP-MITQQAR:1433;databaseName=dbQuanLySanPham;integratedSecurity=true;";
			conn= DriverManager.getConnection(connectionUrl);
//			System.out.println("Kết nối thành công!");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
