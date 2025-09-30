<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js"></script>
<title>Home</title>
</head>
<body>
	<h1>Show User</h1>

	<table class="table">
		<thead>
			<tr>
				<th>Username</th>
				<th>SESSION ID</th>
				<th>Last Request</th>
				<th>Delete</th>
			</tr>
		</thead>
		<thead>
			<c:forEach var="visitor" items="${visitors}">
				<tr>
					<td>${visitor.username}</td>
					<td>${visitor.sessionId}</td>
					<td>${visitor.lastRequest}</td>
					<td>
						<button class="btn btn-default btn-xs" type="button" data-session-id="${visitor.sessionId}">
							<i class="fa fa-trash" aria-hidden="true"></i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</thead>
	</table>
	
</body>
</html>