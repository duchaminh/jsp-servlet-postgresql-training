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
</head>
<body>
	<c:if test="${sessionScope.name == null }">
		<c:out value = "${sessionScope.name }"/>
		<c:redirect url="${request.contextPath}/logincontroller"/>
	</c:if>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">JSP&SERVLET</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Home</a></li>
	      <li><a href="${pageContext.request.contextPath}/AggregateController">Tong Hop</a></li>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="#"><span class="glyphicon glyphicon-user"></span><c:out value = "${sessionScope.name }"/></a></li>
	      <li><a href="${pageContext.request.contextPath}/UserController?action=LOGOUT"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	    </ul>
	  </div>
	</nav>
	<h1>Aggregate-page</h1>
	<table border = "1" class = "table table-striped table-bordered">
			<tr class = "thead-dark">
				<th>No</th>
				<th>Authority Name</th>
				<th>Man</th>
				<th>Women</th>
				<th>Gender None</th>
				<th>Age(0-19]</th>
				<th>Age[20)</th>
				<th>Age None</th>
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
		<button class ="btn btn-primary" onclick="window.location.href= '${pageContext.request.contextPath}/AggregateController'"> AGGREGATE </button>
		<button class ="btn btn-primary" onclick="window.location.href= '${pageContext.request.contextPath}/logincontroller'"> Back to home </button>
</body>
</html>