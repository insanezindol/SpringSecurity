<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function doLogin() {
		if($("#username").val() == "") {
			alert("아이디를 입력해주세요.");
			return;
		}
		if($("#password").val() == "") {
			alert("패스워드를 입력해주세요.");
			return;
		}
		frm.submit();
	}
</script>
<title>Login</title>
</head>
<body>

	<h1>login page</h1>

	<form name="frm" action="/login/loginCheck.do" method="post">
		<table>
			<tr>
				<td><input type="text" id="user_id" name="user_id" placeholder="아이디" /></td>
			</tr>
			<tr>
				<td><input type="password" id="user_pwd" name="user_pwd" placeholder="비밀번호" /></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="remember_me" name="remember_me" /> 로그인 상태 유지</td>
			</tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><input type="submit" value="로그인" onclick="javascript:doLogin();" /></td>
			</tr>
		</table>
	</form>
	
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

</body>
</html>