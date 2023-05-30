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
import model.form.TaskCategoryStatusForm;

/**
 * タスク登録確認サーブレット
 * @author 吉澤誠和
 */
@WebServlet("/task-register-confilm-servlet")
public class TaskRegisterConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public TaskRegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        // JSPからのパラメータ名を受け取る変数たち
        String category = request.getParameter("category");
        String taskName = request.getParameter("taskName");
        int categoryId = 0;
        String[] categoryArray = category.split(",");
        categoryId = Integer.parseInt(categoryArray[0]);
        String categoryName = categoryArray[1];
        String limitDateStr = request.getParameter("limitDate");

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

        // JSPから受け取ったパラメータ達をFormでインスタンス化しtcsf変数に格納する
        TaskCategoryStatusForm tcsf = new TaskCategoryStatusForm();
        tcsf.setTaskName(taskName);
        tcsf.setCategoryId(categoryId);
        tcsf.setLimitDateStr(limitDateStr);
        tcsf.setStatusCode(statusCode);
        tcsf.setMemo(memo);
        tcsf.setUserId(userId);
        tcsf.setCategoryName(categoryName);
        tcsf.setStatusName(statusName);

        // セッション属性設定
        session.setAttribute("tcsf", tcsf);
        // リクエスト転送
        RequestDispatcher rd = request.getRequestDispatcher("task-register-confirm.jsp");
        rd.forward(request, response);

    }

}
