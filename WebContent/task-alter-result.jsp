<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserBean, model.entity.TaskCategoryStatusBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<title>商品情報-変更登録結果画面</title>
</head>
<body>

	<h1>商品情報-変更登録結果画面</h1>
	<hr>

	<%
	TaskCategoryStatusBean tcs = (TaskCategoryStatusBean) request.getAttribute("tcs");
	UserBean user = (UserBean)session.getAttribute("user");
	int count = (int)request.getAttribute("count");

	if (count != 0) {
	%>
	<h2>次のデータを変更登録しました。</h2>

	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=tcs.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=tcs.getCategoryName() %>"></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><input type="text" value="<%=tcs.getLimitDate() %>" name="limitDate"></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><input type="text" value="<%=user.getUserName() %>" name="userName"></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=tcs.getStatusName() %>"></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><input type="text" value="<%=tcs.getMemo() %>" name="memo"></td>
		</tr>
	</table>

	<%
	} else {
	%>

	<h2>次のデータを変更登録できませんでした。</h2>

	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=tcs.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=tcs.getCategoryName() %>"></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><input type="text" value="<%=tcs.getLimitDate() %>" name="limitDate"></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><input type="text" value="<%=user.getUserName() %>" name="userName"></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=tcs.getStatusName() %>"></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><input type="text" value="<%=tcs.getMemo() %>" name="memo"></td>
		</tr>
	</table>

	<%
	}
	%>

	<form action="menu.jsp" method="post">
		<input type="submit" value="メニュー画面へ">
	</form>

</body>
</html>