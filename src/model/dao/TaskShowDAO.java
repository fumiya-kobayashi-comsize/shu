package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskCategoryStatusBean;

//担当者ごとのタスク一覧を表示するDAO
public class TaskShowDAO {

	public List<TaskCategoryStatusBean> selectAll(String userId) throws ClassNotFoundException, SQLException {

		List<TaskCategoryStatusBean> taskList = new ArrayList<TaskCategoryStatusBean>();

		String sql = "SELECT *" +
				"FROM\r\n" +
				"	task_db.t_task t1\r\n" +
				"	INNER JOIN task_db.m_category t2\r\n" +
				"		ON t1.category_id = t2.category_id\r\n" +
				"	INNER JOIN task_db.m_status t4\r\n" +
				"		ON t1.status_code = t4.status_code\r\n" +
				"WHERE\r\n" +
				"	t1.user_id = ?;";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダに値を設定
			pstmt.setString(1, userId);

			ResultSet res = pstmt.executeQuery();//SQLを実行したときに表が帰ってくる。

			// 結果の操作
			while (res.next()) {
				TaskCategoryStatusBean task = new TaskCategoryStatusBean();

				//タスク詳細のget
				int taskId = res.getInt("task_id");
				String taskName = res.getString("task_name");
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				Date limitDate = res.getDate("limit_date");
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				String memo = res.getString("memo");

				//taskにset
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setCategoryName(categoryName);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setStatusName(statusName);
				task.setMemo(memo);

				taskList.add(task);
			}
		}

		return taskList;

	}
}
