<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<hr>
	<h2>ユーザIDとパスワードを入力してください</h2>
	<form action="login-servlet" method="POST">
		<table>
			<tr>
				<th>ユーザID</th>
				<td><input type="text" name="user_id" maxlength="24" required>
				<td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" required>
				<td><br>
			</tr>
		</table>
		<br>
		<input type="submit" value="ログイン">
		<input type="reset" value="取消">
	</form>
	<br>
	<form action="user-register.jsp" method="POST">
		<input type="submit" value="新規登録">
	</form>
</body>
</html>