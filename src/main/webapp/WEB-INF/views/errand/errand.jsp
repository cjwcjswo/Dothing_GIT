<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/errands/errands.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.js">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
</head>
<body>
	<br><br><br><br>
	<div style="height: 15%;"></div>

	<div class="container" id="content">
		<div class="row" id="">
			<!--  필터  -->

		</div>
		<div class="row" style="width: 100%; height: 100%;">
			<div class="col-xs-4" id="map">
				<!-- 지도 삽입 부분 -->

				<iframe width="100%" height="500px" frameborder="0" scrolling="no"
					marginheight="0" marginwidth="0"
					src="https://maps.google.co.uk/maps?f=q&source=s_q&hl=en&geocode=&q=15+Springfield+Way,+Hythe,+CT21+5SH&aq=t&sll=52.8382,-2.327815&sspn=8.047465,13.666992&ie=UTF8&hq=&hnear=15+Springfield+Way,+Hythe+CT21+5SH,+United+Kingdom&t=m&z=14&ll=51.077429,1.121722&output=embed"></iframe>


			</div>
			<div class="col-xs-6" style="width:50%">
				<!-- 심부름 리스트  -->
				<table id="tb">
					<c:forEach begin="1" end="6">
						<tr>
							<th>
								<div class="col-md-6">
									<div class="well well-sm">
										<div class="row">
											<div class="col-xs-3 col-md-3 text-center">
												<img
													src="${pageContext.request.contextPath}/resources/img/mac.jpg"
													alt="bootsnipp" class="img-rounded img-responsive" />
											</div>
											<div class="col-xs-9 col-md-9 section-box">
												<h2>맥도날드 사주세요</h2>
												<p>
													참깨빵 위에 순쇠고기 패티 두장 특별한 소스, 양상추, 치즈 피클 양파까지<br> 등록일시 :
													2017-05-31 14:40
												</p>
												<hr />
												<div class="row rating-desc">
													<div class="col-md-12">
														<span class="glyphicon glyphicon-heart"></span><span
															class="glyphicon glyphicon-heart"> </span><span
															class="glyphicon glyphicon-heart"></span><span
															class="glyphicon glyphicon-heart"> </span><span
															class="glyphicon glyphicon-heart"></span>(36)<span
															class="separator">|</span> <span
															class="glyphicon glyphicon-comment"></span>(5 Comments) <a
															class="btn btn-danger" href="orderForm">신청하기</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</th>
						</tr>
					</c:forEach>
					<tr>
						<th>
							<ul class="pagination" style="margin-left:15%">
								<li class="disabled"><a href="#">«</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">»</a></li>
							</ul>
						</th>
					</tr>
				</table>

			</div>

		</div>

	</div>

</body>
</html>