<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.TaskCategoryStatusBean"
	import="java.util.List,model.entity.CategoryBean"
	import="java.util.List,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録確認画面</title>
</head>
<body>

	<%
		TaskCategoryStatusBean tcs = (TaskCategoryStatusBean) session.getAttribute("tcs");
	%>

	<h1>本当に登録するの？？(／≧ω＼)</h1>
	<hr>
	<form action="task-add-servlet" method="POST">
		<table>
			<tr>
				<th>タスク名</th>
				<td><%=tcs.getTaskName()%></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>

				<td><%=tcs.getCategoryName()%></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><%=tcs.getLimitDate()%></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<%
					String userId = (String) session.getAttribute("userId");
				%>
				<%
					String userName = (String) session.getAttribute("userName");
				%>
				<td><%=userName%></td>
			</tr>

			<tr>
				<th>ステータス情報</th>
				<td><%=tcs.getStatusName()%></td>
			</tr>

			<tr>
				<th>メモ</th>
				<td><%=tcs.getMemo()%></td>
			</tr>
		</table>
		<input type="submit" value="変更する">
	</form>
	<form action="task-register.jsp" method="POST">
		<input type="submit" value="タスク登録画面へ">
	</form>
</body>
</html>