<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.TaskCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="css/item-detail-style.css" type="text/css" /> -->
<meta charset="UTF-8">
<title>タスク削除確認画面</title>
</head>
<body>
	<%
		taskBean taskDetail = (taskBean) session.getAttribute("taskDetail");
	%>

	<h1>タスク削除確認画面</h1>
	<hr>
	<h2>タスクを削除します。よろしいですか？</h2>
	<br>
		<table>
			<tr>
				<th>タスクID</th>
				<td><%=taskDetail.getTaskId()%></td>
			</tr>
			<tr>
				<th>タスク名</th>
				<td><%=taskDetail.getTaskyName()%></td>
			</tr>
			<tr>
				<th>カテゴリID</th>
				<td><%=taskDetail.getCategoryId()%></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><%=taskDetail.getLimitDate()%></td>
			</tr>
			<tr>
				<th>ユーザーID</th>
				<td><%=taskDetail.getUserId()%></td>
			</tr>
			<tr>
				<th>ステータスコード</th>
				<td><%=taskDetail.getStatusCode()%></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><%=taskDetail.getMemo()%></td>
			</tr>
			<tr>
				<th>登録日時</th>
				<td><%=taskDetail.getCreateDateTime()%></td>
			</tr>
			<tr>
				<th>更新日時</th>
				<td><%=taskDetail.getUpdateDateTime()%></td>
			</tr>
		</table>
	<br>

	<!-- 		タスクIDをサーブレットに送ってサーブレットからDAOに送って削除の処理をしてもらう。 -->
	<form action="ItemDeleteServlet" method="POST">
		<input type="hidden" name="task_id" value="<%=taskDetail.getTaskCode()%>">
		<input type="submit" value="削除する">
	</form>

	<br>

	<form action="task-list.jsp" method="POST">
		<input type="submit" value="タスク一覧表示画面へ">
	</form>
</body>
</html>