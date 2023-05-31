package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import model.entity.TaskCategoryStatusBean;

class TaskDeleteDAOTest {
	@Disabled
	@Test
	void testselectTask() {

		TaskDeleteDAO taskDeleteDAO = new TaskDeleteDAO();
		TaskCategoryStatusBean task = new TaskCategoryStatusBean();

		try {
			task = taskDeleteDAO.selectTask(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1, bean.getItemCode());
		assertEquals("テストDVD1", bean.getItemName());
		assertEquals("DVD", bean.getCategoryName());
		assertEquals(3024, bean.getPrice());
	}


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

	/**
	 * 指定されたタスクIDのタスク情報を削除して処理件数を返します。
	 * @param taskId タスクID
	 * @return 削除処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

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
