package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.TaskCategoryStatusBean;
import model.entity.UserBean;

/**
 * タスク登録確認サーブレット
 * Servlet implementation class TaskRegisterConfirmServlet
 * @author 吉澤誠和
 */
@WebServlet("/task-register-confilm-servlet")
public class TaskRegisterConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskRegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * GETメソッドの処理
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * POSTメソッドの処理
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // UTF-8の書式でコードを受け取る
        request.setCharacterEncoding("UTF-8");
        // boolean型で振り分けをするための変数
        boolean doInsert = true;
        // DAOからcountを受け取るための変数
        int count = 0;
        // JSPからのパラメータ名を受け取る変数たち
        String category = request.getParameter("category");
        String taskName = request.getParameter("taskName");
        int categoryId = 0;
        String[] categoryArray = category.split(",");
        categoryId = Integer.parseInt(categoryArray[0]);
        String categoryName = categoryArray[1];
        String limitDateStr = request.getParameter("limitDate");
        Date limitDate = null;

        // 入力された日付が空文字かどうか確認する
        if (!"".equals(limitDateStr)) {
            try {
                // 空文字でなければSQL Dateに変換する
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
        // JSPからのパラメータ名を受け取る変数たち
        String status = request.getParameter("status");
        String[] statusArray = status.split(",");
        String statusCode = statusArray[0];
        String statusName = statusArray[1];
        String memo = request.getParameter("memo");

        // LoginServletでセッションしているuserの受け取り
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        String userId = user.getUserId();

        // JSPから受け取ったパラメータ達をBeanでインスタンス化しtcs変数に格納する
        TaskCategoryStatusBean tcs = new TaskCategoryStatusBean();
        tcs.setTaskName(taskName);
        tcs.setCategoryId(categoryId);
        tcs.setLimitDate(limitDate);
        tcs.setStatusCode(statusCode);
        tcs.setMemo(memo);
        tcs.setUserId(userId);
        tcs.setCategoryName(categoryName);
        tcs.setStatusName(statusName);

        // リクエスト属性設定
        session.setAttribute("tcs", tcs);
        // リクエスト転送
        RequestDispatcher rd = request.getRequestDispatcher("task-register-confirm.jsp");
        rd.forward(request, response);
    }
}
