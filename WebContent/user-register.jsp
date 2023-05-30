<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.UserBean"%>
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
	<% UserBean user = (UserBean) session.getAttribute("newUser"); %>
	<form action="user-confirm-servlet" method="POST">
		ユーザID<br>
		<input type="text" name="userId" maxlength="24" required
			<%if (!(user == null)) {%>
				value="<%=user.getUserId()%>"
			<%}%>
		><br>
		パスワード<br>
		<input type="password" name="password" required
			<%if (!(user == null)) {%>
				value="<%=user.getPassword()%>"
			<%}%>
		><br>
		パスワードを再入力<br>
		<input type="password" name="passwordConfirm" required oninput="checkPasswordMatch()"
			<%if (!(user == null)) {%>
				value="<%=session.getAttribute("passwordConfirm")%>"
			<%}%>
		><br>
		ユーザ名<br>
		<input type="text" name="userName" maxlength="20" required
			<%if (!(user == null)) {%>
				value="<%=user.getUserName()%>"
			<%}%>
		><br>

		<p id="passwordMatchMessage" style="color: red;"></p>

		<input type="submit" value="登録確認" id="submitBtn" disabled>
		<input type="reset" value="取消">
	</form>
	<br>
	<form action="login.jsp" method="POST">
		<input type="submit" value="ログイン画面へ">
	</form>
</body>
</html>
