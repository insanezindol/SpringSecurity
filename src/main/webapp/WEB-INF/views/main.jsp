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
<title>Home</title>
<script type="text/javascript">
	function fnLogout() {
		$("#logoutForm").submit();
	}
</script>
</head>
<body>
	<div class="card wide">
		<div class="header">
			<div>
				<h1 class="title">Hello, <sec:authentication property="name"/>! 👋</h1>
				<p class="subtitle">오늘도 좋은 하루 보내세요</p>
			</div>
			<button type="button" class="btn btn-outline" onclick="fnLogout();">로그아웃</button>
		</div>

		<form action="/login/logout.do" method="post" id="logoutForm" style="display: none;">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

		<div class="info-box">
			<span class="icon">⏰</span>
			<div>
				<div class="label">서버 시간</div>
				<div class="value">${serverTime}</div>
			</div>
		</div>

		<div class="info-box">
			<span class="icon">🛡️</span>
			<div>
				<div class="label">보유 권한</div>
				<div class="value"><span class="chip"><sec:authentication property="principal.authorities"/></span></div>
			</div>
		</div>

		<div class="badges">
			<sec:authorize access="hasRole('DEV')"><span class="badge badge-dev">💻 당신은 개발자 입니다.</span></sec:authorize>
			<sec:authorize access="hasRole('ADMIN')"><span class="badge badge-admin">👑 당신은 관리자 입니다.</span></sec:authorize>
			<sec:authorize access="hasRole('USER')"><span class="badge badge-user">🙂 당신은 일반사용자 입니다.</span></sec:authorize>
		</div>

		<div class="actions">
			<a class="btn btn-soft" href="/main/showUser.do">👥 접속자 현황 보기</a>
		</div>
	</div>

	<p class="footer">Spring Boot · Spring Security Example</p>
</body>
</html>
