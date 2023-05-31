package model.entity;

/**
 * ユーザの情報を表します。
 * m_userのEntityクラスであり、Beanオブジェクトです。
 * @author 櫻井藍子
 */
public class UserBean {

	/**
	 * ユーザID
	 */
	private String userId;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * ユーザ名
	 */
	private String userName;


	/**
	 * UserBeanを構築します。
	 */
	public UserBean() {

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
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * フィールドpasswordの値を返します。
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * フィールドpasswordの値を設定します。
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * フィールドuserNameの値を返します。
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * フィールドuserNameの値を設定します。
	 * @param userName ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
