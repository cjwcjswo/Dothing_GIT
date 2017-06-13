<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Spotter - Universal Directory Listing HTML Template</title>
<script type="text/javascript" src="assets/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
</head>
<body onunload=""
	class="map-fullscreen page-homepage navigation-off-canvas"
	id="page-top">


	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>Navigation</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->
		<!--Page Content-->
		<div id="page-content">
			<!-- Map Canvas-->
			<div class="map-canvas list-width-30">
				<!-- Map -->
				<div class="map">
					<div class="toggle-navigation" style="z-index: 5">
						<div class="icon">
							<div class="line"></div>
							<div class="line"></div>
							<div class="line"></div>
						</div>
					</div>
					<!--/.toggle-navigation-->
					<div id="map" class="has-parallax"></div>
					<!--/#map-->
					<div class="search-bar horizontal">
						<form class="main-search border-less-inputs" role="form"
							method="post">
							<div class="input-row">
								<div class="form-group">
									<input type="text" class="form-control" id="keyword"
										placeholder="해쉬태그 검색(예시: #음식배달)">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<div class="input-group location">
										<input type="text" class="form-control" id="location"
											placeholder="지역을 찾아보세요"> <span
											class="input-group-addon"><i
											class="fa fa-map-marker geolocation" data-toggle="tooltip"
											data-placement="bottom" title="내 위치 찾기"></i></span>
									</div>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<select name="category" multiple title="Select Category"
										data-live-search="true">
										<!--  <option value="1">#음식배달</option>
                                        <option value="2" class="sub-category">Apparel</option>
                                        <option value="3" class="sub-category">Computers</option>
                                        <option value="4" class="sub-category">Nature</option>
                                        <option value="5">Tourism</option>
                                        <option value="6">Restaurant & Bars</option>
                                        <option value="7" class="sub-category">Bars</option>
                                        <option value="8" class="sub-category">Vegetarian</option>
                                        <option value="9" class="sub-category">Steak & Grill</option>
                                        <option value="10">Sports</option>
                                        <option value="11" class="sub-category">Cycling</option>
                                        <option value="12" class="sub-category">Water Sports</option>
                                        <option value="13" class="sub-category">Winter Sports</option> -->
									</select>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<button type="submit" class="btn btn-default">
										<i class="fa fa-search"></i>
									</button>
								</div>
								<!-- /.form-group -->
							</div>
							<!-- /.input-row -->
						</form>
						<!-- /.main-search -->
					</div>
					<!-- /.search-bar -->
				</div>
				<!-- end Map -->
				<!--Items List-->
				<div class="items-list">
					<div class="inner">
						<header>
							<h3>검색 결과</h3>
						</header>
						<ul class="results list">
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item" id="' + json.data[a].id + '">
									<a href="#" class="image">
										<div class="inner">
											<div class="item-specific">그림설명</div>
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png"
												alt="">
										</div>
									</a>
									<div class="wrapper">
										<a href="#" id="' + json.data[a].id + '">
											<h3>꼬북꼬북</h3>
										</a>
										<figure>경기도 수원시 권선구
										</figure>
										<div class="price">$100</div>
										<div class="info">
											<div class="type">
												<span>어디냐2</span>
											</div>
										</div>
									</div>
								</div>
							</li>

						</ul>
					</div>
					<!--results-->
				</div>
				<!--end Items List-->
			</div>
			<!-- end Map Canvas-->
			<!--Featured-->
			<section id="featured"
				class="block background-color-grey-dark equal-height">
				<div class="container">
					<header>
						<h2>추천 인증 심부름꾼</h2>
					</header>
					<div class="row">
						<div class="col-md-3 col-sm-3">
							<div class="item featured">
								<div class="image">
									<div class="quick-view" id="1">
										<i class="fa fa-eye"></i><span>Quick View</span>
									</div>
									<a href="item-detail.html">
										<div class="overlay">
											<div class="inner">
												<div class="content">
													<h4>설명</h4>
													<p>빠른 배달, 최고 친절 , 모든 것을 보여줍니다.(심부름꾼)</p>
												</div>
											</div>
										</div> <!--  <div class="item-specific">
                                                <span title="Bedrooms"><img src="assets/img/bedrooms.png" alt="">2</span>
                                                <span title="Bathrooms"><img src="assets/img/bathrooms.png" alt="">2</span>
                                                <span title="Area"><img src="assets/img/area.png" alt="">240m<sup>2</sup></span>
                                                <span title="Garages"><img src="assets/img/garages.png" alt="">1</span>
                                            </div> -->
										<div class="icon">
											<i class="fa fa-thumbs-up"></i>
										</div> <img
										src="${pageContext.request.contextPath }/assets/img/items/1.jpg"
										alt="">
									</a>
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>심부름꾼 아이디</h3></a>
									<figure>심부름꾼 위치(주소)
									</figure>
									<div class="info">
										<div class="type">
											<i><img
												src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/restaurant.png"
												alt=""></i> <span>#친절함 #존잘 #이태호</span>
										</div>
										<div class="rating" data-rating="4"></div>
									</div>
								</div>
							</div>
							<!-- /.item-->
						</div>
						<!--/.col-sm-4-->
						<div class="col-md-3 col-sm-3">
							<div class="item featured">
								<div class="image">
									<div class="quick-view">
										<i class="fa fa-eye"></i><span>Quick View</span>
									</div>
									<a href="item-detail.html">
										<div class="overlay">
											<div class="inner">
												<div class="content">
													<h4>Description</h4>
													<p>Curabitur odio nibh, luctus non pulvinar a,
														ultricies ac diam. Donec neque massa</p>
												</div>
											</div>
										</div> <img
										src="${pageContext.request.contextPath}/assets/img/items/2.jpg"
										alt="">
									</a>
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>Benny’s Cafeteria</h3></a>
									<figure>63 Birch Street
									</figure>
									<div class="info">
										<div class="type">
											<i><img
												src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/cafetaria.png"
												alt=""></i> <span>Cafeteria</span>
										</div>
										<div class="rating" data-rating="4"></div>
									</div>
								</div>
							</div>
							<!-- /.item-->
						</div>
						<!--/.col-sm-4-->
						<div class="col-md-3 col-sm-3">
							<div class="item featured">
								<div class="image">
									<div class="quick-view">
										<i class="fa fa-eye"></i><span>Quick View</span>
									</div>
									<a href="item-detail.html">
										<div class="overlay">
											<div class="inner">
												<div class="content">
													<h4>Description</h4>
													<p>Curabitur odio nibh, luctus non pulvinar a,
														ultricies ac diam. Donec neque massa</p>
												</div>
											</div>
										</div>
										<div class="item-specific">
											<span>Daily menu from: $6</span>
										</div> <img
										src="${pageContext.request.contextPath}/assets/img/items/restaurant/9.jpg"
										alt="">
									</a>
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>Big Bamboo</h3></a>
									<figure>4662 Bruce Street
									</figure>
									<div class="info">
										<div class="type">
											<i><img
												src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/japanese-food.png"
												alt=""></i> <span>Sushi</span>
										</div>
										<div class="rating" data-rating="3"></div>
									</div>
								</div>
							</div>
							<!-- /.item-->
						</div>
						<!--/.col-sm-4-->
						<div class="col-md-3 col-sm-3">
							<div class="item featured">
								<div class="image">
									<div class="quick-view">
										<i class="fa fa-eye"></i><span>Quick View</span>
									</div>
									<a href="item-detail.html">
										<div class="overlay">
											<div class="inner">
												<div class="content">
													<h4>Description</h4>
													<p>Curabitur odio nibh, luctus non pulvinar a,
														ultricies ac diam. Donec neque massa</p>
												</div>
											</div>
										</div>
										<div class="item-specific">
											<span>Daily menu from: $6</span>
										</div> <img
										src="${pageContext.request.contextPath}/assets/img/items/restaurant/10.jpg"
										alt="">
									</a>
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>Sushi Wooshi Bar</h3></a>
									<figure>357 Trainer Avenue
									</figure>
									<div class="info">
										<div class="type">
											<i><img
												src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/fishchips.png"
												alt=""></i> <span>Sushi Bar</span>
										</div>
										<div class="rating" data-rating="3"></div>
									</div>
								</div>
							</div>
							<!-- /.item-->
						</div>
						<!--/.col-sm-4-->
					</div>
					<!--/.row-->
				</div>
				<!--/.container-->
			</section>
			<!--end Featured-->

			<!--Popular-->
			<section id="popular" class="block background-color-white">
				<div class="container">
					<header>
						<h2>평점이 높은 심부름꾼</h2>
					</header>
					<div class="owl-carousel wide carousel">
						<div class="slide">
							<div class="inner">
								<div class="image">
									<div class="item-specific">
										<div class="inner">
											<div class="content">
												<dl>
													<dt>심부름 수행횟수</dt>
													<dd>20회</dd>
													<dt>평균 심부름 소요시간</dt>
													<dd>12분 23초</dd>
													<dt>인증 심부름 여부</dt>
													<dd>O</dd>

												</dl>
											</div>
										</div>
									</div>
									<img
										src="${pageContext.request.contextPath}/assets/img/items/restaurant/8.jpg"
										alt="">
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>유저 아이디</h3></a>
									<figure>
										<i class="fa fa-map-marker"></i>
										<span>심부름꾼 위치 여부</span>
									</figure>
									<div class="info">
										<div class="rating" data-rating="4">
											<aside class="reviews">6개의 리뷰</aside>
										</div>

									</div>
									<!--/.info-->
									<p>자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개
										블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개 블라블랍자기 소개
										블라블랍</p>
									<a href="item-detail.html" class="read-more icon">더 알아보기</a>
								</div>
								<!--/.wrapper-->
							</div>
							<!--/.inner-->
						</div>
						<!--/.slide-->
						<div class="slide">
							<div class="inner">
								<div class="image">
									<div class="item-specific">
										<div class="inner">
											<div class="content">
												<dl>
													<dt>Bedrooms</dt>
													<dd>2</dd>
													<dt>Bathrooms</dt>
													<dd>2</dd>
													<dt>Area</dt>
													<dd>
														240m<sup>2</sup>
													</dd>
													<dt>Garages</dt>
													<dd>1</dd>
													<dt>Build Year</dt>
													<dd>1990</dd>
												</dl>
											</div>
										</div>
									</div>
									<img
										src="${pageContext.request.contextPath}/assets/img/items/restaurant/9.jpg"
										alt="">
								</div>
								<div class="wrapper">
									<a href="item-detail.html"><h3>Saguaro Tavern</h3></a>
									<figure>
										<i class="fa fa-map-marker"></i>
										<span>2476 Whispering Pines Circle</span>
									</figure>
									<div class="info">
										<div class="rating" data-rating="4">
											<aside class="reviews">6 reviews</aside>
										</div>
										<div class="type">
											<i><img
												src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/restaurant.png"
												alt=""></i> <span>Restaurant</span>
										</div>
									</div>
									<!--/.info-->
									<p>Curabitur odio nibh, luctus non pulvinar a, ultricies ac
										diam. Donec neque massa, viverra interdum eros ut, imperdiet
										pellentesque mauris. Proin sit amet scelerisque risus. Donec
										semper semper erat ut mollis. Curabitur suscipit, justo eu
										dignissim lacinia, ante sapien pharetra dui...</p>
									<a href="item-detail.html" class="read-more icon">Read More</a>
								</div>
								<!--/.wrapper-->
							</div>
							<!--/.inner-->
						</div>
						<!--/.slide-->
					</div>
					<!--/.owl-carousel-->
				</div>
				<!--/.container-->
			</section>
			<!--end Popular-->
			<section class="block equal-height">
				<div class="container">
					<div class="row">
						<div class="col-md-9">
							<header>
								<h2>인기있는 심부름</h2>
							</header>
							<div class="row">
								<div class="col-md-4 col-sm-4">
									<div class="item">
										<div class="image">
											<div class="quick-view">
												<i class="fa fa-eye"></i><span>Quick View</span>
											</div>
											<a href="item-detail.html">
												<div class="overlay">
													<div class="inner">
														<div class="content">
															<h4>설명</h4>
															<p>21:34분 까지 빅맥 사주세요</p>
														</div>
													</div>
												</div>

												<div class="icon">
													<i class="fa fa-thumbs-up"></i>
												</div> <img
												src="${pageContext.request.contextPath}/assets/img/items/1.jpg"
												alt="">
											</a>
										</div>
										<div class="wrapper">
											<a href="item-detail.html"><h3>심부름 제목</h3></a>
											<figure>심부름 요청위치
											</figure>
											<div class="info">
												<div class="type">
													<i><img
														src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/restaurant.png"
														alt=""></i> <span>#음식배달</span>
												</div>
												<div class="rating" data-rating="3"></div>
											</div>
										</div>
									</div>
									<!-- /.item-->
								</div>
								<!--/.col-sm-4-->
								<div class="col-md-4 col-sm-4">
									<div class="item">
										<div class="image">
											<div class="quick-view">
												<i class="fa fa-eye"></i><span>Quick View</span>
											</div>
											<a href="item-detail.html">
												<div class="overlay">
													<div class="inner">
														<div class="content">
															<h4>Description</h4>
															<p>Curabitur odio nibh, luctus non pulvinar a,
																ultricies ac diam. Donec neque massa</p>
														</div>
													</div>
												</div> <img
												src="${pageContext.request.contextPath}/assets/img/items/2.jpg"
												alt="">
											</a>
										</div>
										<div class="wrapper">
											<a href="item-detail.html"><h3>Benny’s Cafeteria</h3></a>
											<figure>63 Birch Street
											</figure>
											<div class="info">
												<div class="type">
													<i><img
														src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/cafetaria.png"
														alt=""></i> <span>Cafeteria</span>
												</div>
												<div class="rating" data-rating="4"></div>
											</div>
										</div>
									</div>
									<!-- /.item-->
								</div>
								<!--/.col-sm-4-->
								<div class="col-md-4 col-sm-4">
									<div class="item">
										<div class="image">
											<div class="quick-view">
												<i class="fa fa-eye"></i><span>Quick View</span>
											</div>
											<a href="item-detail.html">
												<div class="overlay">
													<div class="inner">
														<div class="content">
															<h4>Description</h4>
															<p>Curabitur odio nibh, luctus non pulvinar a,
																ultricies ac diam. Donec neque massa</p>
														</div>
													</div>
												</div>
												<div class="item-specific">
													<span>Daily menu from: $6</span>
												</div> <img
												src="${pageContext.request.contextPath}/assets/img/items/restaurant/9.jpg"
												alt="">
											</a>
										</div>
										<div class="wrapper">
											<a href="item-detail.html"><h3>Big Bamboo</h3></a>
											<figure>4662 Bruce Street
											</figure>
											<div class="info">
												<div class="type">
													<i><img
														src="${pageContext.request.contextPath}/assets/icons/restaurants-bars/restaurants/japanese-food.png"
														alt=""></i> <span>Sushi</span>
												</div>
												<div class="rating" data-rating="3"></div>
											</div>
										</div>
									</div>
									<!-- /.item-->
								</div>
								<!--/.col-sm-4-->
							</div>
							<!--/.row-->

							<!--Recent-->
							<section id="recent">
								<header>
									<h2>최근 심부름</h2>
								</header>
								<div class="item list">
									<div class="image">
										<div class="quick-view">
											<i class="fa fa-eye"></i><span>Quick View</span>
										</div>
										<a href="item-detail.html">
											<div class="overlay">
												<div class="inner">
													<div class="content">
														<h4>설명</h4>
														<p>옥수수 사줘 옥수수수수수수</p>
													</div>
												</div>
											</div>
											<div class="item-specific">물품 가격 + 심부름 가격 = 4,500원</div>
											<div class="icon">
												<i class="fa fa-thumbs-up"></i>
											</div> <img src="assets/img/items/1.jpg" alt="">
										</a>
									</div>
									<div class="wrapper">
										<a href="item-detail.html"><h3>심부름 제목</h3></a>
										<figure>심부름 위치
										</figure>
										<div class="info">
											<div class="type">
												<span>해시태그
													<p>#예시 #예시 #예시</p>
												</span>
											</div>
											<div class="rating" data-rating="4"></div>
										</div>
									</div>
								</div>
								<!-- /.item-->
								<div class="item list">
									<div class="image">
										<div class="quick-view">
											<i class="fa fa-eye"></i><span>Quick View</span>
										</div>
										<a href="item-detail.html">
											<div class="overlay">
												<div class="inner">
													<div class="content">
														<h4>Description</h4>
														<p>Curabitur odio nibh, luctus non pulvinar a,
															ultricies ac diam. Donec neque massa</p>
													</div>
												</div>
											</div> <img src="assets/img/items/2.jpg" alt="">
										</a>
									</div>
									<div class="wrapper">
										<a href="item-detail.html"><h3>Benny’s Cafeteria</h3></a>
										<figure>63 Birch Street
										</figure>
										<div class="info">
											<div class="type">
												<i><img
													src="assets/icons/restaurants-bars/restaurants/cafetaria.png"
													alt=""></i> <span>Cafeteria</span>
											</div>
											<div class="rating" data-rating="4"></div>
										</div>
									</div>
								</div>
								<!-- /.item-->
								<div class="item list">
									<div class="image">
										<div class="quick-view">
											<i class="fa fa-eye"></i><span>Quick View</span>
										</div>
										<a href="item-detail.html">
											<div class="overlay">
												<div class="inner">
													<div class="content">
														<h4>Description</h4>
														<p>Curabitur odio nibh, luctus non pulvinar a,
															ultricies ac diam. Donec neque massa</p>
													</div>
												</div>
											</div>
											<div class="item-specific">
												<span>Daily menu from: $6</span>
											</div> <img src="assets/img/items/restaurant/9.jpg" alt="">
										</a>
									</div>
									<div class="wrapper">
										<a href="item-detail.html"><h3>Big Bamboo</h3></a>
										<figure>4662 Bruce Street
										</figure>
										<div class="info">
											<div class="type">
												<i><img
													src="assets/icons/restaurants-bars/restaurants/japanese-food.png"
													alt=""></i> <span>Sushi</span>
											</div>
											<div class="rating" data-rating="3"></div>
										</div>
									</div>
								</div>
								<!-- /.item-->
							</section>
							<!--end Recent-->

							<!--end Categories-->
						</div>
						<!--/.col-md-9-->
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
									<header>
										<h2>Categories</h2>
									</header>
									<!-- 	<ul class="bullets">
									<li><a href="#">Restaurant</a></li>
									<li><a href="#">Steak House & Grill</a></li>
									<li><a href="#">Fast Food</a></li>
									<li><a href="#">Breakfast</a></li>
									<li><a href="#">Winery</a></li>
									<li><a href="#">Bar & Lounge</a></li>
									<li><a href="#">Pub</a></li>
								</ul> -->
								</section>
								<!-- <section> 
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
								/.form-group </section> -->
							</aside>
							<!-- /#sidebar-->
						</div>
						<!-- /.col-md-3-->
					</div>
					<!--/.row-->
				</div>
			</section>

			<!--Banner-->
			<section>
				<div class="container">
					<div class="block">
						<a href="#"><img src="assets/img/ad-banner.png" alt=""></a>
					</div>
				</div>
			</section>
			<!--end Banner-->
			<!--Subscribe-->
			<section id="subscribe" class="block">
				<div class="container">
					<header>
						<h2>구독하기</h2>
					</header>
					<form class="subscribe form-inline border-less-inputs" action="?"
						method="post" role="form">
						<div class="input-group">
							<input type="email" class="form-control" id="subscribe_email"
								placeholder="메일을 작성해서 DoThing의 새로운 소식을 받아보세요!"> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-default btn-large">
									구독완료<i class="fa fa-angle-right"></i>
								</button>
							</span>
						</div>
					</form>
					<!--/.subscribe-->
				</div>
				<!--/.container-->
			</section>
			<!--end Subscribe-->
			<!--Partners-->
			<section id="partners" class="block">
				<div class="container">
					<header>
						<h2>파트너사</h2>
					</header>
					<div class="logos">
						<div class="logo">
							<a href="#"><img src="assets/img/logo-partner-01.png" alt=""></a>
						</div>
						<div class="logo">
							<a href="#"><img src="assets/img/logo-partner-02.png" alt=""></a>
						</div>
						<div class="logo">
							<a href="#"><img src="assets/img/logo-partner-03.png" alt=""></a>
						</div>
						<div class="logo">
							<a href="#"><img src="assets/img/logo-partner-04.png" alt=""></a>
						</div>
						<div class="logo">
							<a href="#"><img src="assets/img/logo-partner-05.png" alt=""></a>
						</div>
					</div>
				</div>
				<!--/.container-->
			</section>
			<!--end Partners-->
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				level : 3 // 지도의 확대 레벨
			};
	
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption);
	
		$('.map .toggle-navigation').click(function() {
			$('.map-canvas').toggleClass('results-collapsed');
	
		});
		// Set if language is RTL and load Owl Carousel
	
		$(window).load(function() {
			var rtl = false; // Use RTL
			initializeOwl(rtl);
		});
	
		map.relayout();
	</script>
</body>
</html>