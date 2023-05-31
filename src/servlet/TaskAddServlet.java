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
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskCategoryStatusBean;
import model.form.TaskCategoryStatusForm;

/**
 * タスク追加のServletクラスです。
 * @author 吉澤誠和
 */
@WebServlet("/task-add-servlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public TaskAddServlet() {
		super();
	}

	/**
	 * GETリクエストを処理し、タスク登録ページに遷移します。
	 *
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
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

		// セッションスコープへの属性の設定
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("statusList", statusList);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * POSTリクエストを処理し、タスクの登録を行います。
	 *
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UTF-8の書式でコードを受け取る
		request.setCharacterEncoding("UTF-8");
		// boolean型で振り分けをする為記載
		boolean doInsert = true;
		// DAOからcountを受け取るためcount変数記載
		int count = 0;
		// sessionの利用
		HttpSession session = request.getSession();
		TaskCategoryStatusForm tcsf = (TaskCategoryStatusForm)session.getAttribute("tcsf");
		TaskCategoryStatusBean tcsb = null;

		try {
			tcsb = tcsf.toEntity();

		} catch (IllegalArgumentException e) {
			doInsert = false;
		}


		// DAOをインスタンス化
		TaskRegisterDAO dao = new TaskRegisterDAO();

		// もしタスクネームが空文字だったらFALSEに
		if("".equals(tcsf.getTaskName())) {
			doInsert = false;
		}
		try {
			// booleanがtrueだったら
			if (doInsert) {
				// タスクをデータベースに登録する
				count = dao.insertTask(tcsb);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// DAOから帰ってきたcountが0だったら
		if (count == 0) {
			// 登録失敗ページにフォワードする
			RequestDispatcher rd = request.getRequestDispatcher("register-failure.jsp");
			rd.forward(request, response);

		} else {
			// 登録成功ページにフォワードする
			RequestDispatcher rd = request.getRequestDispatcher("register-success.jsp");
			rd.forward(request, response);
		}
	}
}