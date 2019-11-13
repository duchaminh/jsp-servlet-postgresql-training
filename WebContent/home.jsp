<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		if(session.getAttribute("name") == null){
			response.sendRedirect("index.html");
		}
	%>
	<h1>Welcome</h1>
	<table border = "1" class = "table table-striped table-bordered">
			
			<tr class = "thead-dark">
				<th>No</th>
				<th>userid</th>
				<th>Name</th>
				<th>Sex</th>
				<th>Age</th>
				<th>Authority Name</th>
				<th><button class ="btn btn-primary" onclick="window.location.href= 'views/add-user.jsp'"> ADD </button></th>
			</tr>
			<c:set var="i" value="${1}" />
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${i}</td>
					<td>${user.userId}</td>
					<td>${user.firstName} ${user.familyName}</td>
					<td>${user.genderName}</td>
					<td>${user.age}</td>
					<td>${user.authorityName}</td>
					<td>Edit|Delete</td>
				</tr>
			<c:set var="i" value="${i+1}" />
			</c:forEach>
			
		</table>
</body>
</html>