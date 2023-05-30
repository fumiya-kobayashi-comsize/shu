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

import model.dao.TaskDeleteDAO;
import model.entity.TaskCategoryStatusBean;

/**
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/task-delete-confirm-servlet")
public class TaskDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDeleteConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		//DAOの利用
		TaskDeleteDAO dao = new TaskDeleteDAO();
		//リストの作成、初期化
		List<TaskCategoryStatusBean> taskSelect = null;
		//セッションからtask_idを、int型taskIdで受け取る。
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		try {
			//タスクマスタから指定されたタスクIDの情報を抜き出すDAO
			taskSelect = dao.selectTask(taskId);//タスク詳細取得
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		//セッションから情報の取得
		HttpSession session = request.getSession();

		// タスクの詳細情報、task_idをセッションに設定
		session.setAttribute("taskSelect", taskSelect);
		session.setAttribute("taskId", taskId);
		// タスク削除確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-confirm.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
