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
	<div>
	<form action="LoginServlet" method="POST">
		<table>
			<tr>
				<th>ユーザID</th>
				<td><input type="text" name="user_id" maxlength="24" required>
				<td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" maxlength="32" required>
				<td><br>
			</tr>
		</table>
		<br>
		<div class="button-container">
		<input type="submit" value="ログイン">
		<input type="reset" value="取消">
		</div>
	</form>
	</div>
</body>
</html>