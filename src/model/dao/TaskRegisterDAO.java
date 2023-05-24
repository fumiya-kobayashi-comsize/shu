package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

public class TaskRegisterDAO {
	public int insertTask(TaskBean tb) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = "INSERT INTO task_db.t_task (task_id, task_name, category_id, limit_date, user_id, status_code) "
				+ "VALUE (?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			//pstmtでsql実行
			pstmt.setInt(1, tb.getTaskId());
			pstmt.setString(2, tb.getTaskName());
			pstmt.setInt(3, tb.getCategoryId());
			pstmt.setString(4, tb.getLimitDate());
			pstmt.setString(5, tb.getUserId());
			pstmt.setString(6, tb.getStatusCode());

			// SQLを実行したレコード件数が入っている
			count = pstmt.executeUpdate();
		}
			return count;
	}


}