package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.Product;

public class ProductService {
	public static Vector<Product> getProduct(){
		Vector<Product> listProduct = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();
			Statement stm = conn.createStatement();
			String sql = "SELECT * FROM dbqlkh.sanpham, mausac, mausanpham "
					+ "where sanpham.idSanPham = mausanpham.idSanPham "
					+ "and mausanpham.idMauSac = mausac.idMauSac "
					+ "and sanpham.TrangThai = 1;" ;
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				product.setID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getInt(3));
				product.setManufacturerName(rs.getString(4));
				product.setAmount(rs.getInt(5));
				product.setColor(rs.getString(10));
				product.setStatus(1);
				product.setIdCategory(rs.getInt(8));
				
				listProduct.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProduct;
 	}
	
	public static boolean updateProduct(Product product) {
		try {
			Connection conn = DatabaseConnec.getConnect();
			
			String sql = "insert into sanpham value(?,?,?,?,?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, product.getID());
			pstm.setString(2, product.getProductName());
			pstm.setInt(3, product.getCost());
			pstm.setString(4, product.getManufacturerName());
			pstm.setInt(5, product.getAmount());
			pstm.setString(6, "1 năm");
			pstm.setInt(7, 1);
			pstm.setInt(8, product.getIdCategory());
			
			int rowEff = pstm.executeUpdate();
			if(rowEff == 0) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	
//	public static void main(String[] args) {
//	Vector<Product> vec = ProductService.getProduct();
//	System.out.println(vec);
//}
}












