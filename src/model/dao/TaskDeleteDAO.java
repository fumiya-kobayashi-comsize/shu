package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskCategoryStatusBean;

public class TaskDeleteDAO {

	//task_idに対応したt_taskのテーブルを表示する(タスク削除確認用)
	public List<TaskCategoryStatusBean> selectTask(int taskId) throws SQLException, ClassNotFoundException {

		List<TaskCategoryStatusBean> selectTask = new ArrayList<TaskCategoryStatusBean>();

		String sql = "SELECT *" +
				"FROM\r\n" +
				"	task_db.t_task t1\r\n" +
				"	INNER JOIN task_db.m_category t2\r\n" +
				"		ON t1.category_id = t2.category_id\r\n" +
				"	INNER JOIN task_db.m_status t4\r\n" +
				"		ON t1.status_code = t4.status_code\r\n" +
				"WHERE\r\n" +
				"	t1.task_id = ?;";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダに値を設定
			pstmt.setInt(1, taskId);

			ResultSet res = pstmt.executeQuery();//SQLを実行したときに表が帰ってくる。

			while (res.next()) {

				TaskCategoryStatusBean task = new TaskCategoryStatusBean();

				String taskName = res.getString("task_name");
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				Date limitDate = res.getDate("limit_date");
				String userId = res.getString("user_id");
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				String memo = res.getString("memo");

				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setCategoryName(categoryName);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setStatusName(statusName);
				task.setMemo(memo);

				selectTask.add(task);
			}
			return selectTask;
		}
	}

	//task_idに対応したt_taskのテーブルを削除するDAO（タスク削除用）
	public int deleteTask(int taskId) throws SQLException, ClassNotFoundException {

		String sql = "DELETE FROM task_db.t_task WHERE task_id = ?";
		int processingNumber = 0; //処理件数
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;

	}

}
