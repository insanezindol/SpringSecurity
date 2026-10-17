<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/style.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function doLogin() {
		if($("#user_id").val() == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if($("#user_pwd").val() == "") {
			alert("패스워드를 입력해주세요.");
			return false;
		}
		return true;
	}
</script>
<title>Login</title>
</head>
<body>

	<div class="card">
		<div class="logo">🔐</div>
		<h1 class="title">Welcome Back!</h1>
		<p class="subtitle">계정 정보를 입력하고 로그인하세요</p>

		<form name="frm" action="/login/loginCheck.do" method="post" onsubmit="return doLogin();">
			<div class="field">
				<label for="user_id">아이디</label>
				<input type="text" id="user_id" name="user_id" class="input" placeholder="아이디" autofocus />
			</div>
			<div class="field">
				<label for="user_pwd">비밀번호</label>
				<input type="password" id="user_pwd" name="user_pwd" class="input" placeholder="비밀번호" />
			</div>
			<label class="check">
				<input type="checkbox" id="remember_me" name="remember_me" /> 로그인 상태 유지
			</label>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>

		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
	</div>

	<p class="footer">Spring Boot · Spring Security Example</p>

</body>
</html>
