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

/**
 * タスク登録のためのデータアクセスメソッドを提供するクラスです。
 * データベースとのやり取りを行い、CRUD操作を実行します。
 * @author 吉澤誠和
 */
public class TaskRegisterDAO {

    /**
     * タスクをデータベースに挿入します。
     *
     * @param tcs 挿入するタスクを表すTaskCategoryStatusBeanオブジェクト
     * @return 影響を受けたレコード数（挿入が成功した場合は通常1）
     * @throws SQLException            データベースアクセスエラーが発生した場合
     * @throws ClassNotFoundException データベースドライバクラスが見つからない場合
     */
    public int insertTask(TaskCategoryStatusBean tcs) throws SQLException, ClassNotFoundException {
        int count = 0; // 影響を受けたレコード数（初期値は0）
        String sql = "INSERT INTO task_db.t_task (task_name, category_id, limit_date, user_id, status_code, memo) "
                + "VALUES (?, ?, ?, ?, ?, ?)"; // タスクを挿入するためのSQL文

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tcs.getTaskName());
            pstmt.setInt(2, tcs.getCategoryId());
            pstmt.setDate(3, tcs.getLimitDate());
            pstmt.setString(4, tcs.getUserId());
            pstmt.setString(5, tcs.getStatusCode());
            pstmt.setString(6, tcs.getMemo());

            count = pstmt.executeUpdate(); // SQL文を実行し、影響を受けたレコード数を取得
        }
        return count;
    }

    /**
     * データベースからカテゴリのリストを取得します。
     *
     * @return カテゴリを表すCategoryBeanオブジェクトのリスト
     * @throws SQLException            データベースアクセスエラーが発生した場合
     * @throws ClassNotFoundException データベースドライバクラスが見つからない場合
     */
    public List<CategoryBean> selectCategory() throws SQLException, ClassNotFoundException {
        List<CategoryBean> categoryList = new ArrayList<>();

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

    /**
     * データベースからステータスのリストを取得します。
     *
     * @return ステータスを表すStatusBeanオブジェクトのリスト
     * @throws SQLException            データベースアクセスエラーが発生した場合
     * @throws ClassNotFoundException データベースドライバクラスが見つからない場合
     */
    public List<StatusBean> selectStatus() throws SQLException, ClassNotFoundException {
        List<StatusBean> statusList = new ArrayList<>();

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
