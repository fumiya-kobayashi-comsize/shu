<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<title>商品情報-変更確認画面</title>
</head>
<body>

	<h1>商品情報-変更確認画面</h1>
	<hr>

	<h2>商品情報を以下の内容に変更します。よろしいですか？</h2>

	<% UserBean user = (UserBean) session.getAttribute("user"); %>
	<form action="task-alter-servlet" method="post">
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td>
				<%=request.getAttribute("taskName") %>
				<input type="hidden" value="<%=request.getAttribute("taskId") %>
				,<%=request.getAttribute("taskName") %>" name="updatedTask">
			</td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=request.getAttribute("categoryName") %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=request.getAttribute("limitDate") %></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td>
				<%=user.getUserName() %>
				<input type="hidden" value="<%=user.getUserId() %>
				,<%=user.getUserName() %>" name="user">
			</td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td>
				<%=request.getAttribute("statusName") %>
				<input type="hidden" value="<%=request.getAttribute("statusCode") %>
				,<%=request.getAttribute("statusName") %>" name="status">
			</td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=request.getAttribute("memo") %></td>
		</tr>
		<tr class="table-button">
			<td colspan="2"><input type="submit" value="変更する"></td>
		</tr>
	</table>
	</form>

	<form action="task-alter-form.jsp" method="post">
		<input type="submit" value="変更入力フォームへ">
	</form>

</body>
</html>