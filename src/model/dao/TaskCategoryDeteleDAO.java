package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.ItemBean;
import model.entity.ItemCategoryBean;

public class TaskCategoryDeteleDAO {
	public List<ItemCategoryBean> selectAll()
			throws SQLException, ClassNotFoundException {

		List<ItemCategoryBean> itemList = new ArrayList<ItemCategoryBean>();
		String sql = "SELECT t1.item_code, t1.item_name, t1.price, t2.category_name FROM itemdb.m_item t1 LEFT JOIN itemdb.m_category t2 ON t1.category_code = t2.category_code ORDER BY item_code;";
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT ");
//		sb.append(" t1.item_code ");
//		sb.append(",t1.item_name ");
//		sb.append(",t1.price ");
//		sb.append(",t2.category_name ");
//		sb.append("FROM ");
//		sb.append(" itemdb.m_item t1 ");
//		sb.append("LEFT JOIN  ");
//		sb.append(" itemdb.m_category t2 ");
//		sb.append("ON  ");
//		sb.append(" t1.category_code = t2.category_code ");
//		sb.append("ORDER BY item_code ");
//		String sql = sb.toString();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {

			// 結果の操作
			while (res.next()) {
				int itemCode = res.getInt("item_code");
				String categoryName = res.getString("category_name");
				String itemName = res.getString("item_name");
				int price = res.getInt("price");

				ItemCategoryBean item = new ItemCategoryBean();
				item.setItemCode(itemCode);
				item.setCategoryName(categoryName);
				item.setItemName(itemName);
				item.setPrice(price);

				itemList.add(item);
			}
		}
		return itemList;
	}




	public List<CategoryBean> selectCategory() throws SQLException, ClassNotFoundException {

		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_category")) {
			// 結果の操作
			while (res.next()) {
				int categoryCode = res.getInt("category_code");
				String categoryName = res.getString("category_name");
				CategoryBean category = new CategoryBean();
				category.setCategoryCode(categoryCode);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
		}
		return categoryList;
	}

	public int insertItem(ItemBean itemInfo)
			throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数
		String sql = "INSERT INTO m_item(category_code,item_name,price)VALUES(?,?,?)";
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, itemInfo.getCategoryCode());
			pstmt.setString(2, itemInfo.getItemName());
			pstmt.setInt(3, itemInfo.getPrice());
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}

	public int updateItem(ItemCategoryBean itemResult)
			throws SQLException, ClassNotFoundException {
		int processingNumber = 0; //処理件数

		String sql = "UPDATE m_item SET item_name = ?, category_code = ?, price = ? WHERE item_code = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// プレースホルダへの値の設定
			pstmt.setString(1, itemResult.getItemName());
			pstmt.setInt(2, itemResult.getCategoryCode());
			pstmt.setInt(3, itemResult.getPrice());
			pstmt.setInt(4, itemResult.getItemCode());
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;

	}

	public int deleteItem(int itemCode) throws SQLException, ClassNotFoundException {

		String sql = "DELETE FROM m_item WHERE item_code = ?";
		int processingNumber = 0; //処理件数
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, itemCode);
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;

	}

}
