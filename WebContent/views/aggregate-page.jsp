<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggregate-page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/error.css" />" rel="stylesheet">
</head>
<body>
	<c:if test="${sessionScope.name == null }">
		<c:out value = "${sessionScope.name }"/>
		<c:redirect url="${request.contextPath}/logincontroller"/>
	</c:if>
	
	<div id="alignright">
		<a href="${pageContext.request.contextPath}/logincontroller">一覧&emsp;&emsp;</a>
	</div>
	<h1>役職別集計</h1><br>
	<button id="alignrightbutton" class ="btn btn-primary" onclick="window.location.href= '${pageContext.request.contextPath}/AggregateController'"> 集計 </button>
	<br><br><br>
	<table border = "1" class = "table table-striped table-bordered">
			<tr class = "thead-dark">
				<th>No</th>
				<th>役職</th>
				<th>男の人数</th>
				<th>女の人数</th>
				<th>未登録の人数</th>
				<th>年齢別人数(0-19]</th>
				<th>年齢別人数[20)</th>
				<th>年齢別人数（未登録）</th>
			</tr>
			<c:set var="i" value="${1}" />
			<c:forEach items="${aggregate}" var="index">
				<tr>
					<td>${i}</td>
					<td>${index.authorityName}</td>
					<td>${index.countMale}</td>
					<td>${index.countFemale}</td>
					<td>${index.countGenderNone}</td>
					<td>${index.countAgeBeetweenZeroToNineTeen}</td>
					<td>${index.countAgeMoreThanTwenty}</td>
					<td>${index.countAgeNone}</td>	
				</tr>
			<c:set var="i" value="${i+1}" />
			</c:forEach>	
		</table>
</body>
</html>