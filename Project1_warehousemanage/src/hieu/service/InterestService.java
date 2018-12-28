package hieu.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.Interest;

public class InterestService {
	public static Vector<Interest> statistic(){
		Vector<Interest> listInterest = new Vector<>();
//		Vector<Product> listProduct = ProductService.getProduct();
//		Vector<Storage> listStorage = StorageService.getStorage();
//		
//		for(int i = 0; i < listProduct.size(); i ++) {
//			
//			Product product = listProduct.get(i);
//			Storage storage = listStorage.get(i);
//			Interest interest = new Interest();
//			interest.setId(product.getID());
//			interest.setName(product.getProductName());
//			int sales = storage.getAmount() - product.getAmount();
//			int profit = sales * (product.getCost());
//			interest.setProfit(profit);
//			interest.setSales(sales);
//			
//			listInterest.add(interest);
//		}
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "select khohang.Gia as GiaNhap, sanpham.idSanPham, sanpham.Gia as GiaBan, "
					+ "sanpham.TenSanPham, khohang.SoLuong as SoluongNhap, sanpham.SoLuong as TonKho "
					+ "from khohang, sanpham where khohang.idSanPham = sanpham.idSanPham;";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Interest interest = new Interest();
				interest.setId(rs.getInt(2));
				interest.setName(rs.getString(4));
				int sales = rs.getInt(5) - rs.getInt(6);
				interest.setSales(sales);
				long profit = (long)rs.getInt(3)*sales;
				interest.setProfit(profit);
				listInterest.add(interest);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listInterest;
	}
}
