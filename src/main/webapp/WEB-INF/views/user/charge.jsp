<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>충전하기</title>


<script type="text/javascript">

	function chargePoint() {
		var select = $("#sel1 option:selected").val();
		var way = $("input:radio[name=radioValue]:checked").val();

		if (way == null) {
			alert("결제 방식을 선택해주세요.");
			return false;
		}
		location.href = "${pageContext.request.contextPath}/user/pointCharge?select=" + select + "&way=" + way;
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
						<li class="active">포인트 충전 및 내역</li>
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
						<li><a
							href="${pageContext.request.contextPath}/errand/myRequest"><h1
									class="page-title">심부름 요청 및 수행 내역</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/user/safetyRegister"><h1
									class="page-title">안전 심부름꾼 신청</h1></a></li>
						<li class="active"><a
							href="${pageContext.request.contextPath}/user/charge"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>
					</ul>
				</header>
				<table class="table table-hover">
					<thead align="left">
						<tr>

							<td align="left">결제 수단 :<label class="radio-inline"><input
									type="radio" name="radioValue" value="bandBook">무통장 입금</label>
								<label class="radio-inline"><input type="radio"
									name="radioValue" value="card">카드결제</label></td>
						</tr>
					</thead>
					<tbody align="left">
						<tr>
							<div class="form-group col-xs-4">
								<label for="sel1">포인트 액수</label> <select class="form-control"
									id="sel1">
									<option value="5000">5,000p(5,000원)</option>
									<option value="10000">10,000p(10,000원)</option>
									<option value="20000">20,000p(20,000원)</option>
									<option value="50000">50,000p(50,000원)</option>
									<option value="100000">100,000p(100,000원)</option>

								</select>
							</div>

						</tr>


					</tbody>

				</table>
				<div class="form-group" align="center">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}"> <input type="hidden" id="select" />
					<input type="hidden" id="way" />
					<button type="submit" class="btn btn-large btn-default" id="submit"
						onclick="chargePoint()">충전</button>


				</div>

				<h1>
					<i class="fa fa-check"></i>포인트 사용 내역
				</h1>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>물품 포인트</th>
							<th>심부름 포인트</th>
							<th>사용 시간</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${empty requestScope.list}">
							<tr>
								<td colspan="3">
									<div align="center">
										<b><span style="font-size: 9pt;">포인트 사용 내역이 없습니다.</span></b>
									</div>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.list}" var="errandDto">
								<tbody>
									<tr>
										<td><c:choose>
												<c:when test="${0 eq errandDto.productPrice}">
						0
						</c:when>
												<c:otherwise>
						-${errandDto.productPrice}
						</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when test="${0 eq errandDto.errandsPrice}">
						0
						</c:when>
												<c:otherwise>
						-${errandDto.errandsPrice}
						</c:otherwise>
											</c:choose></td>
										</td>
										<td>${errandDto.startTime}</td>

									</tr>
								</tbody>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</table>

				<h1>
					<i class="fa fa-check"></i>심부름 수행으로 받은 내역
				</h1>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>물품 포인트</th>
							<th>심부름 포인트</th>
							<th>수행 완료 시간</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${empty requestScope.successlist}">
							<tr>
								<td colspan="5">
									<div align="center">
										<b><span style="font-size: 9pt;">심부름 수행 내역이 없습니다.</span></b>
									</div>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.successList}"
								var="errandDtoSuccess">
								<tbody>
									<tr>
										<td>${errandDtoSuccess.productPrice}</td>
										<td><c:choose>
												<c:when test="${0 eq errandDtoSuccess.errandsPrice}">
						0
						</c:when>
												<c:otherwise>
						+${errandDtoSuccess.errandsPrice}
						</c:otherwise>
											</c:choose></td>
										</td>
										<td>${errandDtoSuccess.finishTime}</td>
									</tr>
								</tbody>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<!-- <tbody>
					<tr>
						<td>2017-06-19</td>
						<td>5,000</td>
						<td>0</td>
						<td>12,000</td>
						<td>-</td>

					</tr>

				</tbody> -->
				</table>


				<!-- 페이지네이션 -->

				<!-- <ul class="pager">
				<li><a href="#">Previous</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">Next</a></li>
			</ul> -->

				<!-- 페이지네이션 종료 -->
				<!-- /.form-group -->
			</section>


		</div>
		<!--/.col-md-6-->
	</div>

	<!-- </div>



	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>