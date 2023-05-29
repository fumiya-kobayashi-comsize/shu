<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.entity.UserBean, model.entity.CategoryBean, model.entity.StatusBean, model.entity.TaskCategoryStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css" /> -->
<title>タスク情報-編集フォーム</title>
</head>
<body>

	<h1>タスク情報-編集フォーム</h1>
	<hr>

	<form action="task-alter-confirm-servlet" method="post">
	<table border="1">
		<%
		TaskCategoryStatusBean task = (TaskCategoryStatusBean) session.getAttribute("task");
		UserBean user = (UserBean)session.getAttribute("user");
		%>
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td>
				<% List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList"); %>
				<select name="category">
				<option value="<%=task.getCategoryId() %>, <%=task.getCategoryName() %>"><%=task.getCategoryName() %></option>
				<%
					for (int i = 0; i < categoryList.size(); i++) {
						CategoryBean category = categoryList.get(i);
						if (category.getCategoryId() == task.getCategoryId()) {
							continue;
						} else {
				%>
						<option value="<%=category.getCategoryId() %>, <%=category.getCategoryName() %>"><%=category.getCategoryName() %></option>
					<%
						}
					}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<th>期限</th>
			<td><input type="text" value="<%=task.getLimitDate() %>" name="limitDate"></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><input type="text" value="<%=user.getUserName() %>" name="userName"></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td>
				<% List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList"); %>
				<select name="status">
				<option value="<%=task.getStatusCode() %>, <%=task.getStatusName() %>"><%=task.getStatusName() %></option>
				<%
					for (int i = 0; i < statusList.size(); i++) {
						StatusBean status = statusList.get(i);
						if (status.getStatusCode() == task.getStatusCode()) {
							continue;
						} else {
				%>
						<option value="<%=status.getStatusCode() %>, <%=status.getStatusName() %>"><%=status.getStatusName() %></option>
					<%
						}
					}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><input type="text" value="<%=task.getMemo() %>" name="memo"></td>
		</tr>
		<tr class="table-button">
			<td colspan="2"><input type="submit" value="変更する"></td>
		</tr>
	</table>
	</form>

	<form action="task-detail-servlet" method="post">
		<input type="hidden" value="<%=task.getTaskName() %>" name="taskName">
		<input type="submit" value="一覧表示へ">
	</form>

</body>
</html>