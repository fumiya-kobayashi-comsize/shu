package model.form;

import java.sql.Date;
import java.time.LocalDate;

import model.entity.TaskCategoryStatusBean;
/**
 * jspで打ち込まれた値を表します。
 * @author 吉澤誠和
 */

public class TaskCategoryStatusForm {
	/**
	 * タスクID
	 */
	private int taskId;
	/**
	 * タスク名
	 */
	private String taskName;
	/**
	 * カテゴリーID
	 */
	private int categoryId;
	/**
	 * 期限日の文字列
	 */
	private String limitDateStr;
	/**
	 * ユーザーID
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
	 * @param taskId セットするタスクID
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
	 * @param taskName セットするタスク名
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
	 * @param categoryId セットするカテゴリID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 期限日の文字列を取得します。
	 *
	 * @return 期限日の文字列
	 */
	public String getLimitDateStr() {
		return limitDateStr;
	}

	/**
	 * 期限日の文字列を設定します。
	 *
	 * @param limitDateStr セットする期限日の文字列
	 */
	public void setLimitDateStr(String limitDateStr) {
		this.limitDateStr = limitDateStr;
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
	 * @param userId セットするユーザID
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
	 * @param statusCode セットするステータスコード
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
	 * @param memo セットするメモ
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
	 * @param categoryName セットするカテゴリ名
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
	 * @param statusName セットするステータス名
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * TaskCategoryStatusBeanオブジェクトに変換します。
	 *
	 * @return TaskCategoryStatusBeanオブジェクト
	 * @throws IllegalArgumentException 変換時に例外が発生した場合
	 */
	public TaskCategoryStatusBean toEntity() throws IllegalArgumentException {
		TaskCategoryStatusBean tcs = new TaskCategoryStatusBean();// Beanをインスタンス化

		tcs.setTaskName(taskName);
		tcs.setCategoryId(categoryId);
		// String型で受け取ったlimitDateをDate型へ変換
		Date limitDate = null;
		if (!"".equals(limitDateStr)) {
			limitDate = Date.valueOf(limitDateStr);
			LocalDate currentDate = LocalDate.now();
			Date currentDatesql = Date.valueOf(currentDate);
			if (!currentDatesql.before(limitDate)) {
				throw new IllegalArgumentException();
			}
			tcs.setLimitDate(limitDate);
		}

		tcs.setStatusCode(statusCode);
		tcs.setMemo(memo);
		tcs.setUserId(userId);
		tcs.setCategoryName(categoryName);
		tcs.setStatusName(statusName);
		return tcs;
	}
}

