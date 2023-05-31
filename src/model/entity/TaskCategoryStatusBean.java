package model.entity;

import java.sql.Date;

/**
 * タスク情報を表すエンティティクラスです。
 * このクラスは、task_db.t_task、task_db.m_category、task_db.m_statusのエンティティであり、
 * Beanオブジェクトです。
 *
 * @author 桑原嵩
 */
public class TaskCategoryStatusBean {

	/* テーブル t_task、m_category、m_status のBean */

	/**
	 * タスクID
	 */
	private int taskId;
	/**
	 * タスク名
	 */
	private String taskName;
	/**
	 * カテゴリID
	 */
	private int categoryId;
	/**
	 * 期限
	 */
	private Date limitDate;
	/**
	 * ユーザID
	 */
	private String userId;
	/**
	 * ステータスコード
	 */
	private String statusCode;
	/**
	 * メモ
	 */
	private String memo;
	/**
	 * カテゴリ名
	 */
	private String categoryName;
	/**
     * ステータス名
     */
	private String statusName;

	/**
	 * タスクIDを取得します。
	 *
	 * @return タスクID
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * タスクIDを設定します。
	 *
	 * @param taskId タスクID
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * タスク名を取得します。
	 *
	 * @return タスク名
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * タスク名を設定します。
	 *
	 * @param taskName タスク名
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * カテゴリIDを取得します。
	 *
	 * @return カテゴリID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * カテゴリIDを設定します。
	 *
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 期限を取得します。
	 *
	 * @return 期限
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * 期限を設定します。
	 *
	 * @param limitDate 期限
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	/**
	 * ユーザIDを取得します。
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定します。
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ステータスコードを取得します。
	 *
	 * @return ステータスコード
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * ステータスコードを設定します。
	 *
	 * @param statusCode ステータスコード
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * メモを取得します。
	 *
	 * @return メモ
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * メモを設定します。
	 *
	 * @param memo メモ
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * カテゴリ名を取得します。
	 *
	 * @return カテゴリ名
	 */

	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * カテゴリ名を設定します。
	 *
	 * @param categoryName カテゴリ名
	 */

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	/**
	 * ステータス名を取得します。
	 *
	 * @return ステータス名
	 */

	public String getStatusName() {
		return statusName;
	}

	/**
	 * ステータス名を設定します。
	 *
	 * @param statusName ステータス名
	 */

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
