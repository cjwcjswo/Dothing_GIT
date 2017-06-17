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
<link rel="stylesheet" href="assets/css/owl.carousel.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<link rel="stylesheet" href="assets/css/user.style.css" type="text/css">

<script type="text/javascript">
function checkValid() {
    var f = window.document.f;
	
	if ( f.reply_content.value == "" ) {
		alert( "댓글 내용을 입력하세요." );
		return false;
	}	
    return true;
}
</script>

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
						<h2>1:1 게시판</h2>
						<span style="color: #9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span>
						<table class="table" align="center">
							<thead align="left">
								<tr>
									<td align=""><span style="font-size: 16px;"><h1>${board.boardTitle}</h1></span>
										<div align="right">
											<span style="color: #ff513f;">${board.boardDate} | 조회수 ${board.readNum}</span>
										</div></td>

								</tr>

							</thead>
							<tbody align="left">
								<tr>
									<td><div class="comment-text">${board.boardContent}</div></td>
								</tr>


							</tbody>
						</table>

						<!-- /.form-group -->

						<%-- <!-- reviews -->
						<section class="" id="reviews">
						<header class="clearfix">
						<h2 class="pull-left">댓글 목록</h2>
						<a href="#write-review" class="btn framed icon pull-right roll">댓글
							작성하기 <i class="fa fa-pencil"></i>
						</a>
						</header> 
						<section class="reviews">
						<article class="review">
						<!-- <figure class="author"> 
						<img
							src="assets/img/default-avatar.png" alt="">
						<div class="date">작성일 2017-06-15 오후 5:34</div>
						</figure> /.author -->
						
						<div class="wrapper">
						<!-- <c:choose>
							<h5>작성자 아이디</h5>
							<figure class="rating big color" data-rating="4"></figure>
							<p>이거 방탄 유리야 새끼야~~~</p>
						</c:choose> -->
						
						<c:choose>
						
						<c:when test="${empty requestScope.reply}">
								<tr>
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 댓글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
						</c:when>
							
						<c:otherwise>
							<c:forEach items="${requestScope.reply}" var="replyDto">
							
							<figure class="author"> 
						    <img
							src="assets/img/default-avatar.png" alt="">
						    <!-- <div class="date">작성일 2017-06-15 오후 5:34</div> -->
						    </figure> <!-- /.author-->
							
							<h5>운영자</h5>
							<figure class="rating big color" data-rating="4">
							</figure>
							<p>${replyDto.replyContent}</p>
							
							</c:forEach>
						</c:otherwise>
						</c:choose>

						</div>
						<!-- /.wrapper--> </article> <!-- /.review --> <!-- /.review --> </section> <!-- /.reviews-->
						</section>
						<!-- /#reviews -->
						<!--end Reviews--> --%>
						
						<!-- NEW -->
						<div class="" id="reviews">
						<div class="clearfix">
						<h2 class="pull-left">댓글 목록</h2>
						<a href="#write-review" class="btn framed icon pull-right roll">댓글
							작성하기 <i class="fa fa-pencil"></i>
						</a>
						</div> 
						<div class="reviews">
						<div class="review">
						<!-- <figure class="author"> 
						<img
							src="assets/img/default-avatar.png" alt="">
						<div class="date">작성일 2017-06-15 오후 5:34</div>
						</figure> /.author -->
						
						<div class="wrapper">
						
						
						<c:choose>
						
						<c:when test="${empty requestScope.reply}">
								<!-- <tr>
									<td colspan="5"> -->
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 댓글이 없습니다.</span></b>
										</p>
									<!-- </td>
								</tr> -->
						</c:when>
							
						<c:otherwise>
							<c:forEach items="${requestScope.reply}" var="replyDto">
							
							<div class="author"> 
						    <img
							src="assets/img/default-avatar.png" alt="">
						    <!-- <div class="date">작성일 2017-06-15 오후 5:34</div> -->
						    </div> <!-- /.author-->
							
							<h5>운영자</h5>
							<div class="rating big color" data-rating="4">
							</div>
							<p>${replyDto.replyContent}</p>
							
							</c:forEach>
						</c:otherwise>
						</c:choose>

						</div>
						<!-- /.wrapper--> </div> <!-- /.review --> <!-- /.review --> </div> <!-- /.reviews-->
						</div>
						<!-- /#reviews -->
						<!--end Reviews-->
						
						<!--Review Form-->
						<div id="write-review"> <div>
						<h2>댓글 작성하기</h2>
						</div>
						
						<form id="form-review" role="form" method="post" action="${pageContext.request.contextPath}/board/insertReply"
							class="background-color-white" onsubmit="return checkValid()">
							<div class="row">
								<div class="col-md-8">
									<div class="form-group">
										<label for="form-review-name">아이디</label> <input type="text"
											class="form-control" id="form-review-name" value="운영자"
											name="form-review-name" required=""  disabled>
									</div>
									<!-- /.form-group -->
									
									<!-- /.form-group -->
									<input type="hidden" name="board.inquiryNum"
									value="${board.inquiryNum}">
								    <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
									
									<div class="form-group" style="width:100%">
										<label for="form-review-message">댓글 내용</label>
										<textarea class="form-control" id="form-review-message"
											name="replyContent" rows="3" required=""></textarea>
									</div>
									<!-- /.form-group -->
									<div class="form-group">
										<button type="submit" class="btn btn-default">댓글등록</button>
									</div>
									<!-- /.form-group -->
								</div>
							
							</div>
						</form>
						<!-- /.main-search --> </div>
						<br><br>

						<div class="form-group col-sm-6" align="left">
							<button type="button" class="btn btn-large btn-default"
								id="submit">다음글</button>
						</div>
						<div class="form-group col-sm-6" align="right">
							<button type="submit" class="btn btn-large btn-default"
								id="submit" onclick="location.href='${pageContext.request.contextPath}/board/inquiryBoardList'">목록</button>
						</div>


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