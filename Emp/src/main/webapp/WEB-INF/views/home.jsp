<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Home Page</title>
</head>
<body>
<h1>Welcome Page</h1>
<sec:authorize access="isAnonymous()">
	<a href="login"># 로그인</a><br>
	<a href="member/insert"># 회원 가입</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="username"/> ${username}님 환영합니다.
	<form action=logout method=post>
	<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type=submit value="로그아웃">
	</form>
	<br>
	<a href="member/info?userId=<sec:authentication property="principal.username" />"># 마이페이지</a><br>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
		<a href="member/list"># 회원 목록 관리</a><br>
	</sec:authorize>
</sec:authorize>
<a href="file/"># 파일 업로드 페이지</a><br>
<a href="hr/main"># 사원 정보 페이지</a><br>

</body>
</html>