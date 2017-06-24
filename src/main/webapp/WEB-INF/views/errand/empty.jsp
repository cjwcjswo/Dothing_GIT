<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
function sendMessage(){
	ws.send("심부름:${insertNum}:${insertTitle}:${insertImage}");
	location.href = "${pageContext.request.contextPath}/errand/errand";
}

$(function(){
		if(${insertNum} > 0){
		setTimeout("sendMessage()", 500);
		}else{
			location.href = "${pageContext.request.contextPath}/errand/errand";
		}
});
</script>
</head>
<body>

</body>
</html>