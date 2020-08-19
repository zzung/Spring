<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> chatting </title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h2>안녕하세요  ${pageContext.session.id}님 !</h2>
	<h1>채팅방</h1>
	<p id=chatroom>채팅을 시작해주세요.</p>
	<br>
	<input type=text id=inputChat><br>
	<button id=open>채팅 시작</button>
	<button id=send>전송</button>
	<button id=finish>채팅 종료</button>
	<script>
		let ws; 
		$("#send").hide();
		$("#finish").hide();
		$("#open").on("click",function(){
			if(ws==null | ws==undefined){
				ws = new WebSocket("ws://" + location.host +"<c:url value='/websocket/chat.do/websocket' />");
				ws.onopen = function(){
					$("#chatroom").text("웹 소켓이 열렸습니다. 채팅을 시작합니다.");
					$("#open").hide();
					$("#send").show();
					$("#finish").show();
					setTimeout(function(){
						$("#chatroom").text("");
					},1000);
				};
			}else{
				$("#chatroom").html($("#chatroom").text()+"<br>웹 소켓이 이미 실행중입니다.");
			}
		});	
		
		$("#send").on("click",function(){
			if(ws==null | ws==undefined){
				alert("웹 소켓이 연결되지 않았습니다.");
			}else{
				ws.send($("#inputChat").val());
				$("#inputChat").val("");
				ws.onmessage = function(event){
					$("#chatroom").html($("#chatroom").html().replace(/\n/g,'<br>')+
							"<br>"+event.data);
				};
			}
		});
		
		$("#finish").on("click",function(){
			if(ws==null | ws==undefined){
				alert("웹 소켓이 연결되지 않았습니다.");
			}else{
				ws.close();
				ws.onclose = function(){
					$("#chatroom").text("채팅 종료 ---");
					$("#open").show();
					$("#send").hide();
					$("#finish").hide();
				}
				ws=null;
			}
		});
	</script>
</body>
</html>