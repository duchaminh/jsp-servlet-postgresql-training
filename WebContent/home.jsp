<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
	<h1>一覧</h1>
	<c:if test="${not empty delete_msg}">
	    <script>
	         alert("Delete success");
	    </script>
	</c:if>
	<div>
		<form class="form-inline" action="${pageContext.request.contextPath}/UserController?tion=SEARCH" method = "GET">
		    <label for="gender">Family Name:</label>
		    <input type="text" class="form-control" placeholder="Enter family name" name="familyName">
		    <label for="pwd">First Name:</label>
		    <input type="text" class="form-control" placeholder="Enter first name" name="firstName"><br>
		    <div class="form-group">
			  <label for="author">Authority:</label>
			  <select class="form-control" name = "authorityId">
				<c:forEach items="${listAuthority}" var = "author" >
					<option value = '<c:out value="${author.authorityId}"/>'><c:out value="${author.authorityName}"></c:out></option>
				</c:forEach>
			  </select>
			</div>
			<input type="hidden" name="action" value="SEARCH" />
		    <button type="submit" class="btn btn-primary">Search</button>
  		</form>
	</div>
	
	<div>
		<table border = "1" class = "table table-striped table-bordered">
			<tr class = "thead-dark">
				<th>No</th>
				<th>user id</th>
				<th>Name</th>
				<th>Sex</th>
				<th>Age</th>
				<th>Authority Name</th>
				<th><button class ="btn btn-primary" onclick="window.location.href= '${pageContext.request.contextPath}/UserController?action=ADD'"> ADD </button></th>
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
					<td>
					<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/UserController?action=EDIT&id=${user.userId }&name=${user.firstName} ${user.familyName}'">Edit</button>
					|
					<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/UserController?action=DELETE&id=${user.userId }&name=${user.firstName} ${user.familyName}'">DELETE</button>
					</td>		
				</tr>
			<c:set var="i" value="${i+1}" />
			</c:forEach>	
		</table>
	</div>
</body>
</html>