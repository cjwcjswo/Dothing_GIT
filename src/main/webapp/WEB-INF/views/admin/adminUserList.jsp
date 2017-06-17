<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="assets/css/dropzone.css" type="text/css">
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<link rel="stylesheet" href="assets/css/user.style.css" type="text/css">


<title>Spotter - Universal Directory Listing HTML Template</title>
<style>

.stylish-input-group .input-group-addon {
	background: white !important;
}

.stylish-input-group .form-control {
	border-right: 0;
	box-shadow: 0 0 0;
	border-color: #ccc;
}

.stylish-input-group button {
	border: 0;
	background: transparent;
}
</style>
</head>

<body onunload=""
	class="page-subpage page-profile navigation-top-header" id="page-top">

	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<!-- Page Canvas-->
			<div id="page-canvas">



				<!--Page Content-->
				<div id="page-content">
					<section class="container">
						<header>
							<ul class="nav nav-pills">
								<li><a href="adminSafe.jsp"><h1 class="page-title">무통장
											입금 확인</h1></a></li>
								<li><a href="my-items.html"><h1 class="page-title">안전
											심부름꾼 승인</h1></a></li>
								<li class="active"><a href="adminUserList.jsp"><h1
											class="page-title">회원관리</h1></a></li>
							</ul>
						</header>



						<div class="row">

							<div class="col-md-9">

								<div class="row">

									<!-- 서치바 -->


									<div class="col-sm-6 col-sm-offset-3" style="margin-left:10%">
										<div id="imaginary_container">
											<div class="input-group stylish-input-group input-append">
												<input type="text" class="form-control" placeholder="Search">
												<span class="input-group-addon">
													<button type="submit">
														<span class="glyphicon glyphicon-search"></span>
													</button>
												</span>
											</div>
										</div>
									</div>


									<!-- 서치바 종료 -->




									<!--Contact Info-->
									<div class="col-md-9 col-sm-9">
										<!-- 	아이디 포인트액수 비밀번호확인 승인 버튼 삭제버튼  -->
										<table class="table table-hover">
											<thead>
												<tr>
													<th>아이디</th>
													<th>이름</th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>tester</td>

													<td>이태호</td>
													<td><a href="#"><i class="fa fa-trash"> </i></a></td>
												</tr>

											</tbody>
										</table>









										<!-- 페이지네이션 -->

										<ul class="pager">
											<li><a href="#">Previous</a></li>
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">Next</a></li>
										</ul>

										<!-- 페이지네이션 종료 -->


										<!-- /.form-group -->
									</div>
									<!--/.col-md-6-->
								</div>

							</div>

							<!-- /.col-md-3-->
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
	<script type="text/javascript" src="assets/js/dropzone.min.js"></script>
	<script type="text/javascript" src="assets/js/custom.js"></script>
	<script type="text/javascript" src="assets/js/maps.js"></script>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>