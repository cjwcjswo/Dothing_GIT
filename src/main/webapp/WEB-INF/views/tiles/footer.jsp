<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--Page Footer-->
	<footer id="page-footer">
		<div class="inner">
			<div class="footer-top">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-sm-4">
							<!--New Items-->
							<section>
								<h2>New Items</h2>
								<a href="item-detail.html" class="item-horizontal small">
									<h3>Cash Cow Restaurante</h3>
									<figure>63 Birch Street
									</figure>
									<div class="wrapper">
										<div class="image">
											<img
												src="${pageContext.request.contextPath}/assets/img/items/1.jpg"
												alt="">
										</div>
										<div class="info">
											<div class="type">
												<i><img
													src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/restaurant.png"
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
											<img
												src="${pageContext.request.contextPath}/assets/img/items/2.jpg"
												alt="">
										</div>
										<div class="info">
											<div class="type">
												<i><img
													src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/restaurant.png"
													alt=""></i> <span>Restaurant</span>
											</div>
											<div class="rating" data-rating="3"></div>
										</div>
									</div>
								</a>
								<!--/.item-horizontal small-->
							</section>
							<!--end New Items-->
						</div>
						<div class="col-md-4 col-sm-4">
							<!--Recent Reviews-->
							<section>
								<h2>최근 작성된 리뷰</h2>
								<a href="item-detail.html#reviews" class="review small">
									<h3>심부름요청자 아이디</h3>
									<figure>요청자 위치
									</figure>
									<div class="info">
										<div class="rating" data-rating="4"></div>

									</div>
									<p>#꿀심부름 #착함 #돈많이줌</p>
								</a>
								<!--/.review-->
								<a href="item-detail.html#reviews" class="review small">
									<h3>심부름꾼 아이디</h3>
									<figure>심부름꾼 위치
									</figure>
									<div class="info">
										<div class="rating" data-rating="5"></div>

									</div>
									<p>#최고 #친절 #못생김</p>
								</a>
								<!--/.review-->
							</section>
							<!--end Recent Reviews-->
						</div>
						<div class="col-md-4 col-sm-4">
							<section>
								<h2>About Us</h2>
								<address>
									<div>경기도</div>
									<div>분당시 판교로 유스페이스 A동</div>
									<div>8층 코스타</div>
									<figure>
										<div class="info">
											<i class="fa fa-mobile"></i> <span>010-5555-5555</span>
										</div>
										<div class="info">
											<i class="fa fa-phone"></i> <span>02-555-5555</span>
										</div>
										<div class="info">
											<i class="fa fa-globe"></i> <a href="#">www.kosta.com</a>
										</div>
									</figure>
								</address>
								<div class="social">
									<a href="#" class="social-button"><i class="fa fa-twitter"></i></a>
									<a href="#" class="social-button"><i class="fa fa-facebook"></i></a>
									<a href="#" class="social-button"><i
										class="fa fa-pinterest"></i></a>
								</div>

								<a href="contact.html" class="btn framed icon">Contact Us<i
									class="fa fa-angle-right"></i></a>
							</section>
						</div>
						<!--/.col-md-4-->
					</div>
					<!--/.row-->
				</div>
				<!--/.container-->
			</div>
			<!--/.footer-top-->
			<div class="footer-bottom">
				<div class="container">
					<span class="left">(C) ThemeStarz, All rights reserved</span> <span
						class="right"> <a href="#page-top" class="to-top roll"><i
							class="fa fa-angle-up"></i></a>
					</span>
				</div>
			</div>
			<!--/.footer-bottom-->
		</div>
	</footer>
	<!--end Page Footer-->
	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/smoothscroll.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery.nouislider.all.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/icheck.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/maps.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/before.load.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/custom.js"></script>


</body>
</html>