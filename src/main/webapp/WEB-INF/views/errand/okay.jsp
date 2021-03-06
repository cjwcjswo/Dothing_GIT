<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>OKAY!</title>
	<script>
	function sendMessage(){
		ws.send("선택:${num}번 글 -> ${responseName}님과 심부름이 매칭되었습니다.:${responseId}");
	}

	$(function(){
		
		if(${num > 0}){	
			setTimeout("sendMessage()", 1000);
		}
	
		
		
		
	});
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
					<h1 class="page-title">${exception.message}${errorMessage}</h1>
				</header>
				<div class="block">
					<div id="title-404">
						<aside> 매칭 완료! 
						</aside>
						<h2>정상적으로 매칭이 완료되었습니다!</h2><br>
						<button type="button" class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/errand/detailView?num=${num}'">심부름 확인하기</button>
						<button type="button" class="btn btn-default"
						onclick="location.href='${pageContext.request.contextPath}/errand/errand'">다른 심부름 보기</button>
						<p>
							<!-- ${errorMessage} -->
							<br>
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