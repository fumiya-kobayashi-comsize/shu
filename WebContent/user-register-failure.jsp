<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録失敗</title>
</head>
<body>
	<h1>登録失敗</h1>
	<hr>
	<h2>新規ユーザの登録に失敗しました</h2>
	<%session.invalidate(); %>
	<form action="user-register.jsp" method="POST">
		<input type="submit" value="新規登録画面へ">
	</form>
</body>
</html>