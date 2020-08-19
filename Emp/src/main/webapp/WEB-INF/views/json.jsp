<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> AJAX </title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<button id=btn1>XMLHttpRequest 전송</button>
<button id=btn2>jQuery 전송</button>
<div id=result></div>
<script>
	var jsonVO = {name : "ABC", age:27};
	$(function(){
		
	$("#btn1").on("click",function(){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){ /** 콜백 함수 */
			if(xhr.readyState === xhr.DONE){
				if(xhr.status === 200 || xhr.status ===201){
					document.getElementById("result").innerText = xhr.responseText;
				}
			}
		}
	/** xhr.open('get','json/ex/userId=seunghee') */
		xhr.open('post','json/ex');
		xhr.setRequestHeader('content-type','application/json'); 
		xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
		xhr.send(JSON.stringify(jsonVO));
	/**
		* open(요청방식, url, true/false) : 서버로의 요청 준비
		* content-type : requestBody에 담길 내용 
		* application/json : ajax는 거의 json으로 처리한다는 것을 명시
		* get방식일때는 send()의 매개변수에 전달 x, 주소에 직접 전달
		  post방식일때는 send()의 매개변수에 넣어주기 (requestBody에 담길 데이터를 전송하는 용도)
		  xhr.send(jsonVO) ---X  xhr.send(JSON.stringify(jsonVO)) ---O
		    : 파라미터같은 string값이 되어야 하는데 jsonVO는 자바스크립트 객체 리터럴 이라서 스프링이 파싱을 안함
		  --> JSON객체를 문자열의 형태로 만드는 메서드 활용하기 : (JSON.stringify(객체) )*/
		
	});
	
	
	$('#btn2').on("click",function(){
		$.ajax({
			url : "json/ex",
			type : "get",
			data : jsonVO,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result){ <%-- result : 컨트롤러가 보낸거 --%>
				$("#result").text(result);
			},
			error : function(error){
				alert(error.statusText);
			}
		});
	});
	});
</script>
</body>
</html>