package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
 * タスク追加のServletクラスです。
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

		// リクエストスコープへの属性の設定
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
		// jspからのパラメータ名受け取るための変数たち
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String limitDateStr = request.getParameter("limitDate");
		Date limitDate = null;

		// 入力された日付が空文字かどうか確認する
		if (!"".equals(limitDateStr)) {
			try {
				// 空文字でなければsql.dateに変換する
				limitDate = Date.valueOf(limitDateStr);

				// 現在日付を取得する
				LocalDate currentDate = LocalDate.now();
				// java.sql.Dateに変換
				Date currentDateSql = Date.valueOf(currentDate);
				// 入力された日付が過去日付か確認する
				if (!currentDateSql.before(limitDate)) {
					doInsert = false;
				}
			} catch (IllegalArgumentException e) {
				doInsert = false;
			}
		}
		// jspからパラメーター名を受け取る変数たち
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		// jspから受け取ったパラメータ達をBeanでインスタンス化しtb変数に入れとく
		TaskBean tb = new TaskBean();
		tb.setTaskName(taskName);
		tb.setCategoryId(categoryId);
		tb.setLimitDate(limitDate);
		tb.setStatusCode(statusCode);
		tb.setMemo(memo);
		tb.setUserId(userId);

		// DAOをインスタンス化
		TaskRegisterDAO dao = new TaskRegisterDAO();

		try {
			// booleanがtrueだったら
			if (doInsert) {
				// タスクをデータベースに登録する
				count = dao.insertTask(tb);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// DAOから帰ってきたcountが0だったら
		if (count == 0) {
			// 登録失敗ページにリダイレクトする
			RequestDispatcher rd = request.getRequestDispatcher("register-failure.jsp");
			rd.forward(request, response);

		} else {
			// 登録成功ページにリダイレクトする
			RequestDispatcher rd = request.getRequestDispatcher("register-success.jsp");
			rd.forward(request, response);
		}
	}
}