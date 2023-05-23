package model.entity;

public class TaskBean {

/*	タスクテーブル t_task　のBean*/
	private int taskId;
	private String taskName;
	private int categoryId;
	private String limitDate;
	private String userId;
	private char statusCode;
	private String memo;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public char getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(char statusCode) {
		this.statusCode = statusCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


}
