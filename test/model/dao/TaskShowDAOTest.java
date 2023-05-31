/**
 *
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskCategoryStatusBean;

class TaskShowDAOTest {

	@Test
	void testSelectAll() {
		List<TaskCategoryStatusBean> taskList = new ArrayList<TaskCategoryStatusBean>();
		TaskShowDAO dao = new TaskShowDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = dao.selectAll("admin");
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("test.csv");
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
					arr = line.split(",");

				} else {

					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");

					assertEquals(data[0], taskList.get(i - 1).getUserId());
					assertEquals(Integer.parseInt(data[1]), taskList.get(i - 1).getTaskId());
					assertEquals(data[2], taskList.get(i - 1).getTaskName());
					assertEquals(Integer.parseInt(data[3]), taskList.get(i - 1).getCategoryId());
					assertEquals(data[4], taskList.get(i - 1).getCategoryName());
					assertEquals(Date.valueOf(data[5]), taskList.get(i - 1).getLimitDate());
					assertEquals(data[6], taskList.get(i - 1).getStatusCode());
					assertEquals(data[7], taskList.get(i - 1).getStatusName());
					assertEquals(data[8], taskList.get(i - 1).getMemo());

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
