<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-select.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user.style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery.mCustomScrollbar.css"
	type="text/css">
<style>
/**
알림 CSS
**/
.notify {
	width: 250px;
	height: 20px;
	height: auto;
	position: fixed;
	left: 50%;
	margin-left: -125px;
	bottom: 100px;
	z-index: 9999;
	background-color: #383838;
	color: #F0F0F0;
	font-family: Calibri;
	font-size: 15px;
	padding: 10px;
	text-align: center;
	border-radius: 2px;
	-webkit-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	-moz-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- SocketJS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/sockjs.js"></script>
<script>
	var ws = null;
	$(function(){
		ws = new SockJS("${pageContext.request.contextPath}/websocket");

		ws.onmessage = function(e){
			var alertArr = e.data.split(':');
			if(alertArr[0] == "댓글"){
				$(".notify").html(alertArr[1]);
				$('.notify').fadeIn(400).delay(3000).fadeOut(400);
			}
		}
	})
</script>
</head>
<body>
	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<div id="header">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>
			<div class='notify' style='display: none'></div>
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>
</body>
</html>