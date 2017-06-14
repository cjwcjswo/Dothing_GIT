<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<div class="container" >
						<h2>Notice</h2>
						<p>여러분은 최고의 서비스 DoThing에 있습니다.</p>
						<table class="table table-hover"  >
							<thead align="left">
								<tr>
									<td align="left" ><Strong>글제목</Strong></td>
								</tr>
							</thead>
							
							<c:choose>
								<c:when test="${empty requestScope.list}">
									<tr>
										<td colspan="5">
											<p align="center">
												<b><span style="font-size: 9pt;">등록된 내용이 없습니다.</span></b>
											</p>
										</td>
									</tr>
								</c:when>
							<c:otherwise>
							<c:forEach items="${requestScope.list}" var="boardDto">
							<tbody align="left">								
								<tr>
									<td>
									<a href="${pageContext.request.contextPath}/board/noticeBoardRead/${boardDto.noticeNum}"><h1>${boardDto.noticeTitle}</h1></a>
									<a><i class="fa fa-trash-o" data-toggle="tooltip" title="삭제하기"></i></a>
									<a><i class="fa fa-pencil" data-toggle="tooltip" title="수정하기"></i></a>
									<span style="color:#ff513f;">${boardDto.noticeDate}</span>
									</td>
								</tr>
							</tbody>
							</c:forEach>
							</c:otherwise>
							</c:choose>
						</table>
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

					<div class="form-group" align="center">
						<button type="submit" class="btn btn-large btn-default"
							id="submit">글작성하기</button>
					</div>
					<!-- /.form-group -->
				</div>
				<!--/.col-md-6-->
			</div>

		</div>
		<!--Password-->

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