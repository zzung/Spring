<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Example</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<span id=ajaxarea>ajax 값</span>
	<br>
	<button id=ajax>ajax 실행</button>
	<script>
		$("#ajax").click("on",function(){
			/**var xhr = new XMLHttpRequest();
			var setArea = function(word){
				document.getElementById("ajaxarea").innerText = word;
			}
			xhr.open('get', 'json/example?userId=seunghee');
			xhr.setRequestHeader("content-type", "application/json");
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.readyState === xhr.LOADING){
					setArea("로딩중 ...");
				}
				if(xhr.readyState === xhr.DONE){
					if(xhr.status === 200 || xhr.status === 201){
						//setTimeout(setArea(xhr.responseText),5000);
						//방법1
						setTimeout(function(){
		                     setArea(xhr.responseText)
		                  },5000);
						//방법2
						//setTimeout(() => {
		                //     setArea(xhr.responseText)
		                //    }, 5000);
					}
				}
			}*/
		
	
		$.ajax({
			url : "json/example",
			type : "post",  /** get,post,put,delete 만가능 */
			data : {userId : "seunghee", "${_csrf.parameterName}" : "${_csrf.token}"},
			dataType : "text",
			success : function(result){
				$("#ajaxarea").text(result);
			},
			error : function(e){
				console.log(e.statusText);
			}
		});
			
		//show
		});
		$(document).ajaxStart(function(){
			
		});	
		
		//hide
		$(document).ajaxStop(function(){
			
		});
		
	</script>
</body>
</html>