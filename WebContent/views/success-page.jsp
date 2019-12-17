<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complete page</title>
</head>
<body>
	<h1 style = "text-align: center; color: blue"> 
		<c:if test="${not empty success_msg}">
   			<c:out value="${success_msg}"/><br>
   			<c:out value="${success_msg}しました"/>
		</c:if> 
	</h1>
	
	<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/logincontroller'">一覧へ戻る</button>
</body>
</html>