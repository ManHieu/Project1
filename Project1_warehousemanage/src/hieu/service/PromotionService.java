package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.Promotion;

public class PromotionService {
	public static Vector<Promotion> getPromotion(){
		Vector<Promotion> listPromo = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "SELECT * FROM dbqlkh.khuyenmai;";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while(rs.next()) {
				Promotion promo = new Promotion();
				promo.setIdPromo(rs.getInt(1));
				promo.setStartDay(rs.getString(2));
				promo.setEndDay(rs.getString(3));
				promo.setPromoValue(rs.getInt(4));

				listPromo.add(promo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listPromo;
	}

	public static boolean updateUser(Promotion promo) {
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "insert into khuyenmai value (?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, promo.getIdPromo());
			pstm.setString(2, promo.getStartDay());
			pstm.setString(3, promo.getEndDay());
			pstm.setInt(4, promo.getPromoValue());

			int rowEff = pstm.executeUpdate();
			if(rowEff == 0) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}
