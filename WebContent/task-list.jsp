<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.TaskCategoryStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示画面</title>
</head>
<body>
	<h1>タスク一覧表示画面</h1>
	<hr>
	<%
	List<TaskCategoryStatusBean> taskList = (List<TaskCategoryStatusBean>) request.getAttribute("taskList");
	%>

<!-- 	TaskCategoryStatusBean a = (TaskCategoryStatusBean)session.getAttribute("user");
	a.getUserId();
	String shumi = request.getParameter("shumi"); -->

	<table>
	<tr>
			<th align="left">タスク名　　　</th>
			<th align="left">カテゴリー情報　　　　　　</th>
			<th align="left">期限　　　　　　</th>
			<th align="left">担当者情報　　　　　　</th>
			<th align="left">ステータス情報　　　　　　</th>
			<th align="left">メモ　　　　　　</th>
		</tr>

		<%
			for (TaskCategoryStatusBean task : taskList) {
		%>
		<tr>
			<td>
				<%=task.getTaskName()%>
				<input type="hidden" name="updatedTask" value="<%=task.getTaskId()%>, <%=task.getTaskName()%>">
			</td>
			<td>
				<%=task.getCategoryName()%>
				<input type="hidden" name="category" value="<%=task.getCategoryId()%>, <%=task.getCategoryName()%>">
			</td>
			<td><%=task.getLimitDate()%></td>
			<td><%=task.getUserId()%></td>
			<td>
				<%=task.getStatusName()%>
				<input type="hidden" name="status" value="<%=task.getStatusCode()%>, <%=task.getStatusName()%>">
			</td>
			<td><%=task.getMemo()%></td>
			<td><form action="task-alter-servlet" method="POST">
					<input type="submit" value="変更">
				</form>
			<td>
				<form action="task-delete-servlet" method="POST">
					<input type="submit" value="削除">
				</form>
			</td>

		</tr>
		<%
			}
		%>
	</table>

	<br>
	<div>
		<form action="menu.jsp" method="POST">
			<input type="submit" value="メニュー画面へ">
		</form>
	</div>
</body>
</html>