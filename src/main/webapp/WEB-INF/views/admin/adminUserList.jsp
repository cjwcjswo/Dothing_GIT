<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>Spotter - Universal Directory Listing HTML Template</title>
<style>
.stylish-input-group .input-group-addon {
	background: white !important;
}

.stylish-input-group .form-control {
	border-right: 0;
	box-shadow: 0 0 0;
	border-color: #ccc;
}

.stylish-input-group button {
	border: 0;
	background: transparent;
}
</style>
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
					<section class="container">
						<header>
							<ul class="nav nav-pills">
								<li><a
									href="${pageContext.request.contextPath}/admin/adminMoney"><h1
											class="page-title">무통장 입금 확인</h1></a></li>
								<li><a
									href="${pageContext.request.contextPath}/admin/adminSafe"><h1
											class="page-title">안전 심부름꾼 승인</h1></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/adminUserList"><h1
											class="page-title">회원관리</h1></a></li>
							</ul>
						</header>



						<div class="row">

							<div class="col-md-9">

								<div class="row">

									<!-- 서치바 -->
									<form
										action="${pageContext.request.contextPath}/admin/adminUserList">
										<div class="col-sm-6 col-sm-offset-3" style="margin-left: 10%">
											<div id="imaginary_container">
												<div class="input-group stylish-input-group input-append">
													<input type="text" class="form-control" name="id"
														placeholder="Search"> <span
														class="input-group-addon">
														<button type="submit">
															<span class="glyphicon glyphicon-search"></span>
														</button> <input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													</span>
												</div>
											</div>
										</div>
									</form>

									<!-- 서치바 종료 -->




									<!--Contact Info-->
									<div class="col-md-9 col-sm-9">
										<!-- 	아이디 포인트액수 비밀번호확인 승인 버튼 삭제버튼  -->
										<table class="table table-hover">
											<thead>
												<tr>
													<th>아이디</th>
													<th>이름</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach items="${memberList}" var="member">
													<tr>
														<td>${member.userId}</td>

														<td>${member.name}</td>
														<td><a
															href="${pageContext.request.contextPath}/admin/delete?userId=${member.userId}"><i
																class="fa fa-trash"> </i></a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>









										<!-- 페이지네이션 -->

										<ul class="pager">
											<c:if test="${pm.previous}">
												<li><a
													href="${pageContext.request.contextPath}/admin/adminUserList?page=${pm.lastPage - 5}&id=${sid}"><span
														class="glyphicon glyphicon-chevron-left"></span></a></li>
											</c:if>
											<c:if test="${pm.startPage != pm.lastPage }">
												<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
													varStatus="state">
													<c:if
														test="${pm.currentPage == (pm.startPage + state.count-1)}">
														<li class="active"><a
															href='${pageContext.request.contextPath}/admin/adminUserList?page=${pm.startPage + state.count-1}&id=${sid}'>${pm.startPage + state.count-1}</a>
														</li>
													</c:if>
													<c:if
														test="${pm.currentPage != (pm.startPage + state.count-1)}">
														<li><a
															href='${pageContext.request.contextPath}/admin/adminUserList?page=${pm.startPage + state.count-1}&id=${sid}'>${pm.startPage + state.count-1}</a>
														</li>
													</c:if>
												</c:forEach>
											</c:if>
											<c:if test="${pm.next}">
												<li><a
													href="${pageContext.request.contextPath}/admin/adminUserList?page=${pm.lastPage + 1}&id=${sid}"><span
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

		</div>
		<!-- end Inner Wrapper -->
	</div>
	<!-- end Outer Wrapper-->



	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>