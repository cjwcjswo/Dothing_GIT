<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat/chat.css"
	type="text/css">
<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>

<title>Spotter - Universal Directory Listing HTML Template</title>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
<script>
	$(function() {
		function leadingZeros(n, digits) {
			var zero = '';
			n = n.toString();

			if (n.length < digits) {
				for (var i = 0; i < digits - n.length; i++)
					zero += '0';
			}
			return zero + n;
		}

		$(document).on("click", "button[data-target='#myModal']", function() {
			var d = new Date();
			$("#date").html(d.getFullYear() + "-" + leadingZeros((d.getMonth() + 1), 2) + "-" + leadingZeros(d.getDate(), 2) + " "
				+ d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
			$("#sims").html("<h2>심부름꾼: " + $(this).attr("id") + "</h2>");
		})
	});
	function checkValid() {
		var form = document.f;
		if (form.replyContent.value.trim() == "") {
			alert("댓글 내용을 입력하세요");
			return false;
		}
		if (form.arrivalTime.value.trim() == "") {
			alert("시간을 입력하세요");
			return false;
		}
		return true;
	}

	function delErrands() {
		if ("${errands.requestUser.userId}" != "<security:authentication property='principal.userId'/>") {
			alert("작성자가 아닙니다");
			return;
		} else {
			location.href = "${pageContext.request.contextPath}/errand/deleteErrands?num=${errands.errandsNum}&id=<security:authentication property='principal.userId'/>";
		}
	}
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
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li><a
							href="${pageContext.request.contextPath}/errand/errand">심부름
								목록</a></li>
						<li class="active">${errands.errandsNum}번심부름</li>
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
					<div class="col-md-12">
						<section class="block" id="main-content">
							<header class="page-title">
								<div class="title">
									<h1>${errands.title}</h1>
								</div>
								<div class="info">
									<div class="type">
										<div id="hashList">
											<c:if test="${errands.hashes.size()	 != 0}">
												<c:forEach items="${errands.hashes}" var="hash">
													<span class="label label-info">#${hash}</span>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</header>
							<div class="row">
								<!--Detail Sidebar-->
								<aside class="col-md-3" id="detail-sidebar">
									<!--Contact-->
									<section>
										<header>
											<h3>Contact</h3>
										</header>
										<address>

											<figure>
												<div class="info">
													<i class="fa fa-child"></i> <span>${errands.requestUser.userId}</span>
												</div>
												<div class="info">
													<i class="fa fa-calendar"></i> <span>${errands.endTime}
														까지</span>
												</div>
												<div class="info">
													<i class="fa fa-globe"></i> <span>${errands.errandsPos.addr}</span>
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
									<button type="button" onclick="delErrands()"
										class="btn btn-default">삭제하기</button>
								</aside>
								<!--end Detail Sidebar-->
								<!--Content-->
								<div class="col-md-8">
									<section>
										<article class="item-gallery">


											<c:if test="${errands.errandsPhoto != null}">
												<img
													src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
											</c:if>



										</article>
										<!-- /.item-gallery -->
										<article class="block">
											<header>
												<h2>상세설명</h2>
											</header>
											<p>
											<div id="comment">${errands.content}</div>
											</p>
										</article>
										<article class="block">
											<header>
												<h2>돈 내용</h2>
											</header>
											<ul class="bullets">

												<li>물품 가격 : <fmt:formatNumber
														value="${errands.productPrice}" />원
												</li>
												<li>심부름가격 : <fmt:formatNumber
														value="${errands.errandsPrice}" />원
												</li>

												<li>Total : <fmt:formatNumber
														value="${errands.errandsPrice + errands.productPrice}" />원
												</li>
											</ul>
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
												<c:forEach items="${errands.errandsReply}" var="reply">
													<figure class="author">
														<img
															src="${pageContext.request.contextPath}/assets/img/default-avatar.png"
															alt="">
														<div class="date">${reply.arrivalTime}</div>
													</figure>
													<!-- /.author-->

													<div class="wrapper">
														<h5>${reply.user.userId}</h5>
														<figure class="rating big color" data-rating="4"></figure>
														<a href="#" class="btn framed icon pull-right roll">선택<i
															class="fa fa-check"></i></a>
														<p>${reply.replyContent}</p>


													</div>
												</c:forEach>
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
										<form name="f" method="post" action="insertReply"
											onsubmit="return checkValid()" class="background-color-white">
											<div class="row">
												<div class="col-md-8">
													<!-- /.form-group -->
													<div class="form-group">
														<label for="form-review-message">댓글 입력</label>
														<textarea class="form-control" id="form-review-message"
															name="replyContent" rows="3" required="" placeholder=""></textarea>
													</div>
													<div class="form-group">
														<label for="form-review-email">도착예정시간</label> <input
															type="datetime-local" class="form-control"
															name="arrivalTime" />
													</div>
													<input type="hidden" name="errands.errandsNum"
														value="${errands.errandsNum}"> <input
														type="hidden" name="user.userId"
														value="<security:authentication property='principal.userId'/>">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />

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
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-comment"></span> Chat - Miguel
						</h3>
					</div>
					<div class="col-md-4 col-xs-4" style="text-align: right;">
						<a href="#"><span id="minim_chat_window"
							class="glyphicon glyphicon-minus icon_minim"></span></a>
					</div>
				</div>
				<div class="panel-body msg_container_base">
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
						<input id="btn-input" type="text"
							class="form-control input-sm chat_input"
							placeholder="Write your message here..." /> <span
							class="input-group-btn">
							<button class="btn btn-primary btn-sm" id="btn-chat">Send</button>

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

    
    $(document).on('click', '.panel-heading span.icon_minim', function (e) {
        var $this = $(this);
        if (!$this.hasClass('panel-collapsed')) {
            $this.parents('.panel').find('.panel-body').slideUp();
            $this.addClass('panel-collapsed');
            $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
        } else {
            $this.parents('.panel').find('.panel-body').slideDown();
            $this.removeClass('panel-collapsed');
            $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
        }
    });
    $(document).on('focus', '.panel-footer input.chat_input', function (e) {
        var $this = $(this);
        if ($('#minim_chat_window').hasClass('panel-collapsed')) {
            $this.parents('.panel').find('.panel-body').slideDown();
            $('#minim_chat_window').removeClass('panel-collapsed');
            $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
        }
    });
    $(document).on('click', '#new_chat', function (e) {
        var size = $( ".chat-window:last-child" ).css("margin-left");
         size_total = parseInt(size) + 400;
        alert(size_total);
        var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
        clone.css("margin-left", size_total);
    });
    $(document).on('click', '.icon_close', function (e) {
        //$(this).parent().parent().parent().parent().remove();
        $( "#chat_window_1" ).remove();
    });
 // chat 부분 js 
      var mapContainer = document.getElementById('map-detail'), // 지도를 표시할 div 
         mapOption = {
            center : new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude}), // 지도의 중심좌표
            level : 3 // 지도의 확대 레벨
         };
   
      var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
   
      // 마커가 표시될 위치입니다 
      var markerPosition = new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude});
   
      // 마커를 생성합니다
      var marker = new daum.maps.Marker({
         position : markerPosition
      });
   
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
      
      var iwContent = '<div style="padding:5px;"><strong>주소</strong><br>${errands.errandsPos.addr}</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
       iwPosition = new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude}), //인포윈도우 표시 위치입니다
       iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
       var infowindow = new daum.maps.InfoWindow({
           position : iwPosition, 
           content : iwContent 
       });
         
       // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
       infowindow.open(map, marker); 

   </script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>