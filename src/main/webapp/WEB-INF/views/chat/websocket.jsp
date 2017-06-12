<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sockjs.js"></script>

<script type="text/javascript">

var sock = new SockJS('/controller/websocket');

sock.onopen = function() {
    $('#console').append('websocket opened' + '<br>');
};

sock.onmessage = function(message) {
    $('#console').append('receive message : ' + message.data + '<br>');
};

sock.onclose = function(event) {
    $('#console').append('websocket closed : ' + event);
};

function messageSend() {
    sock.send($('#message').val());
}

</script>

</head>

<body>

<input type="text" id="message" />

<input type="button" value="전송" onclick="messageSend();" />

<div id="console"></div>

</body>

</html>


