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
 * 安全なパスワードを生成し、新規ユーザ情報の登録処理をする
 * @author 櫻井藍子
 */
@WebServlet("/user-register-servlet")
public class UserRegisterServlet extends HttpServlet {
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
	public UserRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// DAOのインスタンス化
		UserDAO dao = new UserDAO();

		// Beanのインスタンス化
		UserBean user = new UserBean();

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();

		// 入力されたユーザIDとユーザ名とパスワードを取得する
		UserBean newUser = (UserBean) session.getAttribute("newUser");
		String userId = newUser.getUserId();
		String userName = newUser.getUserName();
		String password = newUser.getPassword();

		String passwordConfirm = (String) session.getAttribute("passwordConfirm");
		boolean doInsert = true;

		// 入力されたユーザIDとパスワードをハッシュ化する
		String safetyPassword = null;

		try {
			// 2つのパスワードフィールドの入力値を比較する
			if(password.equals(passwordConfirm)) {
				// 一致すれば安全なパスワードを生成する
				safetyPassword = getSafetyPassword(password, userId);
			}
		} catch (NumberFormatException e) {
			doInsert = false;
		}

		// beanに情報をセット
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(safetyPassword);

		int processingNumber = 0;//処理件数
		String url = "";//転送先

		try {
			if (doInsert) {
				processingNumber = dao.addUser(user);//登録処理
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 遷移先画面の分岐
		if (processingNumber > 0) {
			url = "user-register-success.jsp";//登録成功画面
		} else {
			url = "user-register-failure.jsp";//登録失敗画面
		}

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
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
