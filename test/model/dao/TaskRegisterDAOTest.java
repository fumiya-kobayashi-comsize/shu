package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskCategoryStatusBean;

class TaskRegisterDAOTest {
	@Disabled
	@Test
	void testInsertItem() {

		TaskRegisterDAO taskRegisterDAO = new TaskRegisterDAO();
		TaskCategoryStatusBean taskInfo = new TaskCategoryStatusBean();
		taskInfo.setTaskName("test");
		taskInfo.setCategoryId(1);
		taskInfo.setLimitDate(Date.valueOf("2023-11-11"));
		taskInfo.setUserId("admin");
		taskInfo.setStatusCode("00");
		taskInfo.setMemo("memo");

		int count = 0;
		try {
			count = taskRegisterDAO.insertTask(taskInfo);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1, count);
	}
	@Disabled
	@Test
	void testSelectCategory() {
		TaskRegisterDAO taskCategoryDAO = new TaskRegisterDAO();
		List<CategoryBean> categoryList = null;

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			categoryList = taskCategoryDAO.selectCategory();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("m_category.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {

				//先頭行は列名
				if (i == 0) {

					//カンマで分割した内容を配列に格納する
					// arr = { "no","name","age","gender","bloodtype" };
					arr = line.split(",");

				} else {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					// csvとdbを比較している
					assertEquals(Integer.parseInt(data[0]), categoryList.get(i - 1).getCategoryId());
					assertEquals(data[1], categoryList.get(i - 1).getCategoryName());

				}

				//行数のインクリメント
				i++;

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//@Disabled
		@Test
		void testSelectStatus() {
			TaskRegisterDAO taskStatusDAO = new TaskRegisterDAO();
			List<StatusBean> statusList = null;

			//ファイル読み込みで使用する３つのクラス
			FileInputStream fi = null;
			InputStreamReader is = null;
			BufferedReader br = null;

			try {
				statusList = taskStatusDAO.selectStatus();
				//読み込みファイルのインスタンス生成
				//ファイル名を指定する
				fi = new FileInputStream("m_status.csv");
				is = new InputStreamReader(fi);
				br = new BufferedReader(is);

				//読み込み行
				String line;

				//読み込み行数の管理
				int i = 0;

				//列名を管理する為の配列
				String[] arr = null;

				//1行ずつ読み込みを行う
				while ((line = br.readLine()) != null) {

					//先頭行は列名
					if (i == 0) {

						//カンマで分割した内容を配列に格納する
						// arr = { "no","name","age","gender","bloodtype" };
						arr = line.split(",");

					} else {
						//カンマで分割した内容を配列に格納する
						String[] data = line.split(",");
						// csvとdbを比較している

						//System.out.println(data[0] + data[1]);
						assertEquals(data[0], statusList.get(i - 1).getStatusCode());
						assertEquals(data[1], statusList.get(i - 1).getStatusName());

					}

					//行数のインクリメント
					i++;

				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

}