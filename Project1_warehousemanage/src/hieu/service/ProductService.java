package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import hieu.model.Product;

public class ProductService {
	public static Vector<Product> getProduct(int idCate){
		Vector<Product> listProduct = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();
			Statement stm = conn.createStatement();
			String sql = "SELECT * FROM dbqlkh.sanpham "
					+ "where sanpham.TrangThai = 1 and sanpham.idDanhMuc ="+ idCate+ " ;" ;
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				product.setID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getInt(3));
				product.setManufacturerName(rs.getString(4));
				product.setAmount(rs.getInt(5));
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

	public static boolean insertProduct(Product product) {
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

	public static boolean updateProduct(Product product) {
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "update sanpham set "
					+ "TenSanPham = ?, Gia = ?, HangSanXuat = ?, SoLuong = ?, BaoHanh = ?, TrangThai = ?, idDanhMuc = ? "
					+ "where idSanPham = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(8, product.getID());
			pstm.setString(1, product.getProductName());
			pstm.setInt(2, product.getCost());
			pstm.setString(3, product.getManufacturerName());
			pstm.setInt(4, product.getAmount());
			pstm.setString(5, "1 năm");
			pstm.setInt(6, product.getStatus());
			pstm.setInt(7, product.getIdCategory());

			int rowEff = pstm.executeUpdate();
			if(rowEff != 0) return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Product> serchProduct(int ID, String name, Vector<Product> list) {
		ArrayList<Product> result = new ArrayList<>();

		for(Product product : list) {
			if(product.getID() == ID || product.getProductName() == name) result.add(product);
		}

		return result;
	}

	public static void main(String[] args) {
		Vector<Product> vec = ProductService.getProduct(3);
		System.out.println(vec);
//		Product product = vec.get(0);
//		product.setAmount(50);
//		boolean result = ProductService.updateProduct(product);
//		System.out.println("Thành công chứ? " + result);
//		System.out.println(product.getAmount());
//		System.out.println(product.getProductName());
//		System.out.println(product.getManufacturerName());
//		System.out.println(product.getIdCategory());
//		System.out.println(product.getID());
//		System.out.println(product.getCost());
//		System.out.println(product.getStatus());
		
//		ArrayList<Product> list = new ArrayList<>();
//		
//		list = ProductService.serchProduct(10901, null, vec);
//		System.out.println(list);
	}
}












