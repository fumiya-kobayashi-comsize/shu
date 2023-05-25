package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskRegisterDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/task-add-servlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<StatusBean> statusList = null;
		List<CategoryBean> categoryList = null;

		// DAOの生成
		TaskRegisterDAO dao = new TaskRegisterDAO();

		try {
			// DAOの利用
			categoryList = dao.selectCategory();
			statusList = dao.selectStatus();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("statusList", statusList);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean doInsert = true;
		int count = 0;
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String limitDateStr = request.getParameter("limitDate");
		Date limitDate = null;
		if (!"".equals(limitDateStr)) {
			try {
				limitDate = Date.valueOf(limitDateStr);
			} catch (IllegalArgumentException e) {
				doInsert = false;
			}
		}else {

		}

		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		TaskBean tb = new TaskBean();
		tb.setTaskName(taskName);
		tb.setCategoryId(categoryId);
		tb.setLimitDate(limitDate);
		tb.setStatusCode(statusCode);
		tb.setMemo(memo);
		tb.setUserId(userId);

		TaskRegisterDAO dao = new TaskRegisterDAO();

		try {
			if (doInsert) {
				// DAOの利用
				count = dao.insertTask(tb);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (count == 0) {
			// リクエストの転送
			RequestDispatcher rd = request.getRequestDispatcher("register-failure.jsp");
			rd.forward(request, response);

		} else {
			// リクエストの転送
			RequestDispatcher rd = request.getRequestDispatcher("register-success.jsp");
			rd.forward(request, response);
		}

	}

}