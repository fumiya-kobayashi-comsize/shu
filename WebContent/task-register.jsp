<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CategoryBean" import="java.util.List,model.entity.StatusBean"%>
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

	<form action = "task-add-servlet" method = "POST">
	タスク名:<input type = "text" name = "taskName" ><br>



	カテゴリ情報:<select name = "categoryId">
				<% for (CategoryBean category : categoryList) { %>
					<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
    			<% } %>
				</select><br>

	期限:<input type="text" name="limitDate" placeholder="YYYY-MM-DD"><br>
	<%String userId = (String) session.getAttribute("userId"); %>
	担当者情報:<%=userId %><br>

	ステータス情報:<select name = "statusCode">
					<% for (StatusBean status : statusList) { %>
					<option value="<%=status.getStatusCode()%>"><%=status.getStatusName()%></option>
    			<% } %>
				</select><br>

	メモ:<input type = "text" name = "memo"><br>

	<input type = "submit" value = "登録確認へ">

	</form>

</body>
</html>