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
import model.form.TaskCategoryStatusForm;

/**
 * Servlet implementation class TaskAlterServlet
 */
@WebServlet("/task-alter-servlet")
public class TaskAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAlterServlet() {
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

		//DAOからcountを受け取るための変数を宣言
		int count = 0;

		//boolean型で振り分けをするための変数を宣言
		boolean doUpdate = true;

		//sessionを利用し、編集後のタスク情報を取得
		HttpSession session = request.getSession();
		TaskCategoryStatusForm tcsf = (TaskCategoryStatusForm) session.getAttribute("tcsf");
		TaskCategoryStatusBean tcsb = null;

		//toEntityメソッドで期限をDate型に変更し、Beanに格納する
		try {
			tcsb = tcsf.toEntity();
		//期限をDate型に変換できなかった場合の処理
		} catch (IllegalArgumentException e) {
			doUpdate = false;
		}

		//編集内容の比較用にセッションスコープから編集前のタスクの情報を取得
		TaskCategoryStatusBean task = (TaskCategoryStatusBean) session.getAttribute("task");

		//daoの利用
		TaskUpdateDAO dao = new TaskUpdateDAO();

		try {
			if (doUpdate) {
				//編集内容が変更されているかどうかの判定
				if (!(tcsb.getTaskName().equals(task.getTaskName())
						&& tcsb.getCategoryId() == task.getCategoryId()
						&& tcsb.getLimitDate().equals(task.getLimitDate())
						&& tcsb.getStatusCode().equals(task.getStatusCode())
						&& tcsb.getMemo().equals(task.getMemo()))) {
					//アップデートの実行
					count = dao.update(tcsb);
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//結果画面への転送
			//編集成功
		if (count != 0) {
			RequestDispatcher rd = request.getRequestDispatcher("task-alter-success.jsp");
			rd.forward(request, response);
			//編集失敗
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("task-alter-failure.jsp");
			rd.forward(request, response);
		}
	}

}
