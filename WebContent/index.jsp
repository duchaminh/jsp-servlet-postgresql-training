<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<title>ログイン</title>
	<style>
		#login_form {
			width: 300px;
			padding:20px;
			margin-top:10px;
			margin-right: auto;
			margin-bottom: 10px;
			margin-left: auto;
		}
	</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="margin:auto; text-align:center;">
		<h1>ログイン</h1>
		<h1 style = "text-align: center; color: red"> 
		<c:if test="${not empty msg}">
   			<c:out value="${msg}"/>
		</c:if> 
		</h1>
		<div id="login_form">
			<form action="usercontroller" method="POST">
				<table style="margin:auto; text-align:center;">
					<tr style="width:300px;">
						<td style="text-align:right; width:100px;">ユーザID：</td>
						<td><input type="text" name="userID" maxlength="8"></td>
					</tr>
					<tr style="width:300px;">
						<td style="text-align:right; width:100px;">パスワード：</td>
						<td><input type="password" name="password" maxlength="8"></td>
					</tr>
				</table>
				<input type="submit" value="ログイン" class = "btn btn-primary">
			</form>
		</div>
	</div>
</body>
</html>