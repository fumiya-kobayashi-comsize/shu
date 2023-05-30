package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.UserBean;

/**
 * 入力されたユーザ情報を読み取り、確認画面に遷移するための処理をする
 * @author 櫻井藍子
 */
@WebServlet("/user-confirm-servlet")
public class UserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserConfirmServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		// Beanのインスタンス化
		UserBean user = new UserBean();

		// 入力されたユーザIDとユーザ名とパスワードを取得する
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		// beanに情報をセット
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();

		// セッションスコープにbeanの情報を設定
		session.setAttribute("newUser", user);
		// 再入力されたパスワードをセッションに設定
		session.setAttribute("passwordConfirm", passwordConfirm);

		// 変更情報確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("user-register-confirm.jsp");
		rd.forward(request, response);
	}
}
