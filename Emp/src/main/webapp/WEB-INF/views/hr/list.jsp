<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee List </title>
</head>
<body>
<h2> ${message eq "getEmpList" ? "사원 목록" : message eq "maxSalary" ? "부서별 최대 급여자 목록" :"부서별 평균 이상 급여자 목록"} </h2>
<input type="button" value="메인으로" onclick="location.href='/emp/hr/main'">
<a href="insert"> 신규 사원 정보 입력 </a><br>
<!-- 상대경로 /hr/이 앞에 자동으로 붙음, home/에서 찾으라고 할때는 ./insert라고 하면됌 -->
<!-- core태그 : 자바코드로 쓸수 있는것들, 프로젝트 컴파일이 되어있는 상태에서 자동으로 붙으니까 뭐가 바뀐다고해도 정상실행됨
	<a href='<c:url value="/hr/insert"/> 라고 붙여주기 , 상대경로가 쉬움 -->
<br><table border=1>
<tr>
	<th>Employee_id</th>
	<th>First_name</th>
	<th>Last_name</th>
	<th>Email</th>
	<th>Phone_number</th>
	<th>Hire_date</th>
	<th>Job_id</th>
	<th>Salary</th>
	<th>Commission_pct</th>
	<th>Manager_id</th>
	<th>Department_id</th>
</tr>
<c:forEach var="emp" items="${list}">
<tr>
	<td><a href="./view?employeeId=${emp.employeeId}">${emp.employeeId}</a></td>
	<td>${emp.firstName}</td>
	<td>${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.jobId}</td>
	<td>${emp.salary}</td>
	<td>${emp.commissionPct}</td>
	<td>${emp.managerId}</td>
	<td>${emp.departmentId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>