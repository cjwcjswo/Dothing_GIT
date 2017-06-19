<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
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
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">

	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

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
						<header>
							<ul class="nav nav-pills">
								<li><a href="profile.html"><h1 class="page-title">유저
											이름 넣어</h1></a></li>
								<li><a href="my-items.html"><h1 class="page-title">심부름
											요청 및 수행 내역</h1></a></li>
								<li class="active"><a href="safetyRegister.jsp"><h1
											class="page-title">안전 심부름꾼 신청</h1></a></li>
								<li><a href="safetyRegister.jsp"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>
							</ul>
						</header>
						<div class="row">
	
							<div class="col-md-9 col-sm-9">
								<section>
									<h3>
										<i class="fa fa-thumbs-up"></i>인증 심부름꾼 여부
										<p align="right">o/x</p>
									</h3>
									<div class="form-group" align="center" id="holder">
										<img
											src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
											class="img-circle" width="200" height="200">

									</div>
									<input class="form-control" type="file" name="selfImgFile"
										placeholder="picture" id="upload" />

									<p>
									<h3>
										<i class="fa fa-info"></i>신분증의 생년월일 앞자리를 가린 후 올려주세요!

									</h3>
									</p>
									추후에 관리자가 심사 후 인증 심부름꾼으로 등록해드립니다.
									
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
	<script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/smoothscroll.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="assets/js/custom.js"></script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>