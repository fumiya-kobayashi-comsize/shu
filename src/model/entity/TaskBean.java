package model.entity;

import java.sql.Date;

/**
 * タスク情報を表します。
 * task_db.t_taskのEntityクラスであり、Beanオブジェクトです。
 * @author 板谷寛希
 */

public class TaskBean {

	/**
	 * タスクID
	 */
	private int taskId;
	/**
	 * タスク名前
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
	 * TaskBeanのコンストラクタを設定します。
	 */
	public TaskBean() {

	}
	/**
	 * フィールドtaskIdの値を返します。
	 * @return タスクID
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * フィールドtaskIdの値を設定します。
	 * @return タスクID
	 */

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * フィールドtaskNameの値を返します。
	 * @return タスク名
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * フィールドtaskNameの値を設定します。
	 * @return タスク名
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * フィールドcategoryIdの値を返します。
	 * @return カテゴリID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * フィールドcategoryIdの値を設定します。
	 * @return カテゴリID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * フィールドlimitDateの値を返します。
	 * @return 期限
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * フィールドlimitDateの値を設定します。
	 * @return 期限
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	/**
	 * フィールドuserIdの値を返します。
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * フィールドuserIdの値を設定します。
	 * @return ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * フィールドstatusCodeの値を返します。
	 * @return ステータスコード
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * フィールドstatusCodeの値を設定します。
	 * @return ステータスコード
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * フィールドmemoの値を返します。
	 * @return メモ
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * フィールドmemoの値を設定します。
	 * @return メモコード
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
