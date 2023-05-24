<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.ItemCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/item-detail-style.css" type="text/css" />
<meta charset="UTF-8">
<title>タスク削除完了画面</title>
</head>
<body>
	<h1>タスク削除完了画面</h1>
	<hr>

	<%
		TaskCategoryBean itemResult = (TaskCategoryBean) session.getAttribute("taskDetail");
	%>
	<h2>次のデータを削除しました。</h2>
	<br>
	<br>
			<table>
			<tr>
				<th>タスクID</th>
<%-- 				<td><%=taskDetail.getTaskId()%></td> --%>
			</tr>
			<tr>
				<th>タスク名</th>
<%-- 				<td><%=taskDetail.getTaskyName()%></td> --%>
			</tr>
			<tr>
				<th>カテゴリID</th>
<%-- 				<td><%=taskDetail.getCategoryId()%></td> --%>
			</tr>
			<tr>
				<th>期限</th>
<%-- 				<td><%=taskDetail.getLimitDate()%></td> --%>
			</tr>
			<tr>
				<th>ユーザーID</th>
<%-- 				<td><%=taskDetail.getUserId()%></td> --%>
			</tr>
			<tr>
				<th>ステータスコード</th>
<%-- 				<td><%=taskDetail.getStatusCode()%></td> --%>
			</tr>
			<tr>
				<th>メモ</th>
<%-- 				<td><%=taskDetail.getMemo()%></td> --%>
			</tr>
			<tr>
				<th>登録日時</th>
<%-- 				<td><%=taskDetail.getCreateDateTime()%></td> --%>
			</tr>
			<tr>
				<th>更新日時</th>
<%-- 				<td><%=taskDetail.getUpdateDateTime()%></td> --%>
			</tr>
		</table>

	<br>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="タスク一覧表示画面へ">
	</form>

</body>
</html>