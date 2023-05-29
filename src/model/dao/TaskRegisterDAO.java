package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskCategoryStatusBean;

public class TaskRegisterDAO {
						// TaskCategoryStatusBean型を選択
	public int insertTask(TaskCategoryStatusBean tcs) throws SQLException, ClassNotFoundException {
		// 最終的にcountで返したいので0を代入
		int count = 0;
		// SQL文の作成jspで入力された値をINSERTで新規追加
		String sql = "INSERT INTO task_db.t_task (task_name, category_id, limit_date, user_id, status_code, memo) "
				+ "VALUE (?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			//pstmtでsql実行
			pstmt.setString(1, tcs.getTaskName());
			pstmt.setInt(2, tcs.getCategoryId());
			pstmt.setDate(3, tcs.getLimitDate());
			pstmt.setString(4, tcs.getUserId());
			pstmt.setString(5, tcs.getStatusCode());
			pstmt.setString(6, tcs.getMemo());

			// SQLを実行したレコード件数が入っている
			count = pstmt.executeUpdate();
		}
		return count;
	}
	// テーブルに入っているCategoryを表示させる為のメソッド
	public List<CategoryBean> selectCategory() throws SQLException, ClassNotFoundException {
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_category")) {
			while (res.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setCategoryId(res.getInt("category_id"));
				cb.setCategoryName(res.getString("category_name"));

				categoryList.add(cb);
			}

		}
		return categoryList;
	}
	// テーブルに入っているStatusを表示させる為のメソッド
	public List<StatusBean> selectStatus() throws SQLException, ClassNotFoundException {
		List<StatusBean> statusList = new ArrayList<StatusBean>();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_status")) {
			while (res.next()) {
				StatusBean sb = new StatusBean();
				sb.setStatusCode(res.getString("status_code"));
				sb.setStatusName(res.getString("status_name"));

				statusList.add(sb);
			}

		}
		return statusList;

	}
}