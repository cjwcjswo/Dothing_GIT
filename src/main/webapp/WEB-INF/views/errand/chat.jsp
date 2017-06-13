<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- SocketJS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sockjs.js"></script>



<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=e110b99859ef72282b675950ede50536"></script>

<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=APIKEY&libraries=services,clusterer,drawing"></script>

<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat/chat.css">
	
<script>
	var today = '<%= new java.text.SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date())%>'
	var sender = '<security:authentication property="principal.userId"/>';
	var errandsNum = ${errandsNum};
	var sock = new SockJS('/controller/websocket');
	
	//var sock = new SockJS('http://localhost:8080/'+errandsNum+'/websocket');
	$(function(){
		/* $('#send').click(function(){
			var msg = $('div textarea').val();
			alert('msg : ' + msg);
			alert('today : ' + today);
			//separator -> #/separator/#
			sock.send(errandsNum+"#/separator/#"+sender+"#/separator/#"+msg+"#/separator/#"+today);
		}); */
		
		$(document).on("click", "#send", function(){
			var msg = $('div textarea').val();
			alert('msg : ' + msg);
			//separator -> #/separator/#
			sock.send(errandsNum+"#/separator/#"+sender+"#/separator/#"+msg+"#/separator/#"+today);
			$('div textarea').val('');
			
		});
		
	});
	
	sock.onopen = function() {
	    $('#console').append('websocket opened' + '<br>');
	  
	  	//스크롤 맨 아래로
	 	document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
	};
	
	sock.onmessage = function(message) {
		var arr = message.data.split("#/separator/#");
		
		var str = '<div class="row"><div class="col-lg-12">' + 
		'<div class="media">' +
		'<a class="pull-left" href="#"> <img class="media-object img-circle" src="http://lorempixel.com/30/30/people/7/" alt=""></a>' +
		'<div class="media-body">'+
			'<h4 class="media-heading">'+ arr[1] +
				'<span class="small pull-right">'+ arr[3] +'</span>'+
			'</h4>'+ arr[2] +
			'</div></div></div></div><hr>';
	
	 	$('#chatList').append(str);
	 	
	 	//스크롤 맨 아래로
	 	document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
	 	
	 	
	    $('#console').append('receive message : ' + message.data + '<br>');
	};
	
	sock.onclose = function(event) {
	    $('#console').append('websocket closed : ' + event);
	};

</script>

</head>
<body>
		<div style="height: 15%;"></div>

	<div class="container" id="content">
		<div class="row" id="">
			<!--  필터  -->

		</div>
		<div class="row" style="width: 100%; height: 100%;">
			<div class="col-xs-4" id="map">
				<!-- 지도 삽입 부분 -->

				<iframe width="100%" height="500px" frameborder="0" scrolling="no"
					marginheight="0" marginwidth="0"
					src="https://maps.google.co.uk/maps?f=q&source=s_q&hl=en&geocode=&q=15+Springfield+Way,+Hythe,+CT21+5SH&aq=t&sll=52.8382,-2.327815&sspn=8.047465,13.666992&ie=UTF8&hq=&hnear=15+Springfield+Way,+Hythe+CT21+5SH,+United+Kingdom&t=m&z=14&ll=51.077429,1.121722&output=embed"></iframe>


			</div>
			<!-- class="col-xs-8 "  -->
			<div class="col-xs-8" style="padding-left: 50%; width: 100%;">

				<!-- 채팅  -->
				<div class="row">
					<div class="portlet portlet-default">
						<div class="portlet-heading"
							style="background-color: lightSkyBlue;">
							<div class="portlet-title">
								<h4>
									<i class="fa fa-circle text-green"></i> 심부름 제목
								</h4>
							</div>
							<div class="portlet-widgets">
								<div class="btn-group">
									<button type="button"
										class="btn btn-white dropdown-toggle btn-xs"
										data-toggle="dropdown">
										<i class="fa fa-circle text-green" style="color: blue;"></i>
										심부름 완료 <span class="caret"></span>
									</button>
									
								</div>
								<span class="divider"></span> <a data-toggle="collapse"
									data-parent="#accordion" href="#chat"><i
									class="fa fa-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<div>
								<div class="portlet-body chat-widget" id='chatList'
									style="overflow-y: auto; width: auto; height: 300px;">
									<div class="row">
										<div class="col-lg-12">
											<p class="text-center text-muted small">2017-06-01 오후
												12:34</p>

										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="media">
												<a class="pull-left" href="#"> <img
													class="media-object img-circle"
													src="http://lorempixel.com/30/30/people/1/" alt="">
												</a>
												<div class="media-body">
													<h4 class="media-heading">
														이태호 <span class="small pull-right">12:23 PM</span>
													</h4>
													<p>님 저 판교 유스페이스 쪽에 있는데 얼마나 걸려요?</p>
												</div>
											</div>
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-lg-12">
											<div class="media">
												<a class="pull-left" href="#"> <img
													class="media-object img-circle"
													src="http://lorempixel.com/30/30/people/7/" alt="">
												</a>
												<div class="media-body">
													<h4 class="media-heading">
														호갱님<span class="small pull-right">12:36 PM</span>
													</h4>
													<p>10분내 도착이요</p>
													<p>빅맥 라지세트 맞죠?</p>
												</div>
											</div>
										</div>
									</div>
									<hr>
								</div>
							</div>
							<div class="portlet-footer"
								style="background-color: lightSkyBlue;">
								<form role="form">
									<div class="form-group">
										<textarea class="form-control" placeholder="Enter message..."></textarea>
									</div>
									<div class="form-group">
										<button type="button" class="btn btn-warning pull-right" id="send">Send</button>
										<div class="clearfix"></div>
									</div>
								</form>
							</div>
							
							
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>
