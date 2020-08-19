<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
</head>
<body>
	<div class="container">
		<table border=1>
			<thead>
			<tr>
				<th>번호</th>
				<th>방 이름</th>
				<th>입장 버튼</th>
			</tr>
			</thead>				
		
			<tbody>
				<c:forEach var="room" items="${rooms}">
				<tr>
				<td>${room.roomId}</td>
				<td>${room.roomName}</td>
				<td><a href="/emp/multichat/room/${room.roomId}"><button id="b${room.roomId}">입장</button></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table><br><br>
		<a href="/emp/multichat/new"><button id=new>채팅방 개설</button></a>
	</div>
</body>
</html>