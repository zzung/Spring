<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee Count </title>
</head>
<body>
<h1> ${message eq "allEmpCount"? "총 사원 수 ":"부서별 사원 수 "} : ${count} 명</h1>
<input type="button" value="메인으로" onclick="location.href='/emp'">
<input type="button" value="뒤로" onclick="history.back(-1);"><br>
</body>
</html>