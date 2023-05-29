package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskUpdateDAO;
import model.entity.TaskCategoryStatusBean;
import model.entity.UserBean;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//処理件数カウント用の変数を宣言
		int count = 0;

		//編集された情報の取得
		request.setCharacterEncoding("UTF-8");

		//splitを利用しタスクIDとタスク名を分離取得
		String updatedTask = request.getParameter("updatedTask");
		String[] splitUpdatedTask = updatedTask.split(",");
		int taskId = Integer.parseInt(splitUpdatedTask[0]);
		String taskName = splitUpdatedTask[1];

		//splitを利用しカテゴリーIDとカテゴリー名を分離取得
		String category = request.getParameter("category");
		String[] splitCategory = category.split(",");
		int categoryId = Integer.parseInt(splitCategory[0]);
		String categoryName = splitCategory[1];

		//期限の情報をDate型に変換して取得
		String limitDateStr = request.getParameter("limitDate");
		Date limitDate = null;
		try {

			limitDate = Date.valueOf(limitDateStr);

		} catch (IllegalArgumentException e) {
			//Date型変換が失敗した場合の画面への転送
			count = 0;
			RequestDispatcher rd = request.getRequestDispatcher("item-alter-result.jsp");
			rd.forward(request, response);
		}

		//セッションスコープからユーザーIDを取得
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("user");
		String userId = user.getUserId();

		//splitを利用しステータスコードとステータス名を分離取得
		String status = request.getParameter("status");
		String[] splitStatus = status.split(",");
		String statusCode = splitStatus[0];
		String statusName = splitStatus[1];

		//メモを取得
		String memo = request.getParameter("memo");


		//編集内容の格納
		TaskCategoryStatusBean tcs = new TaskCategoryStatusBean();
		tcs.setTaskId(taskId);
		tcs.setTaskName(taskName);
		tcs.setCategoryId(categoryId);
		tcs.setLimitDate(limitDate);
		tcs.setUserId(userId);
		tcs.setStatusCode(statusCode);
		tcs.setMemo(memo);
		tcs.setCategoryName(categoryName);
		tcs.setStatusName(statusName);

		//編集内容の比較用にセッションスコープからタスクの情報を取得
		TaskCategoryStatusBean task = (TaskCategoryStatusBean) session.getAttribute("task");

		//daoの利用
		TaskUpdateDAO dao = new TaskUpdateDAO();

		//編集内容が変更されているかどうかの確認
		if (task.getTaskName() != taskName
				&& task.getCategoryId() != categoryId
				&& task.getLimitDate() != limitDate
				&& task.getTaskName() != taskName
				&& task.getStatusCode() != statusCode
				&& task.getMemo() != memo) {

			try {
				//アップデートの実行
				count = dao.update(tcs);
				//jsp表示用のデータをセット
				request.setAttribute("tcs", tcs);
				request.setAttribute("count", count);

			} catch (ClassNotFoundException e) {

				//失敗画面への転送
				count = 0;
				RequestDispatcher rd = request.getRequestDispatcher("item-alter-result.jsp");
				rd.forward(request, response);

			}
		//編集がなかった場合の処理
		} else {

			///jsp表示用のデータをセット
			request.setAttribute("tcs", tcs);
			request.setAttribute("count", count);

			//失敗画面への転送
			RequestDispatcher rd = request.getRequestDispatcher("item-alter-result.jsp");
			rd.forward(request, response);

		}

		//成功画面への転送
		RequestDispatcher rd = request.getRequestDispatcher("item-alter-result.jsp");
		rd.forward(request, response);
	}

}
