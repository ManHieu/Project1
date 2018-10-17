package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import hieu.model.SanPham;

public class SanPhamService {
	public ArrayList <SanPham> getDsSp(String dm){
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			Connection conn = GetConnect.getConnect("DESKTOP-OJK9A1J", "dbQuanLySanPham");
			String sql = "Select * from SanPham where madm=? and isdeleted=0";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, dm);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaDM(dm);
				sp.setIsDeleted(0);
				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setDonGia(rs.getInt(4));
				sp.setSoLuong(rs.getInt(3));
				dsSP.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSP;
	}
	public boolean themSanPham(SanPham sp) {
		Connection conn = GetConnect.getConnect("DESKTOP-OJK9A1J", "dbQuanLySanPham");
		String sql = "insert into SanPham values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, sp.getMaSP());
			pstm.setString(2, sp.getTenSP());
			pstm.setInt(3, sp.getSoLuong());
			pstm.setInt(4, sp.getDonGia());
			pstm.setString(5, sp.getMaDM());
			pstm.setInt(6, 0);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		SanPhamService sps = new SanPhamService();
		SanPham sp = new  SanPham();
		sp.setDonGia(5);
		sp.setIsDeleted(0);
		sp.setMaDM("dm1");
		sp.setMaSP("555");
		sp.setSoLuong(5555);
		sp.setTenSP("hhh");
		sps.themSanPham(sp);
		
	}
}
