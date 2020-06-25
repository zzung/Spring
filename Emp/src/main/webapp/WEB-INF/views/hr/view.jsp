<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee View </title>
</head>
<body>
<h2> 사원 정보 상세 조회 </h2>
<input type="button" value="메인으로" onclick="location.href='/emp/hr/main'">
<table border="1">
<tr>
	<th> Employee_id</th>
	<td> ${emp.employeeId}</td>
</tr>
<tr>
	<th> First_name</th>
	<td> ${emp.firstName} </td>
</tr>
<tr>
	<th> Last_name </th>
	<td> ${emp.lastName} </td>
</tr>
<tr>
	<th> Email </th>
	<td> ${emp.email} </td>
</tr>
<tr>
	<th> Phone_number </th>
	<td> ${emp.phoneNumber} </td>
</tr>
<tr>
	<th> Hire_date</th>
	<td> ${emp.hireDate} </td>
</tr>
<tr>
	<th> Job_id </th>
	<td> ${emp.jobTitle}(${emp.jobId}) </td>
</tr>
<tr>
	<th> Salary</th>
	<td> ${emp.salary} </td>
</tr>
<tr>
	<th> Commission_pct </th>
	<td> ${emp.commissionPct} </td>
</tr>
<tr>
	<th> Manager_id </th>
	<td> ${emp.managerName} (${emp.managerId}) </td>
</tr>
<tr>
	<th> Department_id </th>
	<td> ${emp.deptName} (${emp.departmentId}) </td>
</tr>
</table>
<a href="update?empId=${emp.employeeId}">수정하기</a>
<!--  <a href="update/${emp.employeeId}">수정하기</a>   -->
<a href="delete?empId=${emp.employeeId}">삭제하기</a>
</body>
</html>