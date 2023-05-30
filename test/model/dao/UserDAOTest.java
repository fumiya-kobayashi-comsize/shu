package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class UserDAOTest {

	@Test
	void test1GetUserData() {
		UserDAO userDAO = new UserDAO();
		UserBean user = new UserBean();
		try {
			user = userDAO.getUserData("admin");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals("admin",user.getUserId());
		assertEquals("2e7d01869cd65058cb884c7c039b804bc76d2a259f6143e84a86980e7cdfe23a",user.getPassword());
		assertEquals("桑原",user.getUserName());
	}

	@Test
	void test2GetUserData() {
		UserDAO userDAO = new UserDAO();
		UserBean user = new UserBean();
		try {
			user = userDAO.getUserData(" ");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(user.getUserId());
		assertNull(user.getPassword());
		assertNull(user.getUserName());
	}

	@Test
	void testAddUser() {
		UserDAO dao = new UserDAO();
		UserBean user = new UserBean();

		user.setUserId("admin0");
		user.setUserName("shu");
		user.setPassword("385063b386c587021ca72d1903d553e8189bee06ee60d85c4db2a864477f2538");

		int count = 0;
		try {
			count = dao.addUser(user);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1, count);
	}
}
