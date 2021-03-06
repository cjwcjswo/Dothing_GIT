<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="assets/fonts/font-awesome.css" rel="stylesheet"
	type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/bootstrap-select.min.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<link rel="stylesheet" href="assets/css/user.style.css" type="text/css">

<title>Spotter - Universal Directory Listing HTML Template</title>

</head>

<body onunload=""
	class="page-subpage page-contact navigation-off-canvas" id="page-top">

	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<!-- Page Canvas-->
			<div id="page-canvas">



				<!--Page Content-->
				<div id="page-content">
					<section id="map-simple" class="map-contact"></section>
					<section class="container">
						<div class="row">
							<!--Content-->
							<div class="col-md-9">
								<header>
									<h1 class="page-title">Contact</h1>
								</header>
								<section>
									<div class="row">
										<div class="col-md-4 col-sm-4">
											<header class="no-border">
												<h3>주소</h3>
											</header>
											<address>
												<div>
													<strong>DoThing</strong>
												</div>
												<div>경기도 성남시 분당구 삼평동</div>
												<div>대왕판교로 670길 유스페이스2 B동 8층</div>
												<br>
												<figure>
													<div class="info">
														<i class="fa fa-mobile"></i> <span>010-2824-1816</span>
													</div>
													<div class="info">
														<i class="fa fa-phone"></i> <span>070-5039-5803</span>
													</div>
													<div class="info">
														<i class="fa fa-globe"></i> <a href="#">edu.kosta.or.kr</a>
													</div>
												</figure>
											</address>
										</div>
										<!--/.col-md-4-->
										<div class="col-md-4 col-sm-4">
											<header class="no-border">
												<h3>Social Networks</h3>
											</header>
											<a href="#" class="social-button"><i
												class="fa fa-twitter"></i>Twitter</a> <a href="#"
												class="social-button"><i class="fa fa-facebook"></i>Facebook</a>
											<a href="#" class="social-button"><i
												class="fa fa-pinterest"></i>Pinterest</a>
										</div>
										<!--/.col-md-4-->
										<div class="col-md-4 col-sm-4">
											<header class="no-border">
												<h3>About Us</h3>
											</header>
											<p>DoThing은 사람이 필요한 모든 일들을 연결해주는 서비스 입니다. 당신이 필요한 일들은 언제
												어디서나 부탁해보세요! 몇번의 클릭과 터치만으로 당신의 이웃과 함께</p>
											<a href="${pageContext.request.contextPath}/etc/about-us" class="read-more icon">Read More</a>
										</div>
										<!--/.col-md-4-->
									</div>
									<!--/.row-->
								</section>
								<hr>
								<section>
									<header class="no-border">
										<h3>Contact Form</h3>
									</header>
									<form id="contact-form" role="form" method="post" action="?"
										class="background-color-white">
										<div class="row">
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="company-form-name">이름</label> <input
														type="text" class="form-control" id="company-form-name"
														name="company-form-name" required="">
												</div>
												<!-- /.form-group -->
											</div>
											<!--/.col-md-6-->
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="company-form-email">이메일</label> <input
														type="email" class="form-control" id="company-form-email"
														name="company-form-email" required="">
												</div>
												<!-- /.form-group -->
											</div>
											<!--/.col-md-6-->
										</div>
										<div class="form-group">
											<label for="company-form-message">내용</label>
											<textarea class="form-control" id="company-form-message"
												name="company-form-message" rows="3" required=""></textarea>
										</div>
										<!-- /.form-group -->
										<div class="form-group">
											<button type="submit" class="btn btn-default icon">
												메시지보내기<i class="fa fa-angle-right"></i>
											</button>
										</div>
										<!-- /.form-group -->
									</form>
									<!--/#contact-form-->
								</section>
							</div>
							
						</div>
					</section>
				</div>
				<!-- end Page Content-->
			</div>
			<!-- end Page Canvas-->
		
		</div>
		<!-- end Inner Wrapper -->
	</div>
	<!-- end Outer Wrapper-->

	<script type="text/javascript" src="assets/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="assets/js/before.load.js"></script>
	<script type="text/javascript"
		src="http://maps.google.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script type="text/javascript" src="assets/js/richmarker-compiled.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/smoothscroll.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="assets/js/custom.js"></script>
	<script type="text/javascript" src="assets/js/maps.js"></script>

	<script>
    $(window).load(function(){
        var _latitude = 51.541599;
        var _longitude = -0.112588;
        var draggableMarker = false;
        simpleMap(_latitude, _longitude,draggableMarker);
    });
</script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>