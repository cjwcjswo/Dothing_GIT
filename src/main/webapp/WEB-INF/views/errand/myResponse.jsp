<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<title>심부름 내역</title>

</head>

<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">


	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>Navigation</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->

		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="redefine-search">
						<a href="#redefine-search-form" class="inner"
							data-toggle="collapse" aria-expanded="false"
							aria-controls="redefine-search-form"> <span class="icon"></span>
							<span>Redefine Search</span>
						</a>
					</div>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li class="active">심부름 내역</li>
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
					<ul class="nav nav-pills">
						<li><a href="${pageContext.request.contextPath}/user/myPage"><h1
									class="page-title">개인정보</h1></a></li>
						<li class="active"><a
							href="${pageContext.request.contextPath}/errand/myRequest"><h1
									class="page-title">심부름 요청 및 수행 내역</h1></a></li>
					</ul>
				</header>
				<div class="row">
					<div class="col-md-3 col-sm-3">
						<aside id="sidebar">
							<ul class="navigation-sidebar list-unstyled">
								<li><a
									href="${pageContext.request.contextPath}/errand/myRequest">
										<i class="fa fa-check"></i> <span>심부름 요청 내역</span>
								</a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/errand/myResponse">
										<i class="fa fa-clock-o"></i> <span> 심부름 수행 내역</span>
								</a></li>

							</ul>
						</aside>
					</div>
					<div class="col-md-9 col-sm-9" id="history" style="height: 500px">
						<section id="items">
							<c:forEach items="${errandsList}" var="errands">
								<div class="item list admin-view">
									<div class="image">
										<c:if test="${errands.errandsPhoto != null}">
											<img
												src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
										</c:if>
										<c:if test="${errands.errandsPhoto == null}">
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png" />
										</c:if>
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}">
											<div class="item-specific">
												<span title="Bedrooms"><i class="fa fa-krw">E: <fmt:formatNumber
															value="${errands.errandsPrice}" /></i> <i class="fa fa-krw">
														P:<fmt:formatNumber value="${errands.productPrice}" />
												</i></span>

											</div> <!-- <div class="icon">
												<i class="fa fa-thumbs-up"></i>
												인증 심부름꾼이면 별 아니면 여기 div 지우셈
											</div> <img src="assets/img/items/1.jpg" alt=""> -->
										</a>
									</div>
									<div class="wrapper">
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}"><h3>${errands.title}</h3></a>
										<a>${errands.content}</a>
										<figure>${errands.errandsPos.addr}
										</figure>
										<div class="info">
											<div class="type">
												<div class="price">${errands.errandsReply.size()}개 댓글
													달림</div>
											</div>
											<c:if test="${errands.gpa == null}">
												<div class="rating" data-rating="0"></div>
											</c:if>
											<c:if test="${errands.gpa != null}">
												<div class="rating"
													data-rating="${errands.gpa.requestManners}"></div>
											</c:if>
											<br> <a>마감시간: ${errands.endTime}</a>
											<!-- 평점 -->
										</div>
									</div>
									<div class="description">
										<ul class="list-unstyled actions">

											<li><a href="#" class="hide-item"><i
													class="fa fa-eye"></i></a></li>
											<c:if test="${errands.responseUser == null}">
												<li><i class="fa fa-close"></i>채택 요청중</li>
											</c:if>
											<c:if test="${errands.responseUser != null}">
												<c:if test="${errands.finishTime == null}">
													<li><i class="fa fa-taxi"></i>채택 요청중</li>
												</c:if>
											</c:if>
											<c:if test="${errands.finishTime != null}">
												<li><i class="fa fa-check"></i>심부름 완료</li>
											</c:if>

										</ul>
									</div>
									<c:if test="${errands.finishTime != null}">
										<div class="ribbon">
											<!-- 완료 시 <! class="fa fa-check> 
                                        미완료시 <! class=fa fa-close" -->
										</div>
									</c:if>

								</div>
							</c:forEach>

						</section>
					</div>
				</div>
			</section>
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->
	<script>
		if ($('#history').length > 0) {
			$("#history").mCustomScrollbar();
		}
	</script>
</body>
</html>