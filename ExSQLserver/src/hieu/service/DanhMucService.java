package hieu.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.DanhMuc;

public class DanhMucService {
	public Vector<DanhMuc> hienThiDanhMuc(){
		Vector<DanhMuc> vec = new Vector<DanhMuc>();
		try {
			
			Connection conn = GetConnect.getConnect("DESKTOP-OJK9A1J", "dbQuanLySanPham");
			String sql = "Select * from DanhMuc where isDeleted=0";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				DanhMuc dm = new DanhMuc();
				dm.setMaDM(rs.getString(1));
				dm.setTenDM(rs.getString(2));
				dm.setIsDeleted(0);
				vec.add(dm);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vec;
	}
}
