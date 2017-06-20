<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지게시판</title>

</head>
<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">


	<!-- Page Canvas-->
	<div id="page-canvas">
		<nav class="off-canvas-navigation">
			<header>메뉴</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->

		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">

					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li><a
							href="${pageContext.request.contextPath}/board/noticeBoardList">공지게시판</a></li>
						<li class="active">${board.noticeNum}번글</li>
					</ol>
					<!-- /.breadcrumb-->
				</div>
				<!-- /.container-->
			</div>
			<!-- /.breadcrumb-wrapper-->
		</section>
		<!--Page Content-->
		<div id="page-content">
			<div class="container">
				<h2>Notice</h2>
				<span style="color: #9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span>
				<table class="table" align="center">
					<thead align="left">
						<tr>
							<td align=""><span style="font-size: 16px;"><h1>${board.noticeTitle}</h1></span>
								<br>
								<div align="right">
									<span style="color: #ff513f;">${board.noticeDate} | 조회수
										${board.readNum}</span>
								</div></td>
						</tr>

					</thead>
					<tbody align="left">
						<tr>
							<td><div class="comment-text">${board.noticeContent}</div></td>
						</tr>
					</tbody>
				</table>

				<!-- <div class="form-group col-sm-6" align="left">
							<button type="button" class="btn btn-large btn-default"
								id="submit">다음글</button>
						</div> -->
				<div class="form-group col-sm-6" align="right">
					<button type="submit" class="btn btn-large btn-default" id="submit"
						onclick="location.href='${pageContext.request.contextPath}/board/noticeBoardList'">목록</button>
				</div>
				<!-- /.form-group -->
			</div>
		</div>
		<!--/.col-md-6-->
	</div>





	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>