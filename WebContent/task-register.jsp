<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.UserBean"
	import="java.util.List,model.entity.CategoryBean"
	import="java.util.List,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
		List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	%>
	<%
		List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	%>


	<h1>タスク登録画面</h1>
	<hr>


	<form action="task-register-confilm-servlet" method="POST">
		<table>
			<tr>
				<th>タスク名:</th>
				<td><input type="text" name="taskName"></td>
			</tr>


			<tr>

				<th>カテゴリ情報:</th>
				<td><select name="category">
						<%
							for (CategoryBean category : categoryList) {
						%>
						<option
							value="<%=category.getCategoryId()%>,<%=category.getCategoryName()%>"><%=category.getCategoryName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>

			<tr>
				<th>期限:</th>
				<td><input type="text" name="limitDate"
					placeholder="YYYY-MM-DD"></td>
			</tr>

			<%
				UserBean user = (UserBean) session.getAttribute("user");
			%>
			<%
				String userId = user.getUserId();
			%>
			<%
				String userName = user.getUserName();
			%>
			<tr>
				<th>担当者情報:</th>
				<td><%=userName%></td>
			</tr>

			<tr>
				<th>ステータス情報:</th>
				<td><select name="status">
						<%
							for (StatusBean status : statusList) {
						%>
						<option
							value="<%=status.getStatusCode()%>,<%=status.getStatusName()%>"><%=status.getStatusName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>

			<tr>
				<th>メモ:</th>
				<td><input type="text" name="memo"></td>
			</tr>


		</table>

		<input type="submit" value="タスク登録確認へ">

	</form>

</body>
</html>