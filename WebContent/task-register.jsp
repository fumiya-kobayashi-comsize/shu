<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.entity.UserBean" import="java.util.List,model.entity.CategoryBean" import="java.util.List,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
<% List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList"); %>
<% List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList"); %>


	<h1>タスク登録画面</h1>
	<hr>


	<form action = "task-register-confilm-servlet" method = "POST">
	タスク名:<input type = "text" name = "taskName" ><br>



	カテゴリ情報:<select name = "category">
				<% for (CategoryBean category : categoryList) { %>
					<option value="<%=category.getCategoryId()%>,<%=category.getCategoryName()%>"><%=category.getCategoryName()%></option>
    			<% } %>
				</select><br>


	期限:<input type="text" name="limitDate" placeholder="YYYY-MM-DD"><br>
	<%UserBean user = (UserBean)session.getAttribute("user"); %>
				<%
					String userId = user.getUserId();
				%>
				<%
					String userName = user.getUserName();
				%>

	担当者情報:<%=userName%><br>

	ステータス情報:<select name = "status">
					<% for (StatusBean status : statusList) { %>
					<option value="<%=status.getStatusCode()%>,<%=status.getStatusName()%>"><%=status.getStatusName()%></option>
    			<% } %>
				</select><br>

	メモ:<input type = "text" name = "memo"><br>

	<input type = "submit" value = "タスク登録確認へ">

	</form>

</body>
</html>