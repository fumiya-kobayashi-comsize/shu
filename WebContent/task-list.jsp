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

	<table>
	<tr>
			<th align="center">タスク名</th>
			<th align="center">カテゴリー情報</th>
			<th align="center">期限</th>
			<th align="center">担当者情報</th>
			<th align="center">ステータス情報</th>
			<th align="center">メモ</th>
		</tr>

		<%
			for (TaskCategoryStatusBean task : taskList) {
		%>
		<tr>
			<td>
				<%=task.getTaskName()%>
			</td>
			<td>
				<%=task.getCategoryName()%>
			</td>
			<td>
				<%=task.getLimitDate()%>
			</td>
			<td>
				<%=task.getUserId()%>
			</td>
			<td>
				<%=task.getStatusName()%>
			</td>
			<td>
				<%=task.getMemo()%>
			</td>
			<td>
				<form action="task-alter-confirm-servlet" method="GET">
					<input type="submit" value="変更">
					<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<input type="hidden" name="category" value="<%=task.getCategoryId()%>, <%=task.getCategoryName()%>">
					<input type="hidden" name="status" value="<%=task.getStatusCode()%>, <%=task.getStatusName()%>">

				</form>
			</td>
			<td>
				<form action="task-delete-confirm-servlet" method="GET">
					<input type="submit" value="削除確認">
					<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
<%-- 					<input type="hidden" name="taskList" value="<%=request.getAttribute("taskList")%>"> --%>
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