<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style = "text-align: center; color: red"> 
		<c:if test="${not empty success_msg}">
   			<c:out value="${success_msg}"/>
		</c:if> 
	</h1>
	<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/logincontroller'">Back to home</button>
</body>
</html>