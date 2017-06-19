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

<script type="text/javascript">

	//이미지 클릭시 원본 크기로 팝업 보기
	function doImgPop(img) {
		img1 = new Image();
		img1.src = (img);
		imgControll(img);
	}

	function imgControll(img) {
		if ((img1.width != 0) && (img1.height != 0)) {
			viewImage(img);
		} else {
			controller = "imgControll('" + img + "')";
			intervalID = setTimeout(controller, 20);
		}
	}
	function viewImage(img) {
		W = img1.width;
		H = img1.height;
		O = "width=" + W + ",height=" + H + ",scrollbars=yes";
		imgWin = window.open("", "", O);
		imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
		imgWin.document.write("<body topmargin=0 leftmargin=0>");
		imgWin.document.write("<img src=" + img + " onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
		imgWin.document.close();
	}
</script>


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
								<li><a href="adminMoney.jsp"><h1 class="page-title">무통장
											입금 확인</h1></a></li>
								<li class="active"><a href="adminSafe.jsp"><h1
											class="page-title">안전 심부름꾼 승인</h1></a></li>
								<li><a href="adminUserList.jsp"><h1 class="page-title">회원관리</h1></a></li>
							</ul>
						</header>
						<div class="row">
							<div class="col-md-9">

								<div class="row">

									<!--Contact Info-->
									<div class="col-md-9 col-sm-9">


										<div class="well">
											<div class="media">
												<a class="pull-left" href="#"><img class="media-object"
													src="http://placekitten.com/150/150"
													onclick="doImgPop('http://placekitten.com/150/150')"
													
													title="클릭하시면 원본크기로 보실 수 있습니다."
													style="width: 150px; height: 150px;"> </a>
													<!-- doImgPop('경로') 경로 부분에 원본 이미지 경로 넣어주셈 -->
												<div class="media-body">
													<h4 class="media-heading">사용자 아이디</h4>
													<p class="text-right">
														<a href=""><i class="fa fa-check">승인</i></a>
													</p>
													<p>안전 심부름꾼 신청 해시태그 리뷰 몇개 박아넣어</p>
													<ul class="list-inline list-unstyled">
														<li><span><i
																class="glyphicon glyphicon-calendar"></i> 가입일 </span></li>
														<li>|</li>
														<span><i class="glyphicon glyphicon-comment"></i>
															심부름 횟수</span>
														<li>|</li>
														<li><span class="glyphicon glyphicon-star"></span> <span
															class="glyphicon glyphicon-star"></span> <span
															class="glyphicon glyphicon-star"></span> <span
															class="glyphicon glyphicon-star"></span> <span
															class="glyphicon glyphicon-star-empty"></span></li>
														<li>|</li>

													</ul>
												</div>
											</div>
										</div>






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