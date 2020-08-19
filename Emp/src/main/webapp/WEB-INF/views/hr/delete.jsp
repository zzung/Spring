<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee Delete</title>
</head>
<body>
<h1>회원 정보 삭제 </h1>
<input type="button" value="메인으로" onclick="location.href='/emp/hr/main'">
삭제하려는 <b>${emp.firstName} ${emp.lastName} </b>은(는)
<h3> ${count.empCount} </h3> 명의 매니저 이고 <h3> ${count.deptCount} </h3> 개의 부서를 책임지고 있습니다.
<h3>정말 삭제하시겠습니까 ?</h3>
<c:choose>
	<c:when test="${count.empCount eq 0}">
	<form action="./delete" method=post>
	<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type=hidden name=empId value="${emp.employeeId}">
	<input type=submit value="삭 제">
	<input type=reset value="취 소">
	</form>
	</c:when>
</c:choose>
</body>
</html>