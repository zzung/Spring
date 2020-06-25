<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Member List</title>
</head>
<body>
<h2>Member List</h2>
<input type="button" value="메인으로" onclick="location.href='/emp'">
<form action="search">
	# search : <input type="text" placeholder="아이디 or 이름 입력" name="keyword">
	<input type="submit" value="검색">
</form>
<br>
<table border=1>
<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Address</th>
	<th>Authorities</th>
	<th>Enabled</th>
</tr>
<c:forEach var="mem" items="${memList}">
<c:set var="auth" value="${fn:substringAfter(mem.auth, '_') }" />

<tr>
	<td><a href="./info?userId=${mem.userId}">${mem.userId}</a></td>
	<td>${mem.name}</td>
	<td>${mem.email}</td>
	<td>${mem.address}</td>
	<td>${auth}</td>
	<td>${mem.enabled}</td>
</tr>
</c:forEach>

<tr>
	<td colspan=5>
	<h6>
	[<a href="list?page=1">처음</a>]
	<c:if test="${pageManager.nowBlock gt 1}">
		[<a href="list?page=${pageManager.startPage-1}">이전</a>]
	</c:if>
	<c:forEach var="i" begin="${pageManager.startPage}" end="${pageManager.endPage}">
		[<a href="list?page=${i}">${i}</a>]
	</c:forEach>
	<c:if test="${pageManager.nowBlock < pageManager.totalBlock}">
		[<a href="list?page=${pageManager.endPage+1}">다음</a>]
	</c:if>
	</h6>
	</td>
</tr>
</table>
</body>
</html>