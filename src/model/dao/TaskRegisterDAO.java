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
	public int insertTask(TaskCategoryStatusBean tbs) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = "INSERT INTO task_db.t_task (task_name, category_id, limit_date, user_id, status_code, memo) "
				+ "VALUE (?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			//pstmtでsql実行
			pstmt.setString(1, tbs.getTaskName());
			pstmt.setInt(2, tbs.getCategoryId());
			pstmt.setDate(3, tbs.getLimitDate());
			pstmt.setString(4, tbs.getUserId());
			pstmt.setString(5, tbs.getStatusCode());
			pstmt.setString(6, tbs.getMemo());

			// SQLを実行したレコード件数が入っている
			count = pstmt.executeUpdate();
		}
		return count;
	}

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