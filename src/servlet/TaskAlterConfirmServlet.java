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
import model.form.TaskCategoryStatusForm;

/**
 * タスク編集確認サーブレット
 * @author 桑原嵩
 */
@WebServlet("/task-alter-confirm-servlet")
public class TaskAlterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public TaskAlterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * GETリクエストを処理し、編集したいタスクの情報を送ります。
	 *
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//タスクIDの取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		//DAOの利用
		TaskUpdateDAO updateDAO = new TaskUpdateDAO();
		TaskRegisterDAO registerDAO = new TaskRegisterDAO();

		try {
			//タスクの詳細情報が入ったFormを取得
			TaskCategoryStatusForm task = updateDAO.taskDetail(taskId);
			//カテゴリー名とステータス名のListを取得
			List<CategoryBean> categoryList = registerDAO.selectCategory();
			List<StatusBean> statusList = registerDAO.selectStatus();

			//取得したFormとListをセッションスコープに格納
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
	 * POSTリクエストを処理し、入力された編集内容を取得します。
	 *
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//編集するタスクのタスクIDをセッションスコープから取得
		HttpSession session = request.getSession();
		TaskCategoryStatusForm task = (TaskCategoryStatusForm) session.getAttribute("task");
		int taskId = task.getTaskId();

		//JSPに入力された編集内容の取得
		request.setCharacterEncoding("UTF-8");

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

		//編集内容を、インスタンス化したForm型のtcsf変数に格納する
        TaskCategoryStatusForm tcsf = new TaskCategoryStatusForm();
        tcsf.setTaskId(taskId);
        tcsf.setTaskName(taskName);
        tcsf.setCategoryId(categoryId);
        tcsf.setLimitDateStr(limitDateStr);
        tcsf.setStatusCode(statusCode);
        tcsf.setMemo(memo);
        tcsf.setCategoryName(categoryName);
        tcsf.setStatusName(statusName);

        //編集内容をセッションスコープに格納
        session.setAttribute("tcsf", tcsf);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-confirm.jsp");
		rd.forward(request, response);

	}

}
