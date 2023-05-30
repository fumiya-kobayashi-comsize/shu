<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザ登録</title>
<script src="js/script.js"></script>
</head>
<body>
	<h1>新規ユーザ登録</h1>
	<hr>
	<h2>ユーザ情報を入力してください</h2>
	<form action="user-confirm-servlet" method="POST">
		ユーザID<br>
		<input type="text" name="userId" maxlength="24" required><br>
		パスワード<br>
		<input type="password" name="password" required><br>
		パスワードを再入力<br>
		<input type="password" name="passwordConfirm" required oninput="checkPasswordMatch()"><br>
		ユーザ名<br>
		<input type="text" name="userName" maxlength="20" required><br>

		<p id="passwordMatchMessage" style="color: red;"></p>

		<input type="submit" value="登録確認">
		<input type="reset" value="取消">
	</form>

	<br>
	<form action="login.jsp" method="POST">
		<input type="submit" value="ログイン画面へ">
	</form>
</body>
</html>
