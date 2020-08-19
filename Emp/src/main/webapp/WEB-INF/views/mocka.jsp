<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mock_Data</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="wrap"></div>
	
	<script>
		$(function(){
			$.ajax({
				url : "resources/json/MOCK_DATA.json",
				dataType : "json",
				success : function(data){
					if(data.length > 0){
					var tb = $("<table />");
					for(var i in data){
						var $id= data[i].id;
						var	$first_name = data[i].first_name;
						var $last_name = data[i].last_name;
						var $email = data[i].email;
						var row = $("<tr />").append(
								$("<td />").text($id),
								$("<td />").text($first_name),
								$("<td />").text($last_name),
								$("<td />").text($email));
						tb.append(row);
					}
					$(".wrap").append(tb);
					}
				}
		});
		});
	
	
	</script>
</body>
</html>