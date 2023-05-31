<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザ登録確認</title>
</head>
<body>
	<h1>新規ユーザ登録確認</h1>
	<hr>
	<h2>以下の内容で登録しますか？</h2>
	<% UserBean user = (UserBean) session.getAttribute("newUser"); %>
	<form action="user-register-servlet" method="POST">
		<table>
			<tr>
				<th>ユーザID</th>
				<td><%=user.getUserId()%></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td>
					<%
						String password = user.getPassword();
						int characterCount = password.length();
						StringBuilder asterisks = new StringBuilder();
						for (int i = 0; i < characterCount; i++) {
							asterisks.append("*");
						}
					%> <%=asterisks.toString()%>
				</td>
			</tr>
			<tr>
				<th>ユーザ名</th>
				<td><%=user.getUserName()%></td>
			</tr>
		</table>
		<input type="submit" value="登録する">
	</form>
	<form action="user-register.jsp" method="POST">
		<input type="submit" value="戻る">
	</form>
</body>
</html>