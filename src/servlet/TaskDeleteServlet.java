package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDeleteDAO;

/**
 * 一覧画面からタスクIDを受け取り、削除のための処理をする。
 * @author 板谷寛希
 */
@WebServlet("/task-delete-servlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		//セッションからtask_idを、int型taskIdで受け取る。
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		//処理件数
		int processingNumber = 0;
		//DAOの利用
		TaskDeleteDAO dao = new TaskDeleteDAO();


		try {
			//DAOの利用、処理件数のカウント
			processingNumber = dao.deleteTask(taskId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 処理件数をリクエストスコープに設定
		request.setAttribute("processingNumber", processingNumber);
		// 削除結果画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-result.jsp");
		rd.forward(request, response);

	}

}
