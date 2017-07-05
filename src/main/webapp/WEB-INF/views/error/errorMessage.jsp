<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
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
<script type="text/javascript">
	$(function() {

		$("#remail").click(function() {
			var email = $("#email").val();
			var num = $("#num").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/user/sendMail",
				type : "post",
				data : "userId=" + email + "&num="+num+"&_csrf=${_csrf.token}",
				dataType : "text",
				success : function(result) {
					swal("이메일을 성공적으로 보냈습니다!");
				},
				error : function(err) {
					console.log(err);
				}
			})
		});
	})
</script>
</head>

<body onunload="" class="page-subpage page-404 navigation-off-canvas"
	id="page-top">


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
				<c:set var="em" value="${fn:split(errorMessage,':')}"/>
					<h1 class="page-title">${exception.message}${em[0]}</h1>
				</header>
				<div class="block">
					<div id="title-404">
						<aside>
							ERROR! <img
								src="${pageContext.request.contextPath}/assets/img/scissors.png"
								alt="">
						</aside>
						<h2>${exception.message}${em[0]}</h2>
						<p>
							<!-- ${errorMessage} -->
							<br>
							
							<c:if test="${em[0] == '이메일 인증을 확인해주세요.'}">
								<input type="button" class="btn btn-warning" value="인증메일 다시보내기"
									style="background-color: skyblue" id="remail">
									<input type="hidden" id="email" value="${em[1]}"/>
									<input type="hidden" id="num" value="${em[2]}"/>
								<br>
								<br>
							</c:if>
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