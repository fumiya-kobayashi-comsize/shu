<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserBean, model.form.TaskCategoryStatusForm" %>
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

	<%
	TaskCategoryStatusForm task = (TaskCategoryStatusForm) session.getAttribute("tcsf");
	UserBean user = (UserBean)session.getAttribute("user");
	%>
	<form action="task-alter-servlet" method="post">
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td>
				<%=task.getTaskName()%>
				<input type="hidden" value="<%=task.getTaskId() %>,<%=task.getTaskName()%>" name="updatedTask">
			</td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td>
				<%=task.getCategoryName()%>
				<input type="hidden" value="<%=task.getCategoryId()%>,<%=task.getCategoryName()%>" name="category">
			</td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<%=task.getLimitDateStr()%>
				<input type="hidden" value="<%=task.getLimitDateStr()%>" name="limitDate">
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td>
				<%=user.getUserName() %>
				<input type="hidden" value="<%=user.getUserId()%>,<%=user.getUserName()%>" name="user">
			</td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td>
				<%=task.getStatusName()%>
				<input type="hidden" value="<%=task.getStatusCode()%>,<%=task.getStatusName()%>" name="status">
			</td>
		</tr>
		<tr>
			<th>メモ</th>
			<td>
				<%=task.getMemo()%>
				<input type="hidden" value="<%=task.getMemo()%>" name="memo">
			</td>
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