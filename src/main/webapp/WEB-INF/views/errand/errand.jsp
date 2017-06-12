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
	href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
</head>
<body>
	<div class="container" id="content">
		<div class="row" id="">
			<!--  필터  -->

		</div>
		<div class="row" style="width: 100%; height: 100%; z-index:0">
			<div class="col-xs-4" id="map">
				<!-- 지도 삽입 부분 -->

				<div id="map" style="width: 100%; height: 500px;"></div>
				<script type="text/javascript"
					src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
				<script>
					var lat = new Array();
					var lng = new Array();
					var addr = new Array();
					var size = ${errandsList.size()};
					for(var i=0; i<size; i++){
						switch(i){
						case 0:
							lat.push(${errandsList[0].errandsPos.latitude});
							lng.push(${errandsList[0].errandsPos.longitude});
							addr.push("${errandsList[0].errandsPos.addr}");
							break;
						case 1:
							lat.push(${errandsList[1].errandsPos.latitude});
							lng.push(${errandsList[1].errandsPos.longitude});
							addr.push("${errandsList[1].errandsPos.addr}");
							break;
						case 2:
							lat.push(${errandsList[2].errandsPos.latitude});
							lng.push(${errandsList[2].errandsPos.longitude});
							addr.push("${errandsList[2].errandsPos.addr}");
							break;
						case 3:
							lat.push(${errandsList[3].errandsPos.latitude});
							lng.push(${errandsList[3].errandsPos.longitude});
							addr.push("${errandsList[3].errandsPos.addr}");
							break;
						case 4:
							lat.push(${errandsList[4].errandsPos.latitude});
							lng.push(${errandsList[4].errandsPos.longitude});
							addr.push("${errandsList[4].errandsPos.addr}");
							break;
						case 5:
							lat.push(${errandsList[5].errandsPos.latitude});
							lng.push(${errandsList[5].errandsPos.longitude});
							addr.push("${errandsList[5].errandsPos.addr}");
							break;
						}
					}
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
						mapOption = {
							center : new daum.maps.LatLng(lat[0], lng[0]), // 지도의 중심좌표
							level : 7 // 지도의 확대 레벨
						};
				
					var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
					// 마커를 표시할 위치와 title 객체 배열입니다 
					var positions = [
					    {
					    	title : addr[0],
					        latlng: new daum.maps.LatLng(lat[0], lng[0])
					    },
					    {
					    	title : addr[1],
					        latlng: new daum.maps.LatLng(lat[1], lng[1])
					    },
					    {
					    	title : addr[2],
					        latlng: new daum.maps.LatLng(lat[2], lng[2])
					    },
					    {
					    	title : addr[3],
					        latlng: new daum.maps.LatLng(lat[3], lng[3])
					    },
					    {
					    	title : addr[4],
					        latlng: new daum.maps.LatLng(lat[4], lng[4])
					    },
					    {
					    	title : addr[5],
					        latlng: new daum.maps.LatLng(lat[5], lng[5])
					    },
					   
					];

				
					// 마커 이미지의 이미지 주소입니다
					var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
				
					for (var i = 0; i < positions.length; i++) {
				
						// 마커 이미지의 이미지 크기 입니다
						var imageSize = new daum.maps.Size(24, 35);
				
						// 마커 이미지를 생성합니다    
						var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);
				
						// 마커를 생성합니다
						var marker = new daum.maps.Marker({
							map : map, // 마커를 표시할 지도
							position : positions[i].latlng, // 마커를 표시할 위치
							title : positions[i].title,
							image : markerImage // 마커 이미지 
						});
					}
				</script>
			</div>
			<div class="col-xs-6" style="width: 50%; margin-left: 50%">
				<!-- 심부름 리스트  -->
				<table id="tb">
					<c:forEach items="${errandsList}" var="errands" varStatus="state">
						<tr>
							<th>
								<div class="col-md-6" style="width: 100%;">
									<div class="well well-sm">
										<div class="row">
											<div class="col-xs-3 col-md-3 text-center">
												<c:if test="${errands.errandsPhoto != null}">
													<img
														src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}"
														alt="bootsnipp" class="img-rounded img-responsive" />
												</c:if>
												<c:if test="${errands.errandsPhoto == null}">
													<img
														src="${pageContext.request.contextPath}/resources/img/errands/img.png"
														alt="bootsnipp" class="img-rounded img-responsive" />
												</c:if>
											</div>
											<div class="col-xs-9 col-md-9 section-box">
												<h2>${errands.title}</h2>
												작성자: ${errands.requestUser.userId}<br>
												<p>${errands.content}<br> 마감일시 : ${errands.endTime}
													| 물건 값: <a>${errands.productPrice}원</a> 심부름값:<a>${errands.errandsPrice}원</a>
												</p>
												<hr />
												<div class="row rating-desc">
													<div class="col-md-12">
														| <span class="glyphicon glyphicon-comment"></span>(${errands.errandsReply.size()}
														Comments) <a class="btn btn-danger"
															href="detailView?num=${errands.errandsNum}">신청하기</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div></th>
						</tr>
					</c:forEach>
					<tr>
						<th>
			<ul class="pagination" style="margin-left:35%">
				<c:if test="${pm.previous}">
					<li><a
						href="${pageContext.request.contextPath}/errand/errand?page=${pm.lastPage - 5}"><span
							class="glyphicon glyphicon-chevron-left"></span></a></li>
				</c:if>
				<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
					varStatus="state">
					<c:if test="${pm.currentPage == (pm.startPage + state.count-1)}">
						<li class="active"><a
							href='${pageContext.request.contextPath}/errand/errand?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
						</li>
					</c:if>
					<c:if test="${pm.currentPage != (pm.startPage + state.count-1)}">
						<li><a
							href='${pageContext.request.contextPath}/errand/errand?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${pm.next}">
					<li><a
						href="${pageContext.request.contextPath}/errand/errand?page=${pm.lastPage + 1}"><span
							class="glyphicon glyphicon-chevron-right"></span></a></li>
				</c:if>

			</ul>
						</th>
					</tr>
				</table>

			</div>

		</div>

	</div>

</body>
</html>