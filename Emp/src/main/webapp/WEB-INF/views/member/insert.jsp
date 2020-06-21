<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert/update page</title>
</head>
<body>
<h1>${message eq 'update'? '정보 수정': '회원 가입' }</h1>
<form action="../member/${message}" method="post">
<table border="1">
<tr>
	<th>Id</th>
	<td><input type="text" name="userId" value="${member.userId}"></td>
</tr>
<tr>
	<th>Name </th>
	<td><input type="text" name="name" value="${member.name}"></td>
</tr>
<tr>
	<th>Password </th>
	<td><input type="password" name="password" value="${member.password}"></td>
</tr>
<tr>
	<th>Email</th>
	<td><input type="text" name="email" value="${member.email}"></td>
</tr>
<tr>
	<th>Address</th>
	<td><input type="text" name="address" value="${member.address}"></td>
</tr>
<tr>
<th colspan=2>	
<input type="submit" value="입 력">
<input type="reset" value="취 소">
</th>
</tr>
</table>
</form>

</body>
</html>