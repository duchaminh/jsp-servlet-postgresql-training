<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${sessionScope.name == null }">
		<c:out value = "${sessionScope.name }"/>
		<c:redirect url="${request.contextPath}/logincontroller"/>
	</c:if>
	
<h1> Delete Confirm page</h1>
		<h1>Are You Sure You want To delete this user</h1>
		<div class = "form-group">
			<label for="userid">User ID: <c:out value="${param.id}"/></label><br>
			<label for="userid">User name: <c:out value="${param.name}"/></label>
		</div>
		<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/logincontroller'">Back</button>
		<button class = "btn btn-danger" type ="submit"  onclick="window.location.href='${pageContext.request.contextPath}/UserController?action=DELETE-CONFIRM&id=${param.id }&name=${param.name}'">Delete</button>
</body>
</html>