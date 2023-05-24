package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public boolean existsUser(String userId, String password)
			throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM task_db.m_user WHERE user_id = ? and password = ?;";
		boolean isUserExists = false;

		// データベースへの接続の取得、Statementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			if (res.next()) {
				isUserExists = true;
			}
			return isUserExists;
		}
	}

	public String getUserName(String userId, String password)
			throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM task_db.m_user WHERE user_id = ? and password = ?;";
		String userName = null;

		// データベースへの接続の取得、Statementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while(res.next()) {
				userName = res.getString("user_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userName;

	}

}
