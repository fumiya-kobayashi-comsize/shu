package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskUpdateDAO;
import model.entity.TaskCategoryStatusBean;

/**
 * Servlet implementation class TaskConfirmServlet
 */
@WebServlet("/task-alter-confirm-servlet")
public class TaskAlterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskAlterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//タスクIDの取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		//DAOの利用
		TaskUpdateDAO dao = new TaskUpdateDAO();

		try {
			//タスクの詳細情報を取得
			TaskCategoryStatusBean task = dao.taskDetail(taskId);
			//取得した詳細情報をセッションスコープに格納
			HttpSession session = request.getSession();
			session.setAttribute("task", task);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 編集画面への転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-form.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//編集内容の取得
		request.setCharacterEncoding("UTF-8");

		String taskName = request.getParameter("taskName");

		String category = request.getParameter("category");
		String[] splitCategory = category.split(",");
		int categoryId = Integer.parseInt(splitCategory[0]);
		String categoryName = splitCategory[1];

		String limitDate = request.getParameter("limitDate");
		String userName = request.getParameter("userName");

		String status = request.getParameter("status");
		String[] splitStatus = status.split(",");
		int statusId = Integer.parseInt(splitStatus[0]);
		String statusName = splitStatus[1];

		String memo = request.getParameter("memo");

		//取得した編集内容をリクエストスコープに格納
		request.setAttribute("taskName", taskName);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("limitDate", limitDate);
		request.setAttribute("userName", userName);
		request.setAttribute("statusId", statusId);
		request.setAttribute("memo", memo);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("statusName", statusName);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-confirm.jsp");
		rd.forward(request, response);

	}

}
