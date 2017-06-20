<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/Article-List.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/Team-Boxed.css">


<title>메인 페이지</title>
<script>
Notification.requestPermission(function(result) {
	  if (result === 'denied') {
	    console.log('Permission wasn\'t granted. Allow a retry.');
	    return;
	  }
	  if (result === 'default') {
	    console.log('The permission request was dismissed.');
	    return;
	  }
	  // Do something with the granted permission.
	});
</script>
</head>
<body onunload=""
	class="page-subpage page-item-detail navigation-off-canvas"
	id="page-top">
	<nav class="off-canvas-navigation">
		<header>메뉴</header>
		<div class="main-navigation navigation-off-canvas"></div>
	</nav>
	<div class="video-part">
		<video autoplay="autoplay" loop="loop">
			<source
				src="${pageContext.request.contextPath}/assets/img/Lady_in_ST.mp4"
				type="video/mp4">
		</video>

		<div class="container">
			<div class="video-part-content">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<div class="carousel-caption ">
								<center>
									<img style="width: 150px; height: 150px;"
										src="${pageContext.request.contextPath}/assets/img/main/lightbulb.png"
										class="img-responsive animated fadeInDown">
								</center>
								<div class="full-width animated fadeInUp">
									<h1>무엇이든지 이웃에게, 이웃으로</h1>
									<p>작은 도움이 필요할 때가 있으신가요?</p>
									<a href="${pageContext.request.contextPath}/errand/errand"
										class="btn btn-info">로그인하기</a>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="carousel-caption ">
								<center>
									<img style="height: 100px; width: 100px;"
										src="${pageContext.request.contextPath}/assets/img/main/givemoney.png"
										class="img-responsive  animated fadeInDown">
								</center>
								<div class="full-width animated fadeInUp">
									<h1>이웃에게, 이웃은 나에게</h1>
									<p>커피한잔만을 배달해 줄리가 있을까요? DoThing에서는 가능합니다.</p>
									<a href="${pageContext.request.contextPath}/errand/errand"
										class="btn btn-info">참여하기</a>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="carousel-caption">
								<center>
									<img style="height: 150px; width: 150px;"
										src="${pageContext.request.contextPath}/assets/img/main/shakehands.png"
										class="img-responsive animated fadeInDown">
								</center>
								<div class="full-width animated fadeInUp">
									<h1>1분,1초가 당신의 가치를 보여주는 곳</h1>
									<p>당신의 자투리 시간이 당신의 가치를 보여 줄 수 있습니다. 집에 가는 길, 회사 가는 길 . 순간의
										가치로 이웃과 행복을 나눠보세요.</p>
									<a href="#" class="btn btn-info">About DoThing</a>
								</div>
							</div>
						</div>

					</div>

					<!-- Controls -->

				</div>
			</div>
		</div>

	</div>

	<!-- 아래부분 제발 되라 -->

	<!-- <div class="testimonials" >
		
		<div style="border:1px solid blue ; margin-top: 3px;" >
		
		<h3 class="text-center" >People Love It!</h3>
		</div>
		<blockquote>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Integer posuere erat a ante.</p>
			<footer>Famous tech website</footer>
		</blockquote>
		
		
	</div> -->
	<section class="features" id="features">
		<div class="container">
			<div class="row">

				<div class="col-md-6">
					<h2>Connecting you to your community</h2>
					<p>Dothing 서비스는 주위의 누군가가 나 대신 무엇이든지 해주는 서비스입니다. 당신이 원하는 배달비를 통해
						약간의 부담이면 무엇이든지 할 수 있습니다.</p>
				</div>
				<div class="col-md-6">
					<div class="row icon-features">
						<div class="col-xs-4 icon-feature">
							<i class="fa fa-location-arrow"></i>
							<p>언제 어디서나</p>
						</div>
						<div class="col-xs-4 icon-feature">
							<i class="fa fa-krw"></i>
							<p>당신이 원하는 가격으로</p>
						</div>
						<div class="col-xs-4 icon-feature">
							<i class="fa fa-male"></i>
							<p>원하는 심부름을 해보세요</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="article-list">
		<div class="container">
			<div class="intro" id="about">
				<h2 class="text-center">About Dothing</h2>
				<p class="text-center">DoThing은 사람이 필요한 모든 일들을 연결해주는 서비스 입니다.</p>
				<p class="text-center">당신이 필요한 일들은 언제 어디서나 부탁해보세요!</p>
				<p class="text-center">몇번의 클릭과 터치만으로 당신의 이웃과 함께</p>
			</div>
			<div class="row articles">
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/assets/img/desk.jpg"></a>
					<h3 class="name">심부름을 신청해보세요</h3>
					<p class="description">DoThing에서는 모든 심부름과 심부름 삯을 사용자가 직접 설정 할 수
						있습니다. 최저 배달 비용과 모든 심부름을 내 주변 사람들에게 도와달라고 부탁해보세요!</p>
					<a href="#" class="action"></a>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/assets/img/building.jpg"></a>
					<h3 class="name">실시간 채팅을 통해서</h3>
					<p class="description">자세한 요청 사항을 매칭된 직접 심부름꾼에게 말해보세요. 빅맥세트에서
						콜라를 쉐이크로 바꿀 수 있는 놀라운 마술! 우리집에서 우리 회사로 바꿀 수 있는 놀라운 매력!</p>
					<a href="#" class="action"></a>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/assets/img/loft.jpg"></a>
					<h3 class="name">리뷰와 평점, 그리고 인증을 통해 안전과 신뢰를 보장합니다!</h3>
					<p class="description">DoThing에서는 모두가 심부름꾼이 될 수 있고, 심부름 요청을 할 수
						있습니다. 만약, 상대가 어떨지에 대해 궁금하세요? 상대방을 해시태그 리뷰와 평점을 통해 선택해보세요!</p>
					<a href="#" class="action"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="team-boxed">
		<div class="container">
			<div class="intro">
				<h2 class="text-center">팀 드보르작(Dvorak)</h2>
				<p class="text-center">안토니 드보르작은 교향곡 제9번 ‘신세계’에서 새로운 세상에 대한 기대감과
					설레 임을 표현하기 한 곡입니다. 저희 드보르작 팀 또한 드보르작 같이 새로운 세상을 열 서비스를 만드는 마음으로
					여러분과 만날 준비가 되어 있습니다.</p>
			</div>
			<div class="row people">
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/assets/img/1.jpg">
						<h3 class="name">Ben Johnson</h3>
						<p class="title">Musician</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/assets/img/2.jpg">
						<h3 class="name">Emily Clark</h3>
						<p class="title">Artist</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/assets/img/3.jpg">
						<h3 class="name">Carl Kent</h3>
						<p class="title">Stylist</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="team-boxed"></div>

	<!-- 아래 부분 끝 -->


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>