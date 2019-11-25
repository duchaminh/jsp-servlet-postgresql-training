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
	<c:if test="${sessionScope.name == null }">
		<c:out value = "${sessionScope.name }"/>
		<c:redirect url="${request.contextPath}/logincontroller"/>
	</c:if>
<h1>Add User</h1>
	<h1 style = "text-align: center; color: red"> 
		<c:if test="${not empty isoverlap}">
   			<c:out value="${isoverlap}"/>
		</c:if> 
		<c:if test="${not empty update_err}">
   			<c:out value="${update_err}"/>
		</c:if> 
		<c:if test="${not empty add_err}">
   			<c:out value="${add_err}"/>
		</c:if> 
	</h1>
	<h1><c:out value="${action}"/></h1>
	<form id = "adduser" action="${pageContext.request.contextPath}/UserController?action=${action}" method = "POST">
		
		<div class = "form-group">
			<label for="userid">User ID:</label>
			<c:if test="${action eq 'ADD' }">
				<input type = "text" class = "form-control" name = "userId" id ="userId" placeholder = "Enter userid" value = "${user.userId }"/>
			</c:if>
			<c:if test="${action eq 'EDIT' }">
				<label for="userid"><c:out value="${user.userId}"/></label>
				<input type = "hidden" name = "userId" value = "${user.userId}"/>
			</c:if>
		</div>
		
		<div class = "form-group">
			<label for="password">Password:</label>
			<input type = "password" class = "form-control" name = "password" placeholder = "Enter password" value = "${user.password }"/>
		</div>
		<div class = "form-group">
			<label for="family name">Family Name:</label>
			<input type = "text" class = "form-control" name = "familyName" placeholder = "Enter family name" value = "${user.familyName}"/>
		</div>
		<div class = "form-group">
			<label for="first name">First Name:</label>
			<input type = "text" class = "form-control" name = "firstName" placeholder = "Enter first name" value = "${user.firstName}"/>
		</div>
		<div class = "form-group">
			<label for="age">Age:</label>
			<input type = "text" class = "form-control" id ="age" name = "age" placeholder = "Enter age" value = "${user.age}"/>
		</div>
		<div class="form-group">
		  <label for="author">Authority:</label>
		  <select class="form-control" name = "authorityId">
		  	<c:choose>
			  <c:when test="${action eq 'EDIT' }">
			   <option selected="selected" value = '<c:out value="${user.authorityId}"/>'><c:out value="${user.authorityName}"></c:out></option>
			  </c:when>
			  <c:when test="${action eq 'ADD'}">
			    <option value = '-1' selected="selected">None</option>
			  </c:when>
			</c:choose>
			<c:forEach items="${listAuthority}" var = "author" >
				<c:choose>
					<c:when test="${action eq 'EDIT' }">
					   <c:if test = "${author.authorityId != user.authorityId }">
					   		<option value = '<c:out value="${author.authorityId}"/>'><c:out value="${author.authorityName}"></c:out></option>
					   </c:if>
					</c:when>
					<c:when test="${action eq 'ADD'}">
					    <option value = '<c:out value="${author.authorityId}"/>'><c:out value="${author.authorityName}"></c:out></option>
					</c:when>
				</c:choose>
			</c:forEach>
		  </select>
		</div>
		<div class="form-group">
		  <label for="gender">Gender:</label>
		  <select class="form-control" name = "genderId">
		  	<c:choose>
			  <c:when test="${action eq 'EDIT' }">
			   <option selected="selected" value = '<c:out value="${user.genderId}"/>'><c:out value="${user.genderName}"></c:out></option>
			  </c:when>
			  <c:when test="${action eq 'ADD'}">
			    <option value = '-1' selected="selected">None</option>
			  </c:when>
			</c:choose>
			<c:forEach items="${listGender}" var = "gender" >
				<c:choose>
				  <c:when test="${action eq 'EDIT' }">
				   <c:if test = "${gender.genderId != user.genderId }">
				   		<option value = '<c:out value="${gender.genderId}"/>'><c:out value="${gender.genderName}"></c:out></option>
				   </c:if>
				  </c:when>
				  <c:when test="${action eq 'ADD'}">
				    <option value = '<c:out value="${gender.genderId}"/>'><c:out value="${gender.genderName}"></c:out></option>
				  </c:when>
				</c:choose>
			</c:forEach>
		  </select>
		</div>
		<div class="checkbox">
		 	<label><input type="checkbox" value = "${user.admin }" name ="admin" id ="admin">Admin</label>
		</div>
		<button class ="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/logincontroller'">Back</button>
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