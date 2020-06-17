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
</sec:authorize>
<sec:authentication property="principal.username"/> 님 환영합니다.<br>
<br>
<sec:authorize access="isAuthenticated()">
<form action=logout method=post>
<input type=submit value="로그아웃">
</form>
</sec:authorize>
<a href="member/insert"># 회원 가입</a><br>
<a href="file/"># 파일 업로드 페이지</a><br>
<a href="hr/main"># 사원 정보 페이지</a><br>

</body>
</html>