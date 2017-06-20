<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1게시판 / 글 작성하기</title>

<script type="text/javascript">
	function checkValid() {
		var f = window.document.writeForm;

		if (f.notice_title.value == "") {
			alert("글제목을 입력하세요.");
			f.noticeTitle.focus();
			return false;
		}
		if (f.notice_content.value == "") {
			alert("글내용을 입력하세요.");
			f.noticeContent.focus();
			return false;
		}
		return true;
	}
</script>

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
							href="${pageContext.request.contextPath}/board/inquiryBoardList">1:1
								게시판</a></li>
						<li class="active">글 작성하기</li>
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
				<h2>1대1 문의 게시판</h2>
				<span style="color: #9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span>
				<form name="writeForm"
					action="${pageContext.request.contextPath}/board/insert"
					onSubmit='return checkValid()' method="post"
					style="width: 70%; margin-left: 15%" align="center">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
					<div class="form-group">
						<label for="faq-form-email">제목</label> <input type="text"
							class="form-control" name="boardTitle" id="faq-form-email"
							required="">
					</div>
					<!-- /.form-group -->
					<div class="form-group">
						<label for="faq-form-question">게시물 작성</label>
						<textarea class="form-control" id="faq-form-question" rows="5"
							required="" name="boardContent"></textarea>
					</div>
					<!-- /.form-group -->
					<div class="form-group">
						<span align="left"><a
							href="${pageContext.request.contextPath}/board/inquiryBoardList"><i
								class="fa fa-backward"></i>돌아가기</a></span>
						<button type="submit" class="btn btn-default">등록하기</button>
					</div>
					<!-- /.form-group -->
				</form>

			</div>
			<!--/.col-md-6-->
		</div>
	</div>
	<!--Password-->


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>