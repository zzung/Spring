<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join page</title>
</head>
<body>
<h1>회원 가입 페이지</h1>
<form action="../member/insert" method="post">
<table border="1">
<tr>
	<th>아이디</th>
	<td><input type="text" name="userId"></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="password"></td>
</tr>
<tr>
	<th>이름 </th>
	<td><input type="text" name="name"></td>
</tr>
<tr>
	<th>이메일</th>
	<td><input type="text" name="email"></td>
</tr>
<tr>
	<th>주소 </th>
	<td><input type="text" name="address"></td>
</tr>
<tr>
<th colspan=2>	
<input type="submit" value="가입하기">
<input type="reset" value="취소">
</th>
</tr>
</table>
</form>
</body>
</html>