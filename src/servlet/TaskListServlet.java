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

import model.dao.TaskShowDAO;
import model.entity.TaskCategoryStatusBean;
import model.entity.UserBean;


/**
 * ユーザーIDに応じたタスク一覧画面表示のための処理をする。
 * @author 板谷寛希
 */
@WebServlet("/task-list-servlet")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
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

		List<TaskCategoryStatusBean> taskList = null;


		// DAOの生成
		TaskShowDAO dao = new TaskShowDAO();


		//セッションから情報の取得
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("user");
		String userId = user.getUserId();
		String userName = user.getUserName();


		try {
			// 引数にuserIdを指定してタスクマスタからタスク情報を取得
			taskList = dao.selectAll(userId);



		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("taskList", taskList);
		request.setAttribute("userName", userName);

		// 商品一覧画面への転送
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		rd.forward(request, response);


	}

}
