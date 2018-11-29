package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.Category;

public class CategoryService {
	public static Vector<Category> getCategory(){
		Vector<Category> listCate = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();
			Statement stm = conn.createStatement();
			String sql = "select * from danhmuc where danhmuc.status = 1;";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Category category = new Category();
				category.setIdCategory(rs.getInt(1));
				category.setNameCategory(rs.getString(2));
				
				listCate.add(category);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listCate;
 	}
	
	public static boolean updateCategory(Category cate) {
		try {
			Connection conn = DatabaseConnec.getConnect();
			
			String sql = "insert into danhmuc value (?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cate.getIdCategory());
			pstm.setString(2, cate.getNameCategory());
			pstm.setInt(3, 1);
			pstm.setInt(4, 1);
			
			int rowEff = pstm.executeUpdate();
			if(rowEff == 0) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
//	public static void main(String[] args) {
//		Vector<Category> vec = CategoryService.getCategory();
//		System.out.println(vec);
//	}
}










