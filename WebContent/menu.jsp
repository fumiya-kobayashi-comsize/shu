<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>
	<h1>メニュー</h1>
	<hr>
	<% String userName = (String) session.getAttribute("userName"); %>
	<div class="button-container">
		<form action="task-register.jsp" method="GET">
			<input type="submit" value="タスク登録">
		</form>
		<form action="TaskShowServlet" method="POST">
			<input type="submit" value="タスク一覧表示">
		</form>
		<form action="logout.jsp" method="POST">
			<input type="submit" value="ログアウト">
		</form>
	</div>
</body>
</html>