package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {

	public UserBean getUserData(String userId)
			throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM task_db.m_user WHERE user_id = ?;";

		UserBean user = new UserBean();

		// データベースへの接続の取得、Statementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, userId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while(res.next()) {
				user.setUserId(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setUserName(res.getString("user_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}



}
