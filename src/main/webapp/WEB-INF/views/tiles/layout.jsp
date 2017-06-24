<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DoThing</title>
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
.balloon {
	position: relative;
	display: inline-block;
}

.balloon span {
	display: inline-block;
	padding: 10px;
	color: #fff;
	background: gray;
	border-radius: 20px;
}

.balloon:after {
	content: '';
	position: absolute;
	width: 0;
	height: 0;
	border-style: solid;
}

.balloon.test_1:after, .balloon.test_2:after {
	border-width: 15px 10px;
	left: 50%;
	margin-left: -10px;
}

.balloon.test_3:after, .balloon.test_4:after {
	border-width: 10px 15px;
	top: 50%;
	margin-top: -10px;
}

.balloon.test_1:after {
	border-color: gray transparent transparent transparent;
	bottom: -25px;
}

.balloon.test_2:after {
	border-color: transparent transparent gray transparent;
	top: -25px;
}

.balloon.test_3:after {
	border-color: transparent gray transparent transparent;
	left: -25px;
}

.balloon.test_4:after {
	border-color: transparent transparent transparent gray;
	right: -25px;
}

#chatAlert {
	opacity: 0.9;
	position: fixed;
	left: 72%;
	margin-left: -125x;
	bottom: 50px;
	z-index: 9999;
	cursor: pointer;
}

.writeAlert {
	width: 500px;
	height: auto;
	position: fixed;
	left: 42%;
	margin-left: -125px;
	bottom: 100px;
	z-index: 9999;
	background-color: white;
	color: black;
	font-family: Calibri;
	font-size: 15px;
	padding: 10px;
	text-align: center;
	border-radius: 10px;
	-webkit-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	-moz-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
}

.notify {
	width: 400px;
	height: 50px;
	position: fixed;
	left: 45%;
	margin-left: -125px;
	bottom: 100px;
	z-index: 9999;
	background-color: rgba(255, 255, 255, 0.8);
	color: black;
	font-family: Calibri;
	font-size: 15px;
	padding: 10px;
	text-align: center;
	border-radius: 10px;
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
	ws = new SockJS("${pageContext.request.contextPath}/websocket");
	function sendAlert(message) {
		$(".notify").html(message);
		$('.notify').fadeIn(400).delay(5000).fadeOut(400);
	}
	settingWS();
	function settingWS() {
		ws.onclose = function() {
			if ("${currentId}" != "") {
				ws = new SockJS("${pageContext.request.contextPath}/websocket");
				settingWS();
			}
		}

		ws.onmessage = function(e) {
			var alertArr = e.data.split(':');
			if (alertArr[0] == "댓글") {
				sendAlert("<a href='${pageContext.request.contextPath}/errand/detailView?num=" + alertArr[1].split("번")[0] + "'>" + alertArr[1] + "</a>");

			} else if (alertArr[0] == "심부름") {
				//이미지가 없는경우
				var imgAttr = "";
				if (alertArr[3] == "EMPTY") {
					imgAttr = "${pageContext.request.contextPath}/resources/img/errands/img.png";
				} else {
					imgAttr = "${pageContext.request.contextPath}/errands/" + alertArr[1] + "/" + alertArr[3];
				}
				$(document).on("click", ".writeAlert", function() {
					location.href = "${pageContext.request.contextPath}/errand/detailView?num=" + alertArr[1];
				});
				$("#writeImg").attr("src", imgAttr);
				$("#writeDes").html("<h4>" + alertArr[1] + "번 심부름이 등록됬습니다.</h4> <br>" + alertArr[2]);
				$('.writeAlert').fadeIn(400).delay(5000).fadeOut(400);

			} else if (alertArr[0] == "선택") {
				sendAlert("<a href='${pageContext.request.contextPath}/errand/detailView?num=" + alertArr[1].split("번")[0] + "'>" + alertArr[1] + "</a>");
			} else if (alertArr[0] == "알림") {
				if ("${currentId}" == "") {
					$(document).on("click", "#chatAlert", function() {
						location.href = "${pageContext.request.contextPath}/errand/detailView?num=" + alertArr[1];
					});
					$("#balloon").html("<span>" + alertArr[2] + "</span>")
					$("#balloonImg").attr("src", "${pageContext.request.contextPath}/users/" + alertArr[4] + "/" + alertArr[3]);
					$('#chatAlert').fadeIn(400).delay(5000).fadeOut(400);
				}
			} 
		}
	}
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
			<!-- 글쓰기알림 -->
			<div class="writeAlert" style="display: none">
				<div class="col-md-6">
					<img class="lib-img-show" id="writeImg" src=""
						style="width: 100px; height: 100px">
				</div>
				<div class="col-md-6" id="writeDes"></div>
			</div>
			<!-- 일반 알림 -->
			<div class='notify' style="display: none"></div>
			<!-- 채팅 알림 -->
			<div class=row style="width: 400px; height: 200px; display: none"
				id="chatAlert">
				<div class="col-sm-4"
					style="line-height: 200px; text-align: center;">
					<img alt="" class="img-circle" id="balloonImg" width=120px;
						height=120px;
						src="${pageContext.request.contextPath}/assets/img/member-1.jpg"
						style="vertical-align: middle; max-width: 100%; max-height: 100%;">
				</div>
				<div class="col-sm-8">
					<div class="balloon test_3" id="balloon" style="margin-top: 35%">
					</div>
				</div>

			</div>
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>


			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>
</body>
</html>