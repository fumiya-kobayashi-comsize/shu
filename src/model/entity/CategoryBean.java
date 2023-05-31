package model.entity;

/**
 * カテゴリ情報を表します。
 * task_db.m_categoryのEntityクラスでありBeanオブジェクトです。
 * @author 板谷寛希
 */

public class CategoryBean {

	/**
	 * カテゴリID
	 */
	private int categoryId;
	/**
	 * カテゴリ名
	 */
	private String categoryName;


	/**
	 * CategoryBeanを構築します。
	 */
	public CategoryBean() {

	}

	/**
	 * フィールドcategoryIdの値を返します。
	 * @return カテゴリID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * フィールドcategoryIdの値を設定。
	 * @return カテゴリID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * フィールドcategoryNameの値を返します。
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * フィールドcategoryNameの値を設定します。
	 * @return カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
