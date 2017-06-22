<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지게시판</title>


<style type="text/css">
body p {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 100px;
	height: 20px;
}
</style>

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
						<li class="active">공지게시판</li>
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
						<table class="table table-hover">
							<thead align="left">
								<tr>
									<td align="left"><Strong>글제목</Strong></td>
								</tr>
							</thead>
								<c:choose>
									<c:when test="${empty requestScope.list}">
										<tr>
											<td colspan="5">
												<div align="center">
													<b><span style="font-size: 9pt;">등록된 게시물이 없습니다.</span></b>
												</div>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${requestScope.list}" var="boardDto">
											<tbody align="left">
												<tr>
													<td><a
														href="${pageContext.request.contextPath}/board/noticeBoardRead/${boardDto.noticeNum}"><h1>${boardDto.noticeTitle}</h1></a>

														<input type=hidden name="noticeNum"
														value="${boardDto.noticeNum}"> <input
														type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}"> <a href="#"
														onclick="location.href='${pageContext.request.contextPath}/board/noticeDelete?noticeNum=${boardDto.noticeNum}'">
															<i class="fa fa-trash-o" data-toggle="tooltip"
															title="삭제하기"></i>
													</a>  <span style="color: #ff513f;">${boardDto.noticeDate}</span>
													</td>
												</tr>
											</tbody>
										</c:forEach>
									</c:otherwise>
								</c:choose>		
						</table>
					</div>

					<!-- 페이지네이션 -->


					<ul class="pagination" style="margin-left: 35%">
						<c:if test="${pm.previous}">
							<li><a
								href="${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.lastPage - 5}"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
						</c:if>
						<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
							varStatus="state">
							<c:if test="${pm.currentPage == (pm.startPage + state.count-1)}">
								<li class="active"><a
									href='${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
								</li>
							</c:if>
							<c:if test="${pm.currentPage != (pm.startPage + state.count-1)}">
								<li><a
									href='${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${pm.next}">
							<li><a
								href="${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.lastPage + 1}"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</c:if>

					</ul>
					<!-- 페이지네이션 종료 -->

					<div class="form-group" align="center">
						<button type="submit" class="btn btn-large btn-default"
							id="submit"
							onclick="location.href='${pageContext.request.contextPath}/board/noticeBoardWrite'">글작성하기</button>
					</div>
					<!-- /.form-group -->
				</div>
				<!--/.col-md-6-->
			</div>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->

</body>
</html>