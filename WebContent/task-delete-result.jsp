<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, model.entity.UserBean, java.util.List,model.entity.TaskCategoryStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/item-detail-style.css" type="text/css" />
<meta charset="UTF-8">
<title>タスク削除完了画面</title>
</head>
<body>
	<%
	 	List<TaskCategoryStatusBean> taskSelect = (List<TaskCategoryStatusBean>) session.getAttribute("taskSelect");
		//userセッションを受け取る。
		UserBean user = (UserBean)session.getAttribute("user");
		//削除完了件数を受け取る。
		int processingNumber = (Integer) request.getAttribute("processingNumber");

		//削除完了件数が１件以上なら、以下のコードを実行する。
		if (processingNumber > 0) {
	%>
			<h1>タスク削除完了画面</h1>
			<hr>
			<h2><%=request.getAttribute("processingNumber")%>件のタスクを削除しました。</h2>
			<br>
	<%
		}else{
	%>
			以下のタスクの削除に失敗しました。
	<%
		}
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
	<%	}
	%>
	</table>

	<!-- 	タスク一覧表示画面へ戻る -->
	<form action="task-list-servlet" method="POST">
			<input type="submit" value="タスク一覧表示画面へ">
	</form>
</body>
</html>