package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.mysql.cj.PreparedQuery;

import hieu.model.Storage;

public class StorageService {
	public static Vector<Storage> getStorage(){
		Vector<Storage> list = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();
			
			String sql = "SELECT * FROM dbqlkh.khohang;";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Storage storage = new Storage();
				
				storage.setAmount(rs.getInt(2));
				storage.setID(rs.getInt(4));
				storage.setImportDate(rs.getString(1));
				storage.setUnitPrice(rs.getInt(3));
				
				list.add(storage);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean insertStorage(Storage storage) {
		
		try {
			Connection conn = DatabaseConnec.getConnect();
			
			String sql = "insert into khohang value (?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, storage.getImportDate());
			pstm.setInt(2, storage.getAmount());
			pstm.setInt(3, storage.getUnitPrice());
			pstm.setInt(4, storage.getID());
			
			int rowEff = pstm.executeUpdate();
			if(rowEff == 0) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}












