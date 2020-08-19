<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert/update page</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>${message eq 'update'? '정보 수정': '회원 가입' }</h1>
<form action="../member/${message}" method="post">
<input type=hidden name="${_csrf.parameterName}" value="${_csrf.token}">
<table border="1">
<tr>
	<th>Id</th>
	<td>
		<input type="text" id="userId" value="${member.userId}">
		<input type="button" id="checkId" value="중복검사">
	</td>
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
<input type="submit" value="입 력"  onsubmit=checkId()>
<input type="reset" value="초기화">
<input type="button" value="취 소" onclick="location.href='/emp'">
</th>
</tr>
</table>
<div id="loader">
	<img src='<c:url value="/resources/images/loader.gif" />' width="100" id=loadgif>
</div>
</form>
<script>
	$(function(){
		var success = false;
		//xmlHttpRequest
		/**
		$("#loadgif").hide();
		$("#checkId").on("click",function(){
			if($("#userId").val()==null){
				alert("아이디 중복검사를 실행해주세요.");
			}else{
				var xhr = new XMLHttpRequest();
				xhr.open('post','check');
				xhr.setRequestHeader("content-type","application/json");
				xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
				console.log("userId="+$("#userId").val());
				xhr.send($("#userId").val());
				
				xhr.onreadystatechange = function(){
					if(xhr.readyState === xhr.LOADING){ //로딩중
						$("#loadgif").show();
					}
					if(xhr.readyState === xhr.DONE){ //응답완료
						if(xhr.status === 200 || xhr.status ===201){ //응답 성공
							console.log(xhr.responseText);
							if(xhr.responseText == 1){ //중복된 경우
								alert("중복된 아이디 입니다. 아이디를 다시 입력해주세요!");
								$("#loadgif").hide();
								$("#userId").val()='';
							}else { //중복안된 경우
								alert("사용가능한 아이디 입니다.");
								$("#loadgif").hide();
							}
							success = true;
						} else{ //응답 실패
							alert("응답 오류");
							$("#loadgif").hide();
						}
					}
				}
			}
		});
		*/
		
		//jQuery
		$("#loadgif").hide();
		$("#checkId").on("click",function(){
			console.log($("#userId").val());
			if($("#userId").val()==null){
				alert("중복검사를 진행해주세요 !");
				return false; 
			} else{
				$.ajax({
					url : "check",
					type : "post",
					data : { userId:$("#userId").val(), 
							"${_csrf.parameterName}":"${_csrf.token}" },
					dataType : "json",
					success : function(result){ //ajax로 받아온 결과값
						$("#loadgif").show();
						if(result){
							alert("중복된 아이디 입니다. 아이디를 다시 입력해주세요 !");
							$("#loadgif").hide();
						}else{
							alert("사용가능한 아이디 입니다.");
							$("#loadgif").hide();
							$("#checkId").remove();
						}
						success = true;
					},
					error : function(e){
						alert("오류 발생");
						console.log($("#userId").val());
						console.log(e.statusText);
						return false;
					}
				});
			}
		});
		
		
		function checkId(){
			if(!success){
				alert("아이디 중복검사를 실행해주세요.");	
				return false;
			}
		};
		
	});
</script>
</body>
</html>