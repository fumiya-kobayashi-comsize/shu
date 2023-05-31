package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.TaskCategoryStatusBean;
import model.form.TaskCategoryStatusForm;

/**
 * タスク編集のためのデータアクセスメソッドを提供するクラスです。
 * データベースとのやり取りを行い、CRUD操作を実行します。
 * @author 桑原嵩
 */

public class TaskUpdateDAO {

	/**
     * タスクの編集内容をデータベースに反映します。
     *
     * @param tcs 編集されたタスクを表すTaskCategoryStatusBeanオブジェクト
     * @return 影響を受けたレコード数（挿入が成功した場合は通常1）
     * @throws SQLException            データベースアクセスエラーが発生した場合
     * @throws ClassNotFoundException データベースドライバクラスが見つからない場合
     */

	public int update(TaskCategoryStatusBean tcs) throws ClassNotFoundException {

		//編集内容をデータベースに反映するためのUPDATE文
		String sql = "UPDATE t_task SET task_name = ?, category_id = ?, limit_date = ?, status_code = ?, memo = ? WHERE task_id = ?";
		int count = 0;

		// データベースへの接続の取得、PreparedStatementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			//引数で与えられたBeanから編集内容の取得
			pstmt.setString(1, tcs.getTaskName());
			pstmt.setInt(2, tcs.getCategoryId());
			pstmt.setDate(3, tcs.getLimitDate());
			pstmt.setString(4, tcs.getStatusCode());
			pstmt.setString(5, tcs.getMemo());
			pstmt.setInt(6, tcs.getTaskId());

			//実行結果の件数を取得
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//実行結果の件数を返す
		return count;
	}

	/**
     * 編集したいタスクの情報をデータベースから引き出します。
     *
     * @param taskId 編集したいタスクのID
     * @return 編集したいタスクの情報が入ったForm
     * @throws SQLException            データベースアクセスエラーが発生した場合
     * @throws ClassNotFoundException データベースドライバクラスが見つからない場合
     */

	public TaskCategoryStatusForm taskDetail(int taskId) throws ClassNotFoundException {

		//Formのインスタンス化
		TaskCategoryStatusForm task = new TaskCategoryStatusForm();

		//タスク情報を取得するSELECT文
		String sql = "SELECT "
				+ "t1.task_id, t1.task_name, t1.category_id, t2.category_name, t1.limit_date, t1.user_id, t1.status_code, t3.status_name, t1.memo "
				+ "FROM "
				+ "t_task t1 "
				+ "INNER JOIN m_category t2 "
				+ "ON t1.category_id = t2.category_id "
				+ "INNER JOIN m_status t3 "
				+ "ON t1.status_code = t3.status_code "
				+ "WHERE t1.task_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, taskId);

			ResultSet res = pstmt.executeQuery();

			//データベース上のタスク情報をFormにセット
			while (res.next()) {

				task.setTaskId(taskId);
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				if (res.getString("limit_date") == null) {
					task.setLimitDateStr("");
				} else {
					task.setLimitDateStr(res.getString("limit_date"));
				}
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				task.setMemo(res.getString("memo"));
				task.setCategoryName(res.getString("category_name"));
				task.setStatusName(res.getString("status_name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Formを返す
		return task;
	}


}
