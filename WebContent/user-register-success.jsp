<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
</head>
<body>
	<h1>登録完了</h1>
	<hr>
	<h2>新規ユーザを登録しました</h2>
	<%session.invalidate(); %>
	<form action="login.jsp" method="POST">
		<input type="submit" value="ログイン画面へ">
	</form>
</body>
</html>