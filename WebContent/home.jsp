<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/error.css" />" rel="stylesheet">
</head>
<body>
	<c:if test="${sessionScope.name == null }">
		<c:out value="${sessionScope.name }" />
		<c:redirect url="${request.contextPath}/logincontroller" />
	</c:if>

	<div id="container">
		<div id="alignright">
			<a href="${pageContext.request.contextPath}/AggregateController">役職別集計&emsp;&emsp;</a>
			<a
				href="${pageContext.request.contextPath}/UserController?action=LOGOUT">ログアウト</a>
		</div>
		<h1>一覧</h1>
		<c:if test="${not empty search_not_found}">
			<script>
				alert("Not found. Please try again");
			</script>
		</c:if>
		<h2 style="color: red; text-align: center">
			<c:if test="${not empty not_found}">
				<c:out value="${not_found }" />
			</c:if>
		</h2>
		<div>
				<form class="form-inline">
					<div class="col-xs-2">
						<label for="gender">姓:</label> <input type="text"
							class="form-control" placeholder="Enter family name"
							name="familyName" value="${param.familyName}">
					</div>
					<div class="col-xs-2">
						<label for="pwd">名:</label> <input type="text"
							class="form-control" placeholder="Enter first name"
							name="firstName" value="${param.firstName}"><br>
					</div>
					<div class="col-xs-2">
						<label for="author">役職:</label><br> <select
							class="form-control" name="authorityName">
							<option selected="selected" value="${param.authorityName}"><c:out
									value="${param.authorityName}" /></option>
							<c:forEach items="${listAuthority}" var="author">
								<option value='<c:out value="${author.authorityName}"/>'><c:out
										value="${author.authorityName}"></c:out></option>
							</c:forEach>
							<option value=""><c:out
									value="" /></option>
						</select>
					</div>

					<div>
						<br> <input type="hidden" name="action" value="SEARCH" />
						<c:url var="search" value="/UserController">
						</c:url>
						<c:url var="list" value="/ListController">
						</c:url>
						<button type="submit" class="btn btn-primary"
							onclick="form.action='${search}'">検索</button>
						<button class="btn btn-primary" onclick="form.action='${list}'">リスト</button>

					</div>

				</form>
				<a href="${pageContext.request.contextPath}/logincontroller">
						<button id = "align_ichiran" class="btn btn-primary">一覧</button>
				</a>
		</div>
		<div>
			<br>
			<table border="1" class="table table-striped table-bordered">
				<tr class="thead-dark">
					<th>No</th>
					<th>ユーザID</th>
					<th>氏名</th>
					<th>性別</th>
					<th>年齢</th>
					<th>役職</th>
					<th><button class="btn btn-primary"
							onclick="window.location.href= '${pageContext.request.contextPath}/UserController?action=ADD'">
							登録</button></th>
				</tr>
				<c:set var="i" value="${1}" />
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${i}</td>
						<td>${user.userId}</td>
						<td><c:if test="${user.admin ==1 }">
								<c:out value="*" />
							</c:if> ${user.firstName} ${user.familyName}</td>
						<td>${user.genderName}</td>
						<td>${user.age}</td>
						<td>${user.authorityName}</td>
						<td>
							<button class="btn btn-primary"
								onclick="window.location.href='${pageContext.request.contextPath}/UserController?action=EDIT&id=${user.userId }&name=${user.firstName} ${user.familyName}'">更新</button>
							|
							<button class="btn btn-primary"
								onclick="window.location.href='${pageContext.request.contextPath}/UserController?action=DELETE&id=${user.userId }&name=${user.firstName} ${user.familyName}'">削除</button>
						</td>
					</tr>
					<c:set var="i" value="${i+1}" />
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>