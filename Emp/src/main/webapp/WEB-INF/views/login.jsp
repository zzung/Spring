<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='/emp'">
<sec:authorize access="isAnonymous()">
	${message}
	<form action="loginCheck" method="post">	<!-- post방식일때 모두 csrf 토큰 넣어주기
												전에는 security.xml에서 csrf : disabled로 해놨기 떄문에 안적어놨던것 -->
	<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
	아이디 : <input type=text name=id><br>
	비밀번호 : <input type=password name=pw><br>
	<input type=submit value="로그인">
	</form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<c:redirect url="/"/>
</sec:authorize>
</body>
</html>