<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>You are not authenticated</h1>
<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}'">Login page</button>
</body>
</html>