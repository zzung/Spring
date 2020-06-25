<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee Main </title>
</head>
<body>
<h2>Employee Main</h2>
<input type="button" value="홈으로" onclick="location.href='/emp'"><br>
<a href="./count"># 총 사원 인원 수 </a><br><br>
<form method="get" action="./count">
	# 부서별 인원 수   <input type="text" placeholder="부서번호 입력" name="deptId">
	<input type="submit" value="검색">
</form><br>
<form action="./view?employeeId=${employeeId}" method="get">
	# 사원 정보 조회   <input type="text" placeholder="사원번호 입력" name="employeeId">
	<input type="submit" value="검색">
</form><br>
<a href="./list"># 전체 사원 조회 </a><br><br>
<a href="./insert"># 신규  사원 정보 입력 </a><br><br>
<a href="./maxSalary"># 부서별 최고 급여자 조회</a><br><br>
<a href="./haveAboveAvgSalaryByDept"># 부서별 평균 이상 급여자 조회 </a>
</body>
</html>