<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body>
	<% String userName = (String) session.getAttribute("userName"); %>
	<h1>ログアウト</h1>
	<hr>
	<h1>ログアウトしました</h1>
	<%session.invalidate(); %>
	<form action="login.jsp" method="POST">
		<input type="submit" value="ログイン画面へ">
	</form>
</body>
</html>