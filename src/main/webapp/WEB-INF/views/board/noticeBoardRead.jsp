<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<div class="container">
						<h2>Notice</h2>
						<span style="color:#9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span><br>
						<table class="table" align="center">
							<thead align="left">
								<tr>
									<td align=""><span style="font-size: 16px;"><h1>${board.noticeTitle}</h1></span>
									<br>
										<div align="right">
											<span style="color: #ff513f;">${board.noticeDate} | 조회수 ${board.readNum}</span>
										</div></td>
								</tr>

							</thead>
							<tbody align="left">
								<tr>
									<td><div class="comment-text">${board.noticeContent}</div></td>
								</tr>
							</tbody>
						</table>

						<div class="form-group col-sm-6" align="left">
							<button type="button" class="btn btn-large btn-default"
								id="submit">다음글</button>
						</div>
						<div class="form-group col-sm-6" align="right">
							<button type="submit" class="btn btn-large btn-default"
								id="submit" onclick="location.href='${pageContext.request.contextPath}/board/noticeBoardList'">목록</button>
						</div>
						<!-- /.form-group -->
					</div>
				</div>
				<!--/.col-md-6-->
			</div>
		</div>
		<!--Password-->

	</div>





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