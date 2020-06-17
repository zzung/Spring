<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
<title> File Home </title>
</head>
<body>
<h2>File Upload/Download</h2>
<p><a href='<c:url value="/file/new"/>'># 업로드</a></p>
<p><a href='<c:url value="/file/list"/>'># 파일 전체 목록</a></p>
<form action="" name="form">
# 디렉토리 목록: <select id="dir">
		<option value="/">/
		<option value="/images">이미지
		<option value="/data">자료실
		<option value="/spring">스프링
		<option value="/general">공통
</select>
<input type=submit value="조회" id="form">
</form><br>
<form action='<c:url value="/file/info"/>'>
# 파일 정보 조회 : <input type=text  placeholder="파일 번호 입력" name=fileId>
<input type=submit value=조회>
</form>
<script type=text/javascript>
$(document).ready(function(){
	$("#form").click(function(){
		var dir = $("#dir option:selected").val();
		document.form.action="file/list"+dir;
	})
});
</script>
</body>
</html>