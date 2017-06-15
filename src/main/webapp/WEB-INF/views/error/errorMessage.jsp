<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>

<title>ERROR!</title>

</head>

<body onunload="" class="page-subpage page-404 navigation-off-canvas"
	id="page-top">


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
					<h1 class="page-title">${exception.message}${errorMessage}</h1>
				</header>
				<div class="block">
					<div id="title-404">
						<aside> ERROR! <img
								src="${pageContext.request.contextPath}/assets/img/scissors.png"
								alt="">
						</aside>
						<h2>${exception.message}
							${errorMessage}</h2>
						<p>
							<!-- ${errorMessage} -->
							<br>
							<a href="${pageContext.request.contextPath}/">메인으로 돌아가기</a>
					</div>
				</div>
			</section>
			<!-- /.block-->
			
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->




	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>