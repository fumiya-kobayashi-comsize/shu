<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, model.entity.UserBean, java.util.List,model.entity.TaskCategoryStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="css/item-detail-style.css" type="text/css" /> -->
<meta charset="UTF-8">
<title>タスク削除確認画面</title>
</head>
<body>
	<%
		//リストの作成、セッションからタスクの詳細情報の受け取り
	 	List<TaskCategoryStatusBean> taskSelect = (List<TaskCategoryStatusBean>) session.getAttribute("taskSelect");
		//セッションからuserの受け取り
		UserBean user = (UserBean)session.getAttribute("user");
	%>

	<h1>タスク削除確認画面</h1>
	<hr>
	<h2>タスクを削除します。よろしいですか？</h2>
	<br>

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
        for (TaskCategoryStatusBean taskselect : taskSelect) {
    %>
		<tr>
			<td><%=taskselect.getTaskName()%></td>
			<td><%=taskselect.getCategoryName()%></td>
			<td><%=taskselect.getLimitDate()%></td>
			<td><%=user.getUserName() %></td>
			<td><%=taskselect.getStatusName()%></td>
			<td><%=taskselect.getMemo()%></td>
		</tr>
	<%
		}
	%>
	</table>

	<!-- 		task_idの送信		-->
	<form action="task-delete-servlet" method="POST">
		<input type="submit" value="削除する">
 		<input type="hidden" name="taskId" value="<%=session.getAttribute("taskId")%>">
	</form>

	<!-- 	メニュー画面へ戻る -->
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>