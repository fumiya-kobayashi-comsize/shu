/**
 *
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

/**
 * @author S-01
 *
 */
class UserDAOTest {

	@Test
	void test() {
		UserDAO userDAO = new UserDAO();
		UserBean userData = new UserBean();
		userData.setUserId("admin");
		userData.setPassword("2e7d01869cd65058cb884c7c039b804bc76d2a259f6143e84a86980e7cdfe23a");
		userData.setUserName("桑原");
		int count = 0;
		try {
			count = userDAO.getUserData(userData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1, count);

	}

}
