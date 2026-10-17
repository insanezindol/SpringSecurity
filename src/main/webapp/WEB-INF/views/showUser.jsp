<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/style.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js"></script>
<title>Show User</title>
</head>
<body>
	<div class="card wide">
		<div class="header">
			<div>
				<h1 class="title">Show User 👥</h1>
				<p class="subtitle">현재 접속 중인 사용자 현황</p>
			</div>
			<a class="btn btn-soft" href="/main/main.do">← 메인으로</a>
		</div>

		<div class="table-wrap">
			<table class="table">
				<thead>
					<tr>
						<th>Username</th>
						<th>SESSION ID</th>
						<th>Last Request</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="visitor" items="${visitors}">
						<tr>
							<td>${visitor.username}</td>
							<td><span class="chip">${visitor.sessionId}</span></td>
							<td>${visitor.lastRequest}</td>
							<td>
								<button class="icon-btn" type="button" data-session-id="${visitor.sessionId}" title="삭제">🗑️</button>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty visitors}">
						<tr>
							<td colspan="4" class="empty">표시할 접속자 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<p class="footer">Spring Boot · Spring Security Example</p>
</body>
</html>
