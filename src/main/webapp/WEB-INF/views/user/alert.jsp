<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>알림</title>

</head>
<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">

	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
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
						<li class="active">알림</li>
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
			<div class="container">
				<h2>알림</h2>
				<table class="table table-hover">

					<tbody align="left">
						<c:if test="${alertList.size() == 0}">
							<tr>

								<td><span style="color: #ff513f;">알림이 없습니다.</span><br>
							</tr>
						</c:if>
						<c:if test="${alertList.size() != 0}">
							<c:forEach items="${alertList}" var="alert">
							<tr>
								<td><span style="color: #ff513f;">${alert.writeDate}</span><br>
									${alert.content}</td>
							</tr>
							</c:forEach>
						</c:if>

					</tbody>
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


			<!-- /.form-group -->
		</div>
		<!--/.col-md-6-->
	</div>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>