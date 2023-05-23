<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<h1>タスク登録画面</h1>
	<hr>

	<form action = "task-add-servlet" method = "POST">
	タスク名:<input type = "text" name = "taskName" ><br>
	カテゴリ情報:<select name = "categoryName">


	</select><br>

	期限:<input type = "date" name = "limitDate"><br>

	担当者情報:<input type = "text" name = "userName"><br>

	ステータス情報:<select name = "statusCode">

	</select><br>

	メモ:<input type = "text" name = "memo">

	<input type = "submit" value = "登録">

	</form>

</body>
</html>