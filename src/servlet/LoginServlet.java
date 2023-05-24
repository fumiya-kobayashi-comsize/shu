package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();
		// 入力されたユーザIDとパスワードを取得する
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");

		boolean isExistUser = false;
		String userName = null;

		// DAOの生成
		UserDAO dao = new UserDAO();

		try {
			// 入力されたユーザIDとパスワードでログイン認証する
			isExistUser = dao.existsUser(userId,password);
			// ログインしたユーザ名を取得する
			userName = dao.getUserName(userId,password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストの転送
		if(isExistUser) {
			// セッションスコープへの属性の設定
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp"); // メニュー画面
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp"); // ログイン失敗画面
			rd.forward(request, response);
		}
	}
}
