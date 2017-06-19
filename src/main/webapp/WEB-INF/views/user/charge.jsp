<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

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
					<section class="container"> <header>
					<ul class="nav nav-pills">
						<li><a href="profile.html"><h1 class="page-title">유저
									이름 넣어</h1></a></li>
						<li><a href="my-items.html"><h1 class="page-title">심부름
									요청 및 수행 내역</h1></a></li>
						<li><a href="safetyRegister.jsp"><h1 class="page-title">안전
									심부름꾼 신청</h1></a></li>
						<li class="active"><a href="safetyRegister.jsp"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>
					</ul>
					</header>
					<table class="table table-hover">
						<thead align="left">
							<tr>

								<td align="left">결제 수단 :<label class="radio-inline"><input
										type="radio" name="optradio">무통장 입금</label> <label
									class="radio-inline"><input type="radio"
										name="optradio">카드결제</label></td>
							</tr>
						</thead>
						<tbody align="left">
							<tr>
								<div class="form-group col-xs-4">
									<label for="sel1">포인트 액수</label> <select class="form-control"
										id="sel1">
										<option>5,000p(5,000원)</option>
										<option>10,000p(10,000원)</option>
										<option>20,000p(20,000원)</option>
										<option>50,000p(50,000원)</option>
										<option>100,000p(100,000원)</option>

									</select>
								</div>

							</tr>


						</tbody>

					</table>
					<div class="form-group" align="center">
						<button type="submit" class="btn btn-large btn-default"
							id="submit">충전</button>


					</div>

					<h1>
						<i class="fa fa-check"></i>최근 결제 내역
					</h1>

					<table class="table table-hover">
						<thead>
							<tr>
								<th>구입일</th>
								<th>결제 포인트</th>
								<th>사용 포인트</th>
								<th>잔여 포인트</th>
								<th>환전 포인트</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2017-06-19</td>
								<td>5,000</td>
								<td>0</td>
								<td>12,000</td>
								<td>-</td>

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

					<!-- 페이지네이션 종료 --> <!-- /.form-group --> </section>


				</div>
				<!--/.col-md-6-->
			</div>

		</div>

	</div>
	</section>




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