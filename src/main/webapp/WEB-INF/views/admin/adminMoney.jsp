<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<li class="active"><a href="${pageContext.request.contextPath}/admin/adminMoney"><h1 class="page-title">무통장
											입금 확인</h1></a></li>
								<li><a href="${pageContext.request.contextPath}/admin/adminSafe"><h1 class="page-title">안전
											심부름꾼 승인</h1></a></li>
								<li><a href="${pageContext.request.contextPath}/admin/adminUserList"><h1
											class="page-title">회원관리</h1></a></li>
							</ul>
						</header>
						<div class="row">
							<div class="col-md-9">
								<div class="row">
									<!--Contact Info-->
									<div class="col-md-9 col-sm-9">
										<!-- 	아이디 포인트액수 비밀번호확인 승인 버튼 삭제버튼  -->
										<table class="table table-hover">
											<thead>
												<tr>
													<th>아이디</th>
													<th>요청 포인트</th>
												</tr>
											</thead>

											<c:choose>
												<c:when test="${empty requestScope.list}">
													<tr>
														<td colspan="3">
															<div align="center">
																<b><span style="font-size: 9pt;">등록된 무통장입금
																		포인트 요청이 없습니다.</span></b>
															</div>
														</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${requestScope.list}" var="pointDto">
														<tbody>
															<tr>
																<td>${pointDto.user.userId}</td>
																<td><i class="fa fa-krw"></i>${pointDto.requestPoint}</td>
																<td><a href="#"><i class="fa fa-check"
																		title="포인트로전환"></i></a> <a href="#"><i
																		class="fa fa-close" title="취소"></i></a></td>
															</tr>
														</tbody>
													</c:forEach>
												</c:otherwise>
											</c:choose>
											
										</table>

										<!-- 페이지네이션 -->

										<!-- <ul class="pager">
											<li><a href="#">Previous</a></li>
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">Next</a></li>
										</ul> -->

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