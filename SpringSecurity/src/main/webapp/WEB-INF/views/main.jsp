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
<script type="text/javascript">
	function fnLogout() {
		$("#logoutForm").submit();
	}
</script>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<a href="javascript:fnLogout();">로그아웃</a>

	<form action="/login/logout.do" method="post" id="logoutForm" style="display: none;">
		<input type="text" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<p><sec:authentication property="principal.authorities"/></p>
	
	<p><sec:authorize access="hasRole('ROLE_DEV')">당신은 개발자 입니다.</sec:authorize></p>
	<p><sec:authorize access="hasRole('ROLE_ADMIN')">당신은 관리자 입니다.</sec:authorize></p>
	<p><sec:authorize access="hasRole('ROLE_USER')">당신은 일반사용자 입니다.</sec:authorize></p>
	
	<p><a href="/main/showUser.do">접속자 현황 보기</a></p>
	
</body>
</html>