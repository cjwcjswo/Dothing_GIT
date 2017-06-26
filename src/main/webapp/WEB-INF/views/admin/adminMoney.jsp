<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>무통장 입금 확인</title>

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



		<!--Page Content-->
		<div id="page-content">
			<section class="container">
				<header>
					<ul class="nav nav-pills">
						<li class="active"><a
							href="${pageContext.request.contextPath}/admin/adminMoney"><h1
									class="page-title">무통장 입금 확인</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/adminSafe"><h1
									class="page-title">안전 심부름꾼 승인</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/adminUserList"><h1
									class="page-title">회원관리</h1></a></li>
					</ul>
				</header>
				<div class="row">
					<div class="col-md-9">
						<div class="row">
							<!--Contact Info-->
							<div class="col-md-9 col-sm-9">
								<!-- 	아이디 포인트액수 비밀번호확인 승인 버튼 삭제버튼  -->
								<table class="table table-hover">
									<thead>
										<tr>
											<th>아이디</th>
											<th>요청 포인트</th>
										</tr>
									</thead>

									<c:choose>
										<c:when test="${empty requestScope.list}">
											<tr>
												<td colspan="3">
													<div align="center">
														<b><span style="font-size: 9pt;">등록된 무통장입금 포인트
																요청이 없습니다.</span></b>
													</div>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${requestScope.list}" var="pointDto">
												<tbody>
													<tr>
														<td>${pointDto.user.userId}</td>
														<td><i class="fa fa-krw"></i>${pointDto.requestPoint}</td>
														<input type="hidden" name="userId"
															value="${pointDto.user.userId}">
														<input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}">
														<td><a href="#"
															onclick="location.href='${pageContext.request.contextPath}/admin/pointChange?userId=${pointDto.user.userId}'"><i
																class="fa fa-check" title="포인트로전환"></i></a> <a href="#"
															onclick="location.href='${pageContext.request.contextPath}/admin/pointCancel?userId=${pointDto.user.userId}'"><i
																class="fa fa-close" title="취소"></i></a></td>
													</tr>
												</tbody>
											</c:forEach>
										</c:otherwise>
									</c:choose>

								</table>

								<!-- 페이지네이션 -->

								<!-- <ul class="pager">
											<li><a href="#">Previous</a></li>
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">Next</a></li>
										</ul> -->
								<ul class="pagination" style="margin-left: 35%">
									<c:if test="${pm.previous}">
										<li><a
											href="${pageContext.request.contextPath}/admin/adminMoney?page=${pm.lastPage - 5}"><span
												class="glyphicon glyphicon-chevron-left"></span></a></li>
									</c:if>
									<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
										varStatus="state">
										<c:if
											test="${pm.currentPage == (pm.startPage + state.count-1)}">
											<li class="active"><a
												href='${pageContext.request.contextPath}/admin/adminMoney?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
											</li>
										</c:if>
										<c:if
											test="${pm.currentPage != (pm.startPage + state.count-1)}">
											<li><a
												href='${pageContext.request.contextPath}/admin/adminMoney?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
											</li>
										</c:if>
									</c:forEach>
									<c:if test="${pm.next}">
										<li><a
											href="${pageContext.request.contextPath}/admin/adminMoney?page=${pm.lastPage + 1}"><span
												class="glyphicon glyphicon-chevron-right"></span></a></li>
									</c:if>

								</ul>
								<!-- 페이지네이션 종료 -->


								<!-- /.form-group -->
							</div>
							<!--/.col-md-6-->
						</div>

					</div>

					<!-- /.col-md-3-->
				</div>
			</section>
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>