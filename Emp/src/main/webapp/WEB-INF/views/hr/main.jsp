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
<a href="./count">1. 총 사원 인원 수 </a><br>
2. 부서별 인원 수
	<form method="get" action="./count">
	<input type="text" placeholder="부서번호 입력" name="deptId">
	<input type="submit" value="검색">
	</form>
<a href="./list">3. 전체 사원 조회 </a><br>
<a href="./insert">4. 신규  사원 정보 입력 </a><br>
<a href="./maxSalary">5.부서별 최고 급여자 조회</a><br>
<a href="./haveAboveAvgSalaryByDept">6.부서별 평균 이상 급여자 조회 </a><br><br>
<form action="hr/">
<input type=text name=empId>
hr?empId=100
hr/100
</form>
</body>
</html>