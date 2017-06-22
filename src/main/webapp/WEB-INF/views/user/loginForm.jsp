<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>

<!-- google platform library -->
<script src="https://apis.google.com/js/platform.js" async defer></script>

<!-- specify client ID -->
<meta name="google-signin-client_id" content="852010525738-koadhapuooddd7np0govnv6lfgg5tsqf.apps.googleusercontent.com">

<title>로그인</title>

</head>

<body onunload=""
	class="page-subpage page-item-detail navigation-off-canvas" id="page-top">


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
						<li><a href="#">로그인</a></li>
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
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
							<header>
								<h1 class="page-title">Log In</h1>
							</header>
							<hr>
							<security:authorize access="isAnonymous()">
							<form role="form" method="post"
								action="${pageContext.request.contextPath}/user/login">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
								<div class="form-group">
									<label for="form-sign-in-email">아이디 : </label> <input
										type="text" class="form-control" id="form-sign-in-email"
										name="id" required>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label for="form-sign-in-password">비밀번호:</label> <input
										type="password" class="form-control"
										id="form-sign-in-password" name="password" required>
								</div>
								<!-- /.form-group -->
								<div class="form-group clearfix">
									<div class="btn-group" align="right">
										<button type="button" class="g-signin2" data-onsuccess="onSignIn"></button>
										<button type="submit" class="btn pull-right btn-default"
										id="account-submit">로그인</button>
									</div>
								</div>
								
								<!-- /.form-group -->
							</form>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<security:authentication
										property="principal.userId" /> 님</a> 환영합니다.
							</security:authorize>
						</div>
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