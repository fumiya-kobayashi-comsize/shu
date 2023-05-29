package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * 安全なパスワードを生成し、ログイン認証のための処理をする
 * @author 櫻井藍子
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** パスワードを安全にするためのアルゴリズム */
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	/** ストレッチング回数 */
	private static final int ITERATION_COUNT = 10000;
	/** 生成される鍵の長さ */
	private static final int KEY_LENGTH = 256;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();

		// 入力されたユーザIDとパスワードを取得する
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");

		// 入力されたユーザIDとパスワードをハッシュ化する
		String safetyPassword = getSafetyPassword(password, userId);

		UserBean user = null;

		// DAOの生成
		UserDAO dao = new UserDAO();

		try {
			// 入力されたユーザIDからユーザ情報を取得する
			user = dao.getUserData(userId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースから取得したパスワードと比較してログイン認証する
		if (safetyPassword.equals(user.getPassword())) {
			// ユーザ情報をセッションに設定
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp"); // メニュー画面
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp"); // ログイン失敗画面
			rd.forward(request, response);
		}
	}

	/**
	 * 平文のパスワードとソルトから安全なパスワードを生成し、返却します
	 *
	 * @param password 平文のパスワード
	 * @param salt ソルト
	 * @return 安全なパスワード
	 */
	public static String getSafetyPassword(String password, String salt) {

		char[] passCharAry = password.toCharArray();
		byte[] hashedSalt = getHashedSalt(salt);

		PBEKeySpec keySpec = new PBEKeySpec(passCharAry, hashedSalt, ITERATION_COUNT, KEY_LENGTH);

		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		SecretKey secretKey;
		try {
			secretKey = skf.generateSecret(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
		byte[] passByteAry = secretKey.getEncoded();

		// 生成されたバイト配列を16進数の文字列に変換
		StringBuilder sb = new StringBuilder(64);
		for (byte b : passByteAry) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

	/**
	 * ソルトをハッシュ化して返却します
	 * ※ハッシュアルゴリズムはSHA-256を使用
	 *
	 * @param salt ソルト
	 * @return ハッシュ化されたバイト配列のソルト
	 */
	private static byte[] getHashedSalt(String salt) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		messageDigest.update(salt.getBytes());
		return messageDigest.digest();
	}

}
