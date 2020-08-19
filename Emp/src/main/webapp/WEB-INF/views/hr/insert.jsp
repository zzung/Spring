<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Employee Insert </title>
</head>
<body>
<h2>사원정보 ${message eq "insert" ? "입력" : "수정"}</h2>
<input type="button" value="메인으로" onclick="location.href='/emp/hr/main'">
<c:choose>
<%--수정일때 --%>
<c:when test="${message eq update}">
<form action="./${message}" method=post >	 <!-- ./ =>hr , ../=>home -->
<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
<table border=1>
<tr>
	<th>Employeee_id</th>
	<td><input type=text name=employeeId required value="${emp.employeeId}" ${empty emp ? "" : "readonly"}>
	</td>
</tr>
<tr>
	<th>First_name</th>
	<td><input type=text name=firstName value="${emp.firstName}">
	</td>
</tr>
<tr>
	<th>Last_name</th>
	<td><input type=text name=lastName required value="${emp.lastName}">
	</td>
</tr>
<tr>
	<th>Email</th>
	<td><input type=text name=email required value="${emp.email}" ${empty emp ? "" : "readonly"}>
	</td>
</tr>
<tr>
	<th>Phone_number</th>
	<td><input type=text name=phoneNumber value="${emp.phoneNumber}">
	</td>
</tr>
<tr>
	<th>Hire_date</th>
	<td><input type=date name=hireDate value="${emp.hireDate}">
	</td>
</tr>
<tr>
	<th>Job_id</th>
	<td><select name=jobId>
		<c:forEach var="job" items="${jobList}">
		<option value="${job.jobId}"${emp.jobId eq job.jobId ? "selected" : ""}>${job.jobTitle}</option>	
		<!-- jobId랑 jobTitle이 키와 값으로 매핑이됨. EmpRepository에서 설정한 것 -->
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th>Salary</th>
	<td><input type=text name=salary value="${emp.salary}">
	</td>
</tr>
<tr>
	<th>Commission_pct</th>
	<td><input type=number name=commissionPct step=0.05 min=0 max=0.95 value="${emp.commissionPct}">
	</td>
</tr>
<tr>
	<th>Manager_id</th>
	<td><select name=managerId>
		<c:forEach var="man" items="${manList}">
		<option value="${man.managerId}" ${emp.managerId eq man.managerId ? "selected" : ""}> ${man.managerName}</option>
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th>Department_id</th>
	<td><select name=departmentId>
	<c:forEach var="dept" items="${deptList}">
	<option value="${dept.departmentId}" ${emp.departmentId eq dept.departmentId ? "selected" : ""}>${dept.departmentName}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th colspan=2><input type=submit value="수정">
	<input type=reset value="취소"></th>
</tr>
</table>
</form>
</c:when>
<%-- 유효성 검사 객체 emp넘겨주기 --%>
<%-- 입력일때 --%>
<c:otherwise>
	<form:form action="./${message}" method="post" modelAttribute="emp">
	<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
		<table border=1>
		<tr>
			<th>Employeee_id</th>
			<td><form:input path="employeeId" />	<%-- employeeId에서 에러 있으면 --%>
				<form:errors path="employeeId" /> 	<%--해당 메시지 띄우기 --%>
			</td>
		</tr>
		<tr>
			<th>First_name</th>
			<td><form:input path="firstName" />
				<form:errors path="firstName" /> 
			</td>
		</tr>
		<tr>
			<th>Last_name</th>
			<td><form:input path="lastName" />
				<form:errors path="lastName" /> 
			</td>
		</tr>
		<tr>
			<th>Email</th>
			<td><form:input path="email" />
				<form:errors path="email" /> 
			</td>
		</tr>
		<tr>
			<th>Phone_number</th>
			<td><form:input path="phoneNumber" />
				<form:errors path="phoneNumber" /> 
			</td>
		</tr>
		<tr>
			<th>Hire_date</th>
			<td><form:input path="hireDate" type="date" required="required" />
				<form:errors path="hireDate" /> 
			</td>
		</tr>
		<tr>
			<th>Job_id</th>
			<td><form:select path="jobId">
			
			<%--List안에 jobVO가 들어가 있을 경우 
				<form:options items="${jobList}" itemLabel="jobTitle" itemValue="jobId"/>
			--%>
			
			<%-- jobId랑 jobTitle이 키와 값으로(List<Map<>>)형태로 매핑이됨. EmpRepository에서 설정한 것 --%>
				<c:forEach var="job" items="${jobList}">
					<option value="${job.jobId}">${job.jobTitle}</option>	
				</c:forEach>
			</form:select>
			</td>
		</tr>
		<tr>
			<th>Salary</th>
			<td><form:input path="salary"/>
				<form:errors path="salary" /> 
			</td>
		</tr>
		<tr>
			<th>Commission_pct</th>
			
			<td><form:input path="commissionPct" type="number" step="0.05"/>
				<form:errors path="commissionPct" /> 
			</td>
		</tr>
		<tr>
			<th>Manager_id</th>
			<td><form:select path="managerId">
				<c:forEach var="man" items="${manList}">
					<option value="${man.managerId}">${man.managerName}</option>	
				</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<th>Department_id</th>
			<td><form:select path="departmentId">
				<c:forEach var="dept" items="${deptList}">
					<option value="${dept.departmentId}">${dept.departmentName}</option>	
				</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
		<th colspan=2><input type=submit value="입력">
		<input type=reset value="취소"></th>
		</tr>
		</table>
	</form:form>
</c:otherwise>
</c:choose>
</body>
</html>