<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1게시판</title>

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
						<li><a href="${pageContext.request.contextPath}/board/inquiryBoardList">1:1 게시판</a></li>
						<li class="active">${board.inquiryNum}번 글</li>
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
				<h2>1:1 게시판</h2>
				<span style="color: #9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span>
				<table class="table" align="center">
					<thead align="left">
						<tr>
							<td align=""><span style="font-size: 16px;"><h1>${board.boardTitle}</h1></span>
								<div align="right">
									<span style="color: #ff513f;">작성자 ${board.userId} | ${board.boardDate} | 조회수
										${board.readNum}</span>
								</div></td>

						</tr>

					</thead>
					<tbody align="left">
						<tr>
							<td><div class="comment-text"><pre>${board.boardContent}</pre></div></td>
						</tr>


					</tbody>
				</table>

				<!-- /.form-group -->

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

							<div class="wrapper">

								<c:choose>

									<c:when test="${empty requestScope.reply}">
										
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 답변이 없습니다.</span></b>
										</p>
									
									</c:when>

									<c:otherwise>
										<c:forEach items="${requestScope.reply}" var="replyDto">

											<div class="author">
												<img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="">
												
											</div>
											<!-- /.author-->

											<h5>운영자</h5>
											<div class="rating big color" data-rating="4"></div>
											<pre><p>${replyDto.replyContent}</p></pre>

										</c:forEach>
									</c:otherwise>
								</c:choose>

							</div>
							<!-- /.wrapper-->
						</div>
						<!-- /.review -->
						<!-- /.review -->
					</div>
					<!-- /.reviews-->
				</div>
				<!-- /#reviews -->
				<!--end Reviews-->

				<!--Review Form-->
				<security:authorize access="hasRole('ROLE_ADMIN')">
				<div id="write-review">
					<div>
						<h2>댓글 작성하기</h2>
					</div>

					<form id="form-review" role="form" method="post"
						action="${pageContext.request.contextPath}/board/insertReply"
						class="background-color-white" onsubmit="return checkValid()">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label for="form-review-name">아이디</label> <input type="text"
										class="form-control" id="form-review-name" value="운영자"
										name="form-review-name" required="" disabled>
								</div>
								<!-- /.form-group -->

								<!-- /.form-group -->
								<input type="hidden" name="board.inquiryNum"
									value="${board.inquiryNum}"> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}">

								<div class="form-group" style="width: 100%">
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
					<!-- /.main-search -->
				</div>
				</security:authorize>
				<br>
				<br>

				<!-- <div class="form-group col-sm-6" align="left">
							<button type="button" class="btn btn-large btn-default"
								id="submit">다음글</button>
						</div> -->
				<div class="form-group col-sm-6" align="right">
					<button type="submit" class="btn btn-large btn-default" id="submit"
						onclick="location.href='${pageContext.request.contextPath}/board/inquiryBoardList'">목록</button>
				</div>


			</div>





		</div>

		<!--Password-->




	</div>




	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>