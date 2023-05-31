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
 * タスク編集のServletクラスです。
 * @author 桑原嵩
 */
@WebServlet("/task-alter-servlet")
public class TaskAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public TaskAlterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * POSTリクエストを処理し、タスクの編集内容をデータベースに反映します。
	 *
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
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

		//編集内容の比較用にセッションスコープから編集前のタスクの情報を取得
		TaskCategoryStatusForm task = (TaskCategoryStatusForm) session.getAttribute("task");

		//daoの利用
		TaskUpdateDAO dao = new TaskUpdateDAO();

		//isEditedメソッドで編集の有無を判定する
		doUpdate = isEdited(tcsf, task);

		//Update処理に使用するBean型の変数を宣言
		TaskCategoryStatusBean tcsb = null;

		//toEntityメソッドで期限をDate型に変更し、Beanに格納する
		try {
			tcsb = tcsf.toEntity();
			//期限をDate型に変換できなかった場合の処理
		} catch (IllegalArgumentException e) {
			doUpdate = false;
		}

		try {
			if (doUpdate) {
				//アップデートの実行
				count = dao.update(tcsb);
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


	/**
	 * 編集の有無を判定するためのメソッドです。
	 *
	 * @param tcsf 編集後の情報が入ったTaskCategoryStatusForm型のオブジェクト
	 * @param task 編集前の情報が入ったTaskCategoryStatusForm型のオブジェクト
	 */

	public boolean isEdited(TaskCategoryStatusForm tcsf, TaskCategoryStatusForm task) {

		//判定用の変数を宣言
		boolean flag = false;

		//編集内容が変更されているかどうかの判定
		if (!(tcsf.getTaskName().equals(task.getTaskName())
				&& tcsf.getCategoryId() == task.getCategoryId()
				&& tcsf.getLimitDateStr().equals(task.getLimitDateStr())
				&& tcsf.getStatusCode().equals(task.getStatusCode())
				&& tcsf.getMemo().equals(task.getMemo()))) {
			//変更されていたらtrueを代入
			flag = true;
		}

		//判定を返す
		return flag;

	}

}
