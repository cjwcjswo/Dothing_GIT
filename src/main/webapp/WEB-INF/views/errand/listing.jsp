<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="csrf-token" content="{{ csrf_token() }}">
<title>심부름</title>
<style type="text/css">
</style>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
<script>
	$(function(){
		if(${sort} > 0){
		$("#sort option:eq(${sort})").attr("selected", "selected");
		}
		$("#sort").change(function(){
			var opt = $(this).val();
			if(opt > 0){
			location.href = "${pageContext.request.contextPath}/errand/listing?sort="+opt;
			}
		})
		$("#basic-addon1").click(function(){
			document.f.submit();
		})
	})
</script>
<body onunload=""
	class="page-subpage page-listing-list navigation-off-canvas"
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
						<li><a
							href="${pageContext.request.contextPath}/errand/errand">심부름
								목록</a></li>
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
				<div class="row">
					<!--Content-->
					<div class="col-md-12">
						<header>
							<h1 class="page-title">심부름 리스트로 보기</h1>
						</header>
						<figure class="filter clearfix">
							<form
								action="${pageContext.request.contextPath}/errand/listing?sort=${sort}"
								method="post" name="f">
								<div class="input-group">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="text"
										class="form-control" placeholder="검색할 주소를 입력하세요"
										aria-describedby="basic-addon1" name="addr"> <span
										class="input-group-addon" id="basic-addon1"><i
										class="fa fa-search"></i></span>

								</div>
							</form>
							<div class="pull-right">
								<aside class="sorting">
									<span>Sorting</span>
									<div class="form-group">
										<select class="framed" name="sort" id="sort">
											<option value="0">정렬 방법</option>
											<option value="1">최신순</option>
											<option value="2">높은 가격 순</option>
											<option value="3">낮은 가격 순</option>
										</select>
									</div>
									<!-- /.form-group -->
								</aside>
							</div>
						</figure>

						<!--Listing List-->
						<section class="block listing">
							<c:forEach items="${errandsList}" var="errands">
								<div class="item list">
									<div class="image">
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}">
											<div class="overlay">
												<div class="inner">
													<div class="content">

														<p>${errands.content}</p>
													</div>
												</div>
											</div> <c:if test="${errands.errandsPhoto != 'EMPTY'}">
												<img
													src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
											</c:if> <c:if test="${errands.errandsPhoto == 'EMPTY'}">
												<img
													src="${pageContext.request.contextPath}/resources/img/errands/img.png" />
											</c:if>
										</a>
									</div>
									<div class="wrapper">
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}"><h3>${errands.title}</h3></a>
										<figure>${errands.errandsPos.addr}
										</figure>
										<c:set var="reqtotal" value="0" />
										<c:set var="reqCount" value="0" />
										<c:forEach items="${errands.requestUser.gpaList}" var="gpa">
											<c:if test="${gpa.responseKindness == 0}">
												<c:set var="reqtotal"
													value="${reqtotal + gpa.requestManners}" />
												<c:set var="reqCount" value="${reqCount + 1 }" />
											</c:if>
										</c:forEach>
										<figure class="rating big pull-right"
											data-rating="${reqtotal/reqCount}"></figure>
										<div class="price">
											심부름 삯:
											<fmt:formatNumber value="${errands.errandsPrice}" />
											원
										</div>
										<br>
										<div class="price">
											물건 값:
											<fmt:formatNumber value="${errands.productPrice}" />
											원
										</div>
										<div class="info">
											<div class="type">
												<span>${errands.requestUser.name}</span>
												<c:forEach items="${errands.hashes}" var="hash">
													<span class="label label-info">${hash}</span>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</section>
						<!--end Listing List-->
						<!--Pagination-->
						<nav>
							<ul class="pagination pull-right">
								<c:if test="${pm.previous}">
									<li><a
										href="${pageContext.request.contextPath}/errand/listing?page=${pm.lastPage - 5}&sort=${sort}&addr=${addr}"><span
											class="glyphicon glyphicon-chevron-left"></span></a></li>
								</c:if>
								<c:if test="${pm.startPage != pm.lastPage }">
									<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
										varStatus="state">
										<c:if
											test="${pm.currentPage == (pm.startPage + state.count-1)}">
											<li class="active"><a
												href='${pageContext.request.contextPath}/errand/listing?page=${pm.startPage + state.count-1}&sort=${sort}&addr=${addr}'>${pm.startPage + state.count-1}</a>
											</li>
										</c:if>
										<c:if
											test="${pm.currentPage != (pm.startPage + state.count-1)}">
											<li><a
												href='${pageContext.request.contextPath}/errand/listing?page=${pm.startPage + state.count-1}&sort=${sort}&addr=${addr}'>${pm.startPage + state.count-1}</a>
											</li>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${pm.next}">
									<li><a
										href="${pageContext.request.contextPath}/errand/listing?page=${pm.lastPage + 1}&sort=${sort}&addr=${addr}"><span
											class="glyphicon glyphicon-chevron-right"></span></a></li>
								</c:if>
							</ul>
						</nav>
						<!--end Pagination-->
					</div>


				</div>
			</section>
		</div>
	</div>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>