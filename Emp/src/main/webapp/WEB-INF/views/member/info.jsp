<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
</head>
<body>
<h2>User information</h2>
<input type="button" value="메인으로" onclick="location.href='/emp'">
<input type="button" value="뒤로" onclick="history.back(-1);">
<table border=1>
	<tr>
		<th>Id</th>
		<td>${member.userId}</td>
	</tr>
	<tr>
		<th>Name</th>
		<td>${member.name}</td>
	</tr>
	<tr>
		<th>email</th>
		<td>${member.email}</td>
	</tr>
	<tr>
		<th>address</th>
		<td>${member.address}</td>
	</tr>
	<tr>
		<th>authorities</th>
		<td>${member.auth}</td>
	</tr>
</table>
<a href="update?userId=${member.userId}">정보 수정</a><br>
<form action="delete" method="post">
<br>비밀번호 입력 : <input type="password" name=pw> 
<input type=submit value="탈 퇴"><br>
</form>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
	<h2>계정 설정</h2>
		<form action="enabled" method="post">
		<input type="hidden" name="userId" value="${member.userId}" />
		<input type="checkbox" name= "enabled" /> 활성화
		<input type=submit value="적 용"><br>
		</form>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_MASTER')" >
	<h2>권한 변경</h2>
		<form action="changeAuth" method="post">
		<input type="hidden" name="userId" value="${member.userId}" />
		<select name=authority>
			<option value="USER" ${member.auth eq 'ROLE_USER' ? "selected" : ""}>USER</option>
			<option value="ADMIN" ${member.auth eq 'ROLE_ADMIN' ? "selected" : ""}>ADMIN</option>
			<option value="MASTER" ${member.auth eq 'ROLE_MASTER' ? "selected" : ""}>MASTER</option>
		</select>
			<input type=submit value="적 용"><br>
			</form>
</sec:authorize>


<script type="text/javascript">
	$(document).ready(function(){
	$(".delete").click(function(){
	if(confirm("탈퇴 하시겠습니까?")){
		return true;
	}else{
		return false;
	}
	})
	});
</script>

</body>
</html>