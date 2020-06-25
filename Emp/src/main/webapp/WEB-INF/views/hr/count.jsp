<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee Count </title>
</head>
<body>
<h2> ${message eq "allEmpCount"? "총 사원 수 ":"부서별 사원 수 "} --> ${count} 명</h2>
<input type="button" value="메인으로" onclick="location.href='/emp/hr/main'">
</body>
</html>