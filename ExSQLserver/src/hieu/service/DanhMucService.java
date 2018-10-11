package hieu.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.DanhMuc;

public class DanhMucService extends SQLService{
	public Vector<DanhMuc> ReadDanhMuc(){
		Vector<DanhMuc> vec = new Vector<DanhMuc>();
		try {
			String sql = "select * from DanhMuc where isDeleted=0";
			Statement stm =conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				DanhMuc dm = new DanhMuc();
				dm.setIsDeleted(1);
				dm.setMaDM(rs.getString(1));
				dm.setTenDM(rs.getString(2));
				vec.add(dm);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vec;
	}
}

