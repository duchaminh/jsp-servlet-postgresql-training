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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
<link href="<c:url value="/resources/css/error.css" />" rel="stylesheet">
</head>
<body>
<h1>Add User</h1>
	<h1 style = "text-align: center; color: red"> 
		<c:if test="${not empty isoverlap}">
   			<c:out value="${isoverlap}"/>
		</c:if> 
	</h1>
	<form id = "adduser" action="${pageContext.request.contextPath}/UserController" method = "POST">
		<div class = "form-group">
			<label for="userid">User ID:</label>
			<input type = "text" class = "form-control" name = "userId" id ="userId" placeholder = "Enter userid"/>
		</div>
		<div class = "form-group">
			<label for="password">Password:</label>
			<input type = "password" class = "form-control" name = "password" placeholder = "Enter password"/>
		</div>
		<div class = "form-group">
			<label for="family name">Family Name:</label>
			<input type = "text" class = "form-control" name = "familyName" placeholder = "Enter family name"/>
		</div>
		<div class = "form-group">
			<label for="first name">First Name:</label>
			<input type = "password" class = "form-control" name = "firstName" placeholder = "Enter first name"/>
		</div>
		<div class = "form-group">
			<label for="age">Age:</label>
			<input type = "text" class = "form-control" name = "age" placeholder = "Enter age"/>
		</div>
		<div class="form-group">
		  <label for="author">Authority:</label>
		  <select class="form-control" name = "authorityId">
		    <sql:setDataSource var = "db" driver = "org.postgresql.Driver" url ="jdbc:postgresql://localhost/training" user ="postgres" password="toank21"/>
			<sql:query var="rs" dataSource="${db}">SELECT authority_id,authority_name FROM "mst_role"</sql:query>
			<c:forEach items="${rs.rows}" var = "row" >
				<option value = '<c:out value="${row.authority_id}"/>'><c:out value="${row.authority_name}"></c:out></option>
			</c:forEach>
		  </select>
		</div>
		<div class="form-group">
		  <label for="gender">Gender:</label>
		  <select class="form-control" name = "genderId">
		    <sql:setDataSource var = "db" driver = "org.postgresql.Driver" url ="jdbc:postgresql://localhost/training" user ="postgres" password="toank21"/>
			<sql:query var="rs" dataSource="${db}">SELECT gender_name,gender_id FROM "mst_gender"</sql:query>
			<c:forEach items="${rs.rows}" var = "row" >
				<option value = '<c:out value="${row.gender_id}"/>'><c:out value="${row.gender_name}"></c:out></option>
			</c:forEach>
		  </select>
		</div>
		<div class="checkbox">
		  <label><input type="checkbox" value="" name ="admin">Admin</label>
		</div>
		<button class = "btn btn-primary" type ="submit">Save</button>
    </form>
   <!-- modal -->
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">	
            <div class="modal-body">
                <p>Are You Sure You want To submit The Form</p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close This Modal</button>
                <button class="btn-primary btn" id="SubForm">Confirm and Submit The Form</button>
            </div>
        </div>
    </div>
	</div>
   <script src="<c:url value="/resources/js/add-user.js" />"></script> 
</body>

</html>