package hieu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hieu.model.User;

public class UserService {

	public static Vector<User> getUser(){
		Vector<User> listUser = new Vector<>();
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "SELECT * FROM dbqlkh.quantrivien;";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while(rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));

				listUser.add(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listUser;
	}

	public static boolean updateUser(User user) {
		try {
			Connection conn = DatabaseConnec.getConnect();

			String sql = "insert into quantrivien value (?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getIdUser());
			pstm.setString(2, user.getName());
			pstm.setString(3, user.getUsername());
			pstm.setString(4, user.getPassword());

			int rowEff = pstm.executeUpdate();
			if(rowEff == 0) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}


















}
