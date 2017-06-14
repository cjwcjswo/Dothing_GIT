<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Spotter - Universal Directory Listing HTML Template</title>

<link href="assets/fonts/font-awesome.css" rel="stylesheet"
	type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/chat.css"
	type="text/css">


<!-- SocketJS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sockjs.js"></script>
<!-- jQuery library -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script>
	var today = '<%= new java.text.SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date())%>'
	var sender = '<security:authentication property="principal.userId"/>';
	var errandsNum = ${errandsNum};
	var sock = new SockJS('/controller/websocket');

	$(function(){
		alert(44);
		$(document).on("click", "#sendBtn", function(){
			var msg = $('#inputText').val();
			//separator -> #/separator/#
			sock.send(errandsNum+"#/separator/#"+sender+"#/separator/#"+msg+"#/separator/#"+today);
			$('#inputText').val('');		
		});
		
	});
	
	sock.onopen = function() {
	    $('#console').append('websocket opened' + '<br>');
	  
	  	//스크롤 맨 아래로
	 	document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
	};
	
	sock.onmessage = function(message) {
		var arr = message.data.split("#/separator/#");
		
		var str = '<div class="row msg_container base_receive">'+
			'<div class="col-md-2 col-xs-2 avatar">'+
		'<img'+
			' src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"'+
			' class=" img-responsive ">'+
	'</div>'+
	'<div class="col-xs-10 col-md-10">'+
		'<div class="messages msg_receive">'+
			'<p>'+arr[2]+'</p>'+
			'<time datetime="2009-11-13T20:00">'+arr[3]+'</time>'+
		'</div></div></div>';
	
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

<body onunload=""
	class="page-subpage page-item-detail navigation-off-canvas"
	id="page-top">

	<!-- end Navigation-->
	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>Navigation</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->
		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="redefine-search">
						<a href="#redefine-search-form" class="inner"
							data-toggle="collapse" aria-expanded="false"
							aria-controls="redefine-search-form"> <span class="icon"></span>
							<span>Redefine Search</span>
						</a>
					</div>
					<ol class="breadcrumb">
						<li><a href="index-directory.html"><i class="fa fa-home"></i></a></li>
						<li><a href="#">Page</a></li>
						<li class="active">Detail</li>
					</ol>
					<!-- /.breadcrumb-->
				</div>
				<!-- /.container-->
			</div>
			<!-- /.breadcrumb-wrapper-->
		</section>
		<!--end Sub Header-->

		<!--Page Content-->
		<div id="page-content">

			<div id="map-detail"></div>
			<section class="container">
				<div class="row">
					<!--Item Detail Content-->
					<div class="col-md-9">
						<section class="block" id="main-content">
							<header class="page-title">
								<div class="title">
									<h1>심부름 제목</h1>
									<figure>심부름 위치
									</figure>
								</div>
								<div class="info">
									<div class="type">
										<i><img
											src="assets/icons/restaurants-bars/restaurants/restaurant.png"
											alt=""></i> <span>#음식배달 #아무거나빨리 #으아</span>
									</div>
								</div>
							</header>
							<div class="row">
								<!--Detail Sidebar-->
								<aside class="col-md-4 col-sm-4" id="detail-sidebar">
									<!--Contact-->
									<section>
										<header>
											<h3>Contact</h3>
										</header>
										<address>

											<figure>
												<div class="info">
													<i class="fa fa-child"></i> <span>심부름 요청자 아이디 </span>
												</div>
												<div class="info">
													<i class="fa fa-calendar"></i> <span>심부름 요청일시</span>
												</div>
												<div class="info">
													<i class="fa fa-globe"></i> <span>심부름 상세 위치</span>
												</div>
											</figure>
										</address>
									</section>
									<!--end Contact-->
									<!--Rating-->
									<section class="clearfix">
										<header class="pull-left">
											<a href="#reviews" class="roll"><h3>평점</h3></a>
										</header>
										<figure class="rating big pull-right" data-rating="4"></figure>
									</section>
									<!--end Rating-->
									<!--Events-->
									<section>
										<header>
											<h3>해시태그 리뷰</h3>
										</header>
										<figure>
											<div class="expandable-content collapsed show-60"
												id="detail-sidebar-event">
												<div class="content">
													<p>#믿을만함 #착함 #호갱 #돈많이줌 #배달빠름 #믿을만함 #착함 #호갱 #돈많이줌 #배달빠름
														#믿을만함 #착함 #호갱 #돈많이줌 #배달빠름 #믿을만함 #착함 #호갱 #돈많이줌 #배달빠름</p>
												</div>
											</div>
											<a href="#" class="show-more expand-content"
												data-expand="#detail-sidebar-event">더보기</a>
										</figure>
									</section>
									<!--end Events-->

								</aside>
								<!--end Detail Sidebar-->
								<!--Content-->
								<div class="col-md-8 col-sm-8">
									<section>
										<article class="item-gallery">
											<div class="owl-carousel item-slider">
												<div class="slide">
													<img src="assets/img/items/1.jpg" data-hash="1" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/2.jpg" data-hash="2" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/3.jpg" data-hash="3" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/4.jpg" data-hash="4" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/5.jpg" data-hash="5" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/6.jpg" data-hash="6" alt="">
												</div>
												<div class="slide">
													<img src="assets/img/items/7.jpg" data-hash="7" alt="">
												</div>
											</div>
											<!-- /.item-slider -->
											<div class="thumbnails">
												<span class="expand-content btn framed icon"
													data-expand="#gallery-thumbnails">More<i
													class="fa fa-plus"></i></span>
												<div class="expandable-content height collapsed show-70"
													id="gallery-thumbnails">
													<div class="content">
														<a href="#1" id="thumbnail-1" class="active"><img
															src="assets/img/items/1.jpg" alt=""></a> <a href="#2"
															id="thumbnail-2"><img src="assets/img/items/2.jpg"
															alt=""></a> <a href="#3" id="thumbnail-3"><img
															src="assets/img/items/3.jpg" alt=""></a> <a href="#4"
															id="thumbnail-4"><img src="assets/img/items/4.jpg"
															alt=""></a> <a href="#5" id="thumbnail-5"><img
															src="assets/img/items/5.jpg" alt=""></a> <a href="#6"
															id="thumbnail-6"><img src="assets/img/items/6.jpg"
															alt=""></a> <a href="#7" id="thumbnail-7"><img
															src="assets/img/items/7.jpg" alt=""></a>
													</div>
												</div>
											</div>
										</article>
										<!-- /.item-gallery -->
										<article class="block">
											<header>
												<h2>상세설명</h2>
											</header>
											<p>제 회사까지 서류좀 보내주세요 배고파 디질거가타요 브라랄라라ㅏ라라 우얼나올농라ㅓ노아ㅓ로나ㅓ와ㅓ</p>
										</article>
										<article class="block">
											<header>
												<h2>돈 내용</h2>
											</header>
											<ul class="bullets">

												<li>물품 가격 :</li>
												<li>심부름가격 :</li>

												<li>Total :</li>
											</ul>
										</article>



										<!-- /.block -->
										<article class="block">
											<header>
												<h2>원하는 도착 시간</h2>
											</header>
											<dl class="lines">
												<dt>2017-06-12</dt>
												<dd>08:00 am - 11:00 pm</dd>

											</dl>
										</article>
										<!-- /.block -->
									</section>
									<!--Reviews-->
									<section class="block" id="reviews">
										<header class="clearfix">
											<h2 class="pull-left">심부름 요청 댓글</h2>
											<a href="#write-review"
												class="btn framed icon pull-right roll">댓글로 심부름 신청하기 <i
												class="fa fa-pencil"></i>
											</a>
										</header>
										<!--   <article class="clearfix overall-rating">
                                                <strong class="pull-left">Over Rating</strong>
                                                <figure class="rating big color pull-right" data-rating="4"></figure>
                                                /.rating
                                            </article>/.overall-rating -->
										<section class="reviews">
											<article class="review">
												<figure class="author">
													<img src="assets/img/default-avatar.png" alt="">
													<div class="date">도착 예정 시간</div>
												</figure>
												<!-- /.author-->
												<div class="wrapper">
													<h5>심부름꾼이름</h5>
													<figure class="rating big color" data-rating="4"></figure>
													<a href="#" class="btn framed icon pull-right roll">선택<i
														class="fa fa-check"></i></a>
													<p>누구보다 빠르게 갑니다. 제 별명이 피깣츛츛츄츄ㅠ</p>


												</div>
												<!-- /.wrapper-->
											</article>
											<!-- /.review -->

										</section>
										<!-- /.reviews-->
									</section>
									<!-- /#reviews -->
									<!--end Reviews-->
									<!--Review Form-->
									<section id="write-review">
										<header>
											<h2>심부름 댓글 등록</h2>
										</header>
										<form id="form-review" role="form" method="post" action="?"
											class="background-color-white">
											<div class="row">
												<div class="col-md-8">
													<div class="form-group">
														<label for="form-review-name">아이디</label> <input
															type="text" class="form-control" id="form-review-name"
															name="form-review-name" required="">
													</div>
													<!-- /.form-group -->
													<div class="form-group">
														<label for="form-review-email">Email</label> <input
															type="email" class="form-control" id="form-review-email"
															name="form-review-email" required="">
													</div>
													<!-- /.form-group -->
													<div class="form-group">
														<label for="form-review-message">댓글 입력</label>
														<textarea class="form-control" id="form-review-message"
															name="form-review-message" rows="3" required=""
															placeholder=""></textarea>
													</div>
													<div class="form-group">
														<label for="form-review-email">도착예정시간</label> <input
															type="datetime-local" class="form-control"
															name="arrivalTime" />
													</div>
													<!-- /.form-group -->
													<div class="form-group">
														<button type="submit" class="btn btn-default">등록하기</button>
													</div>
													<!-- /.form-group -->
												</div>


											</div>
										</form>
										<!-- /.main-search -->
									</section>
									<!--end Review Form-->
								</div>
								<!-- /.col-md-8-->
							</div>
							<!-- /.row -->
						</section>
						<!-- /#main-content-->
					</div>
					<!-- /.col-md-8-->
					<!--Sidebar-->
					<div class="col-md-3">
						<aside id="sidebar">
							<section>
								<header>
									<h2>New Places</h2>
								</header>
								<a href="item-detail.html" class="item-horizontal small">
									<h3>Cash Cow Restaurante</h3>
									<figure>63 Birch Street
									</figure>
									<div class="wrapper">
										<div class="image">
											<img src="assets/img/items/1.jpg" alt="">
										</div>
										<div class="info">
											<div class="type">
												<i><img
													src="assets/icons/restaurants-bars/restaurants/restaurant.png"
													alt=""></i> <span>Restaurant</span>
											</div>
											<div class="rating" data-rating="4"></div>
										</div>
									</div>
								</a>
								<!--/.item-horizontal small-->
								<a href="item-detail.html" class="item-horizontal small">
									<h3>Blue Chilli</h3>
									<figure>2476 Whispering Pines Circle
									</figure>
									<div class="wrapper">
										<div class="image">
											<img src="assets/img/items/2.jpg" alt="">
										</div>
										<div class="info">
											<div class="type">
												<i><img
													src="assets/icons/restaurants-bars/restaurants/restaurant.png"
													alt=""></i> <span>Restaurant</span>
											</div>
											<div class="rating" data-rating="3"></div>
										</div>
									</div>
								</a>
								<!--/.item-horizontal small-->
								<a href="item-detail.html" class="item-horizontal small">
									<h3>Eddie’s Fast Food</h3>
									<figure>4365 Bruce Street
									</figure>
									<div class="wrapper">
										<div class="image">
											<img src="assets/img/items/3.jpg" alt="">
										</div>
										<div class="info">
											<div class="type">
												<i><img
													src="assets/icons/restaurants-bars/restaurants/restaurant.png"
													alt=""></i> <span>Restaurant</span>
											</div>
											<div class="rating" data-rating="5"></div>
										</div>
									</div>
								</a>
								<!--/.item-horizontal small-->
							</section>
							<section>
								<a href="#"><img src="assets/img/ad-banner-sidebar.png"
									alt=""></a>
							</section>
							<section>
								<header>
									<h2>Categories</h2>
								</header>
								<ul class="bullets">
									<li><a href="#">Restaurant</a></li>
									<li><a href="#">Steak House & Grill</a></li>
									<li><a href="#">Fast Food</a></li>
									<li><a href="#">Breakfast</a></li>
									<li><a href="#">Winery</a></li>
									<li><a href="#">Bar & Lounge</a></li>
									<li><a href="#">Pub</a></li>
								</ul>
							</section>
							<section>
								<header>
									<h2>Events</h2>
								</header>
								<div class="form-group">
									<select class="framed" name="events">
										<option value="">Select Your City</option>
										<option value="1">London</option>
										<option value="2">New York</option>
										<option value="3">Barcelona</option>
										<option value="4">Moscow</option>
										<option value="5">Tokyo</option>
									</select>
								</div>
								<!-- /.form-group -->
							</section>
						</aside>
						<!-- /#sidebar-->
					</div>
					<!-- /.col-md-3-->
					<!--end Sidebar-->
				</div>
				<!-- /.row-->
			</section>
			<!-- /.container-->
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->
	<!--  chat 시작 -->
	<div class="row chat-window col-xs-5 col-md-3" id="chat_window_1"
		style="margin-left: 10px;">
		<div class="col-xs-12 col-md-12">
			<div class="panel panel-default" id="chat">
				<div class="panel-heading top-bar">
					<div class="col-md-8 col-xs-8">
						<h5 class="panel-title">
							<span class="glyphicon glyphicon-comment"></span> Chat - Miguel
						</h5>


					</div>
					<div class="col-md-4 col-xs-4" style="text-align: right;">

						<a href="#"><span id="minim_chat_window"
							class="glyphicon glyphicon-minus icon_minim"></span></a> <a href="#"><span
							class="glyphicon glyphicon-remove icon_close"
							data-id="chat_window_1"></span></a>
					</div>
				</div>
				<div class="panel-body msg_container_base" id='chatList'>
					<div class="row msg_container base_sent">
						<div class="col-md-10 col-xs-10">
							<div class="messages msg_sent">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
					</div>
					<div class="row msg_container base_receive">
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
						<div class="col-md-10 col-xs-10">
							<div class="messages msg_receive">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
					</div>
					<div class="row msg_container base_receive">
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
						<div class="col-xs-10 col-md-10">
							<div class="messages msg_receive">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
					</div>
					<div class="row msg_container base_sent">
						<div class="col-xs-10 col-md-10">
							<div class="messages msg_sent">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
					</div>
					<div class="row msg_container base_receive">
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
						<div class="col-xs-10 col-md-10">
							<div class="messages msg_receive">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
					</div>
					<div class="row msg_container base_sent">
						<div class="col-md-10 col-xs-10 ">
							<div class="messages msg_sent">
								<p>that mongodb thing looks good, huh? tiny master db, and
									huge document store</p>
								<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
							</div>
						</div>
						<div class="col-md-2 col-xs-2 avatar">
							<img
								src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
								class=" img-responsive ">
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="input-group">
						<input id="inputText" type="text"
							class="form-control input-sm chat_input"
							placeholder="Write your message here..." /> <span
							class="input-group-btn">
							<button class="btn btn-primary btn-sm" id="sendBtn">Send</button>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="btn-group dropup">
		<button type="button" class="btn btn-default dropdown-toggle"
			data-toggle="dropdown">
			<span class="glyphicon glyphicon-cog"></span> <span class="sr-only">Toggle
				Dropdown</span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="#"><span id="chatComplete" class="fa fa-check"></span>심부름
					완료</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-list"></span>
					Ver outras</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-remove"></span>
					Fechar Tudo</a></li>
			<li class="divider"></li>
			<li><a href="#"><span class="glyphicon glyphicon-eye-close"></span>
					Invisivel</a></li>
		</ul>
	</div>
	<!--  chat 끝 -->


	<script>
		var itemID = 1;
		$.getJSON('assets/json/items.json.txt')
			.done(function(json) {
				$.each(json.data, function(a) {
					if (json.data[a].id == itemID) {
						itemDetailMap(json.data[a]);
					}
				});
			})
			.fail(function(jqxhr, textStatus, error) {
				console.log(error);
			})
		;
		$(window).load(function() {
			var rtl = false; // Use RTL
			initializeOwl(rtl);
		});
	</script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>