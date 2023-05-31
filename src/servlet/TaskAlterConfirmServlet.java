package servlet;

import java.io.IOException;
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
import model.dao.TaskUpdateDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskCategoryStatusBean;
import model.form.TaskCategoryStatusForm;

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
		TaskUpdateDAO updateDAO = new TaskUpdateDAO();
		TaskRegisterDAO registerDAO = new TaskRegisterDAO();

		try {
			//タスクの詳細情報を取得
			TaskCategoryStatusBean task = updateDAO.taskDetail(taskId);
			//カテゴリーとステータスの詳細情報を取得
			List<CategoryBean> categoryList = registerDAO.selectCategory();
			List<StatusBean> statusList = registerDAO.selectStatus();

			//取得した情報をセッションスコープに格納
			HttpSession session = request.getSession();
			session.setAttribute("task", task);
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("statusList", statusList);

		} catch (ClassNotFoundException | SQLException e) {
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

		HttpSession session = request.getSession();
		TaskCategoryStatusBean task = (TaskCategoryStatusBean) session.getAttribute("task");

		int taskId = task.getTaskId();
		String taskName = request.getParameter("taskName");

		String category = request.getParameter("category");
		String[] splitCategory = category.split(",");
		int categoryId = Integer.parseInt(splitCategory[0]);
		String categoryName = splitCategory[1];

		String limitDateStr = request.getParameter("limitDate");

		String status = request.getParameter("status");
		String[] splitStatus = status.split(",");
		String statusCode = splitStatus[0];
		String statusName = splitStatus[1];

		String memo = request.getParameter("memo");

		// JSPから受け取ったパラメータをFormでインスタンス化したtcsf変数に格納する
        TaskCategoryStatusForm tcsf = new TaskCategoryStatusForm();
        tcsf.setTaskId(taskId);
        tcsf.setTaskName(taskName);
        tcsf.setCategoryId(categoryId);
        tcsf.setLimitDateStr(limitDateStr);
        tcsf.setStatusCode(statusCode);
        tcsf.setMemo(memo);
        tcsf.setCategoryName(categoryName);
        tcsf.setStatusName(statusName);

        // セッション属性設定
        session.setAttribute("tcsf", tcsf);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-confirm.jsp");
		rd.forward(request, response);

	}

}
