package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskRegisterDAO;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean doInsert = true;
		int count = 0;
		int taskId =Integer.parseInt(request.getParameter("taskId"));
		String taskName = request.getParameter("taskName");
		int categoryId =Integer.parseInt(request.getParameter("categoryId"));
		String limitDate = request.getParameter("limitDate");
		String userId = request.getParameter("userId");
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");

		TaskBean tb = new TaskBean();
			tb.setTaskId(taskId);
			tb.setTaskName(taskName);
			tb.setCategoryId(categoryId);
			tb.setLimitDate(limitDate);
			tb.setStatusCode(statusCode);
			tb.setMemo(memo);

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

