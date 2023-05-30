package model.entity;

/**
 * ステータス情報を表すエンティティクラスです。
 * @author 吉澤誠和
 */
public class StatusBean {
	/**
	 * ステータスコード
	 */
    private String statusCode;
    /**
     * ステータスネーム
     */
    private String statusName;

    /**
     * StatusBeanクラスの新しいインスタンスを作成します。
     */
    public StatusBean() {
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
