<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>1:1게시판</title>
</head>
<body onunload="" class="page-subpage page-faq navigation-off-canvas"
	id="page-top">
	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<!-- Page Canvas-->
			<div id="page-canvas">
			
				<!--Off Canvas Navigation-->
				<nav class="off-canvas-navigation"> <header>Navigation</header>
				<div class="main-navigation navigation-off-canvas"></div>
				</nav>
				<!--end Off Canvas Navigation-->

				<!--Sub Header-->
				<section class="sub-header">
				<div class="search-bar horizontal collapse"
					id="redefine-search-form"></div>
				<!-- /.search-bar -->
				<div class="breadcrumb-wrapper">
					<div class="container">

						<ol class="breadcrumb">
							<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i></a></li>
							<li><a href="#">FAQ게시판</a></li>
						</ol>
						<!-- /.breadcrumb-->
					</div>
					<!-- /.container-->
				</div>
				<!-- /.breadcrumb-wrapper--> </section>
				<!--end Sub Header-->

				<!--Page Content-->
				<div id="page-content">
					<section class="container">
					<div class="row">
						<!--Content-->
						<div class="col-md-9">
							<header>
							<h1 class="page-title">FAQ</h1>
							</header>
							<section class="faq-form"> <figure class="clearfix">
							<i class="fa fa-question"></i>
							<div class="wrapper">
								<div class="pull-left">
									<strong>궁금하신 점을 못찾으셨나요?</strong>
									<h3>1:1 문의 게시판에 올리기</h3>
								</div>
								<a href="#form-faq" class="btn btn-default pull-right"
									data-toggle="collapse" aria-expanded="false"
									aria-controls="form-faq">질문하러 가기</a>
							</div>
							</figure>
							<div class="collapse" id="form-faq">
								<div class="">
									<form name ="writeForm" role="form" action="${pageContext.request.contextPath}/board/insert" onSubmit='return checkValid()' method="post">
												<!-- <div class="form-group">
											<label for="faq-form-email">id</label> <input type="text"
												class="form-control" id="faq-form-email" required=""
												disabled>
										</div> -->
												<!-- /.form-group -->
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}">
												<div class="form-group">
													<label for="faq-form-email">제목</label> <input type="text"
														class="form-control" name="boardTitle" id="faq-form-email"
														required="">
												</div>
												
												<div class="form-group">
													<label for="faq-form-question">질문</label>
													<textarea class="form-control" id="faq-form-question"
														name="boardContent" rows="3" required=""></textarea>
												</div>
												<!-- /.form-group -->
										<div class="form-group">
											<button type="submit" class="btn btn-default">제출하기</button>
										</div>
										<!-- /.form-group -->
									</form>
									<!-- /form-->
								</div>
								<!-- /.content-->
							</div>
							<!-- /#form-faq--> </section>
							<!-- /#faq-form-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>어떻게 심부름꾼을 믿어요?</h4>
								<div class="answer">
									<figure>항상 신뢰를 주는 DOTHING입니다.</figure>
									<p>저희 DOTHING이 인증하는 안전 심부름꾼은 회사 규정에 의거한
									심부름꾼입니다. 또한 저희의 운영방침에 의해서 심부름꾼의 사기같은 경우에는
									100프로 포인트 환불이 가능합니다. 또한 법적 책임을 물을 것 입니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>포인트를 환불 받을 수 있나요?</h4>
								<div class="answer">
									<figure>예 환불 받을 수 있습니다.</figure>
									<p>고객님의 단순 변심으로 인한 포인트 환불은 구매 후 7일 이내 요청해주시면 환불
									할 수 있습니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>포인트 환전 시스템이 궁금합니다.</h4>
								<div class="answer">
									<figure>Answer</figure>
									<p>Cum sociis natoque penatibus et magnis dis parturient
										montes, nascetur ridiculus mus. Integer molestie viverra neque
										sit amet posuere. Nullam eget ultricies mi. Proin pulvinar
										quam porttitor consequat pulvinar. Etiam non neque et quam
										euismod cursus. Praesent eu sem interdum, pharetra metus sed,
										sollicitudin sem. Curabitur tincidunt dolor quis dolor
										iaculis, ut maximus ante fermentum</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>Suspendisse et enim semper, porta nunc ut, ullamcorper
									magna. Duis ut metus eu neque sollicitudin cursus non quis leo.
									Cras quis dignissim magna</h4>
								<div class="answer">
									<figure>Answer</figure>
									<p>Vivamus ut turpis risus. Vestibulum gravida dictum sem a
										rutrum. Fusce tincidunt rhoncus nulla vel rutrum. Nunc vel
										luctus dui. Phasellus egestas interdum lacinia.</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>Praesent scelerisque consectetur velit luctus
									fermentum. Sed lacinia ut nunc id consectetur. Praesent
									dignissim, enim in fringilla laoreet, est erat maximus nibh, id
									auctor sapien nisl a nisi.</h4>
								<div class="answer">
									<figure>Answer</figure>
									<p>Donec at arcu purus. Suspendisse laoreet quam ut purus
										aliquam, tempor pharetra tellus maximus. Etiam semper velit
										tincidunt, accumsan neque non, blandit metus. Aenean blandit
										felis at luctus condimentum. Nam gravida luctus mi, sed
										aliquet ligula consectetur a. Nulla eget semper felis, eget
										iaculis ante. Aliquam tempus molestie quam vel pellentesque.
										Aliquam nibh turpis, lobortis tempor euismod in, faucibus ut
										eros. Cras accumsan gravida tellus.</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
						</div>
						<!--Sidebar-->
						<div class="col-md-3">
							<aside id="sidebar"> <section> <header>
							<h2>New Places</h2>
							</header> <a href="item-detail.html" class="item-horizontal small">
								<h3>Cash Cow Restaurante</h3> <figure>63 Birch Street</figure>
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
							</a> <!--/.item-horizontal small--> <a href="item-detail.html"
								class="item-horizontal small">
								<h3>Blue Chilli</h3> <figure>2476 Whispering Pines
								Circle</figure>
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
							</a> <!--/.item-horizontal small--> <a href="item-detail.html"
								class="item-horizontal small">
								<h3>Eddie’s Fast Food</h3> <figure>4365 Bruce Street</figure>
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
							</a> <!--/.item-horizontal small--> </section> <section> <a href="#"><img
								src="assets/img/ad-banner-sidebar.png" alt=""></a> </section> <section>
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
							</section> <section> <header>
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
							<!-- /.form-group --> </section> </aside>
							<!-- /#sidebar-->
						</div>
						<!-- /.col-md-3-->
						<!--end Sidebar-->
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

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>