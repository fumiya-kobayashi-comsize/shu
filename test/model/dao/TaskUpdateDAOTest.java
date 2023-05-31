/**
 *
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import model.entity.TaskCategoryStatusBean;
import model.form.TaskCategoryStatusForm;

/**
 * @author 桑原嵩
 *
 */
class TaskUpdateDAOTest {

	@Test
	void testUpdate() {
		int count = 0;

		TaskCategoryStatusBean tcs = new TaskCategoryStatusBean();
		tcs.setTaskId(4);
        tcs.setTaskName("テスト用に変えるよ");
        tcs.setCategoryId(1);
        tcs.setLimitDate(Date.valueOf("2033-03-13"));
        tcs.setStatusCode("00");
        tcs.setMemo("テスト用に変えるよ");
        tcs.setCategoryName("新商品A：開発プロジェクト");
        tcs.setStatusName("未着手");

		TaskUpdateDAO dao = new TaskUpdateDAO();

		try {
			count = dao.update(tcs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals(1, count);
	}


	@Test
	void testTaskDetail() {

		TaskCategoryStatusForm tcsf = new TaskCategoryStatusForm();
		TaskUpdateDAO dao = new TaskUpdateDAO();

		try {
			tcsf = dao.taskDetail(2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals(tcsf.getTaskId(), 2);
		assertEquals(tcsf.getTaskName(), "テスト用");
		assertEquals(tcsf.getCategoryId(), 1);
		assertEquals(tcsf.getLimitDateStr(), "2033-01-24");
		assertEquals(tcsf.getUserId(), "admin");
		assertEquals(tcsf.getStatusCode(), "50");
		assertEquals(tcsf.getMemo(), "テスト用");
		assertEquals(tcsf.getCategoryName(), "新商品A：開発プロジェクト");
		assertEquals(tcsf.getStatusName(), "着手");

	}
}
