<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
\
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-select.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/dropzone.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery.ui.timepicker.css"
	type="text/css">

<title>Spotter - Universal Directory Listing HTML Template</title>

</head>

<body onunload="" class="page-subpage page-submit navigation-off-canvas"
	id="page-top">

	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">
			<!-- Navigation-->
			<div class="header">
				<div class="wrapper">
					<div class="brand">
						<a href="index-directory.html"><img src="assets/img/logo.png"
							alt="logo"></a>
					</div>
					<nav class="navigation-items">
						<div class="wrapper">
							<ul class="main-navigation navigation-top-header"></ul>
							<ul class="user-area">
								<li><a href="sign-in.html">Sign In</a></li>
								<li><a href="register.html"><strong>Register</strong></a></li>
							</ul>
							<a href="submit.html" class="submit-item">
								<div class="content">
									<span>Submit Your Item</span>
								</div>
								<div class="icon">
									<i class="fa fa-plus"></i>
								</div>
							</a>
							<div class="toggle-navigation">
								<div class="icon">
									<div class="line"></div>
									<div class="line"></div>
									<div class="line"></div>
								</div>
							</div>
						</div>
					</nav>
				</div>
			</div>
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
					<div class="search-bar horizontal collapse"
						id="redefine-search-form"></div>
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
								<li><a href="index-directory.html"><i
										class="fa fa-home"></i></a></li>
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
					<section class="container">
						<div class="row">
							<!--Content-->
							<div class="col-md-9">
								<header>
									<h1 class="page-title">새로운 심부름 등록하기</h1>
								</header>
								<form id="form-submit" role="form" method="post" action="?"
									enctype="multipart/form-data">
									<section>
										<div class="form-group large">
											<label for="title">심부름 제목</label> <input type="text"
												class="form-control" id="title" name="title">
										</div>
									</section>
									<section>



										<div class="form-group large">
											<label for="title">심부름 받을 위치</label> <input type="text"
												class="form-control" id="title" name="title" disabled>
										</div>
										<div class="form-group large">
											<label for="title">상세 주소</label> <input type="text"
												class="form-control" id="title" name="title">
										</div>
										<div class="col-md-3">
											<div class="menu-icon">
												<a href="#"><i class="fa fa-map-marker"></i><span>주소찾기</span></a>
											</div>
										</div>





									</section>
									<!--/#address-contact-->
									<section>
										<h3>Map</h3>
										<div id="map-simple" class="map-submit"></div>
									</section>
									<section>
										<h3>상세설명</h3>
										<textarea class="form-control" id="form-review-message"
											name="form-review-message" rows="3" required=""
											placeholder="양상추 까지"></textarea>

									</section>
									<!--Menu-->
									<section>
										<h3>물품 가격 및 심부름 삯</h3>
										<div class="row">
											<div class="col-md-3">
												<!-- Nav tabs -->
												<ul class="nav nav-pills nav-stacked">
													<li class="active"><a href="#tab-menu"
														data-toggle="tab">심부름 값 결정해보기</a></li>


												</ul>
											</div>
											<div class="col-md-9">
												<!-- Tab panes -->
												<div class="tab-content menu min-height-160">
													<div class="tab-pane fade in active" id="tab-menu">
														<article>
															<div class="row">
																<div class="col-md-1">
																	<div class="menu-icon">
																		<i class="fa fa-money"></i><span>1</span>
																	</div>
																</div>
																<div class="col-md-11">
																	<div class="row">
																		<div class="col-md-10">
																			<div class="form-group">
																				<input type="text" class="form-control"
																					name="menu-title[]"
																					placeholder="예)심부름 삯: (3,500원) 괄호 부분만 작성해주세요">
																			</div>
																		</div>
																		<!-- /.col-md-10-->


																	</div>
																	<!-- /.row-->
																	<div class="form-group">
																		<input type="text" class="form-control"
																			name="menu-description[]"
																			placeholder="예) 물품 가격 : (5,500원) 괄호 부분만 작성해주세요">
																	</div>

																</div>
																<!--/.col-md-11-->
															</div>
															<!--/.row-->
														</article>
													</div>
													<!--/#tab-menu-->




												</div>
												<!--end Tab panes-->
											</div>
											<!--/.col-md-9-->
										</div>
										<!--/.row-->
									</section>
									<!--end Menu-->
									<!--Gallery-->
									<section>
										<h3>사진</h3>
										<div id="file-submit" class="dropzone">
											<input name="file" type="file" multiple>
											<div class="dz-default dz-message">
												<span>클릭해서 열기 / 드랍 앤 다운</span>
											</div>
										</div>
									</section>
									<!--end Gallery-->
									<!--Opening Hours-->
									<section>
										<h3>원하는 심부름 시간</h3>
										<div class="opening-hours">
											<div class="table-responsive">
												<table class="table">
													<tbody>
														<tr class="day">
															<td class="day-name">현재 날짜</td>
															<td class="from"><input class="oh-timepicker"
																type="text" placeholder="From" name="open-hour-from[]"></td>
															<td class="to"><input class="oh-timepicker"
																type="text" placeholder="To" name="open-hour-to[]"></td>

														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</section>
									<!--end Opening Hours-->
									<hr>
									<section>
										<figure class="pull-left margin-top-15">
											<p>
												<a href="terms-conditions.html" class="link">약관에 동의</a> 하시면
												“제출하기 및 결제”옆의 제출하기 버튼을 클릭해주세요
											</p>
										</figure>
										<div class="form-group">
											<button type="submit" class="btn btn-default pull-right"
												id="submit">제출 및 결제</button>
										</div>
										<!-- /.form-group -->
									</section>
								</form>
								<!--/#form-submit-->
							</div>
							<!--/.col-md-9-->
							<!--Sidebar-->
							<div class="col-md-3">
								<aside id="sidebar">
									<div class="sidebar-box">
										<h3>Payment</h3>
										<div class="form-group">
											<label for="package">Your Package</label> <select
												name="package" id="package" class="framed">
												<option value="">Select your package</option>
												<option value="1">Free</option>
												<option value="2">Silver</option>
												<option value="3">Gold</option>
												<option value="4">Platinum</option>
											</select>
										</div>
										<!-- /.form-group -->
										<h4>This package includes</h4>
										<ul class="bullets">
											<li>1 Property</li>
											<li>1 Agent Profile</li>
											<li class="disabled">Agency Profile</li>
											<li class="disabled">Featured Properties</li>
										</ul>
									</div>
								</aside>
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
									</section>
									<!--end New Items-->
								</div>
								<div class="col-md-4 col-sm-4">
									<!--Recent Reviews-->
									<section>
										<h2>Recent Reviews</h2>
										<a href="item-detail.html#reviews" class="review small">
											<h3>Max Five Lounge</h3>
											<figure>4365 Bruce Street
											</figure>
											<div class="info">
												<div class="rating" data-rating="4"></div>
												<div class="type">
													<i><img
														src="assets/icons/restaurants-bars/restaurants/restaurant.png"
														alt=""></i> <span>Restaurant</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing
												elit. Donec non suscipit felis, sed sagittis tellus.
												Interdum et malesuada fames ac ante ipsum primis in
												faucibus. Cras ac placerat mauris.</p>
										</a>
										<!--/.review-->
										<a href="item-detail.html#reviews" class="review small">
											<h3>Saguaro Tavern</h3>
											<figure>2476 Whispering Pines Circle
											</figure>
											<div class="info">
												<div class="rating" data-rating="5"></div>
												<div class="type">
													<i><img
														src="assets/icons/restaurants-bars/restaurants/restaurant.png"
														alt=""></i> <span>Restaurant</span>
												</div>
											</div>
											<p>Pellentesque mauris. Proin sit amet scelerisque risus.
												Donec semper semper erat ut mollis curabitur</p>
										</a>
										<!--/.review-->
									</section>
									<!--end Recent Reviews-->
								</div>
								<div class="col-md-4 col-sm-4">
									<section>
										<h2>About Us</h2>
										<address>
											<div>Max Five Lounge</div>
											<div>63 Birch Street</div>
											<div>Granada Hills, CA 91344</div>
											<figure>
												<div class="info">
													<i class="fa fa-mobile"></i> <span>818-832-5258</span>
												</div>
												<div class="info">
													<i class="fa fa-phone"></i> <span>+1 123 456 789</span>
												</div>
												<div class="info">
													<i class="fa fa-globe"></i> <a href="#">www.maxfivelounge.com</a>
												</div>
											</figure>
										</address>
										<div class="social">
											<a href="#" class="social-button"><i
												class="fa fa-twitter"></i></a> <a href="#" class="social-button"><i
												class="fa fa-facebook"></i></a> <a href="#"
												class="social-button"><i class="fa fa-pinterest"></i></a>
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
		</div>
		<!-- end Inner Wrapper -->
	</div>
	<!-- end Outer Wrapper-->


	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/icheck.min.js"></script>\
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dropzone.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.ui.timepicker.js"></script>



	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>