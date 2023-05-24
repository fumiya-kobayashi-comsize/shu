package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public boolean existsUser(String userId, String password) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM task_db.m_user WHERE user_id = ? and password = ?;";
		boolean isUserExists = false;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				isUserExists = true;
			}
			return isUserExists;
		}
	}

	public String getUserName(String userId, String password) throws SQLException, ClassNotFoundException {


		String sql = "SELECT * from task_db.m_user WHERE user_id = ? AND password = ?;";
		String userName = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			ResultSet res = pstmt.executeQuery();

			while(res.next()) {
				userName = res.getString("user_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userName;
	}
}
