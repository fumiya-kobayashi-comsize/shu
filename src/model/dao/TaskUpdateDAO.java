package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.TaskCategoryStatusBean;

public class TaskUpdateDAO {

	public int update(TaskCategoryStatusBean tcs) throws ClassNotFoundException {
		//編集内容をデータベースに反映
		String sql = "UPDATE t_task SET task_id = ?, task_name = ?, category_id = ?, limit_date = ?, user_id = ?, status_code = ?, memo = ? WHERE item_code = ?";
		int count = 0;

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, tcs.getTaskId());
			pstmt.setString(2, tcs.getTaskName());
			pstmt.setInt(3, tcs.getCategoryId());
			pstmt.setDate(4, tcs.getLimitDate());
			pstmt.setString(5, tcs.getUserId());
			pstmt.setString(6, tcs.getStatusCode());
			pstmt.setString(7, tcs.getMemo());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//実行結果の件数を返す
		return count;
	}

	public TaskCategoryStatusBean taskDetail(int taskId) throws ClassNotFoundException {

		TaskCategoryStatusBean task = new TaskCategoryStatusBean();

		String sql = "SELECT "
						+ "task_id, task_name, category_id, category_name, limit_date, user_id, status_code, status_name, memo "
						+ "FROM "
						+ "t_task t1 "
						+ "INNER JOIN m_category t2 "
							+ "ON t1.category_id = t2.category_id "
						+ "INNER JOIN m_status t3 "
							+ "ON t1.status_code = t3.status_code "
						+ "WHERE task_id = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, taskId);

			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while (res.next()) {

				task.setTaskId(taskId);
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				task.setLimitDate(res.getDate("limit_date"));
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				task.setMemo(res.getString("memo"));
				task.setCategoryName(res.getString("category_name"));
				task.setStatusName(res.getString("status_name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return task;
	}

}
