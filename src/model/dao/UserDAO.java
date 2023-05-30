package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

/**
 * m_userテーブルのDAOです。
 * @author 櫻井藍子
 */
public class UserDAO {
	/**
	 * 指定されたIDからユーザの情報を検索して返します。
	 * @param userId ユーザID
	 * @return ユーザ情報
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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

	/**
	 * 入力された内容で新規ユーザ情報の登録をします。
	 * @param user ユーザオブジェクト
	 * @return 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int addUser(UserBean user)
			throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数

		String sql = "INSERT INTO task_db.m_user(user_id,password,user_name)VALUES(?,?,?)";

		// データベースへの接続の取得、Statementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());

			// SQLステートメントの実行
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
}
