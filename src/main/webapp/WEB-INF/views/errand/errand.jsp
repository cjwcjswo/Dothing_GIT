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
/**
알림 CSS
**/
.error {
	width: 250px;
	height: 20px;
	height: auto;
	position: fixed;
	left: 50%;
	margin-left: -125px;
	bottom: 100px;
	z-index: 9999;
	background-color: #383838;
	color: #F0F0F0;
	font-family: Calibri;
	font-size: 15px;
	padding: 10px;
	text-align: center;
	border-radius: 2px;
	-webkit-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	-moz-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>

<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
<script>
	function clickDetail(num) {
		location.href = "${pageContext.request.contextPath}/errand/detailView?num=" + num;
	}
</script>
<!-- SocketJS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/sockjs.js"></script>
<script>
	var today = '<%=new java.text.SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date())%>'
	var sock = new SockJS('${pageContext.request.contextPath}/errand/register');
	var msg = '새로운 심부름이 등록되었습니다.';
	var insertRe = <%=session.getAttribute("insertResult")%>
	
	$(function(){
		function sendMessage(){
			if(insertRe > 0){
				sock.send(msg);
				alert('심부름 등록 성공?');
			}
		}
		sendMessage();
	});
	
	
	sock.onopen = function() {
	    $('#console').append('websocket opened' + '<br>');	
	  	//스크롤 맨 아래로
	 	document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
	};
	
	sock.onmessage = function(message) {
		var receiveMessage = message.data;
		alert("receiveMessage : "+receiveMessage);
		//알림
		$("#notification").val(receiveMessage);
		$('.error').fadeIn(400).delay(3000).fadeOut(400);
		
		
	 
	};
	/*
	sock.onclose = function(event) {
	    $('#console').append('websocket closed : ' + event);
	};

  */
</script>
<script>
	if(${notRead} > 0){
		var options = {
	            body: "안읽은 알림이 ${notRead}개 있습니다 확인해주세요."
	        }
		var notification = new Notification("Dothing 알림",options);
	}
	function clickDetail(num) {
		location.href = "${pageContext.request.contextPath}/errand/detailView?num=" + num;
	}
	$(function() {
		$(document).on("click", "a[role='menuitem']", function() {
			$("#keyword").val($(this).text());
			$("#hashDrop li").remove();
			$(".dropdown-menu").hide();
			$("#minPrice").focus();
		});
		$("#keyword").keyup(function() {
			if ($(this).val()[0] == "#") {
				$("#hashDrop li").remove();
				$.ajax({
					url : "hash",
					type : "post",
					dataType : "json",
					data : "hash=" + $(this).val(),
					beforeSend : function(xhr) { /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
						xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
					},
					success : function(result) {
						var count = 0;
						var re = "";
						if (Object.keys(result.hashList).length == 0) {
							$("#hashDrop li").remove();
							$(".dropdown-menu").hide();
						} else {
							$.each(result['hashList'], function(index, item) {
								count++;
								var key = index;
								var value = item;
								re += "<li role='presentation'><a role='menuitem' tabindex='-1'>#" + key + "</a>[등록 태그 갯수 " + value + "개]</li><li role='presentation' class='divider'></li>";
								if (count == 5) return false;
							});
							count = 0;
							$("#hashDrop").append(re)
						}
					},
					error : function(err) {
						alert(err);
					}
				});
				$(".dropdown-menu").show();

			} else {
				$("#hashDrop li").remove();
				$(".dropdown-menu").hide();
			}
		})
	});
</script>
</head>
<body onunload=""
	class="map-fullscreen page-homepage navigation-off-canvas"
	id="page-top">

	<!-- error -->
	<div class='error' style='display: none' id='notification'></div>


	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>메뉴</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->
		<!--Page Content-->
		<div id="page-content">
			<!-- Map Canvas-->
			<div class="map-canvas list-width-30">
				<!-- Map -->
				<div class="map">
					<div class="toggle-navigation" style="z-index: 5">
						<div class="icon">
							<div class="line"></div>
							<div class="line"></div>
							<div class="line"></div>
						</div>
					</div>
					<!--/.toggle-navigation-->
					<div id="map" class="has-parallax"></div>
					<!--/#map-->
					<div class="search-bar horizontal">
						<div class="container-fluid" style="text-align: center">
							<div id="curAddr" style="margin: 0px"></div>
							<span class="label label-warning">총
								${errandsList.size()}건의 심부름이 등록되어있습니다</span>
						</div>
						<form class="main-search border-less-inputs" role="form" name="f"
							method="post" action="search">
							<div class="input-row">

								<!-- /.form-group -->
								<div class="form-group" style="width: 22%">
									<div class="dropdown">
										<input type="text" class="form-control" name="hash"
											id="keyword" placeholder="해시태그(ex: #꿀알바)">
										<ul class="dropdown-menu" role="menu"
											aria-labelledby="dropdownMenu1" id="hashDrop">

										</ul>
									</div>
								</div>
								<div class="form-group" style="width: 22%">
									<input type="number" class="form-control" name="minPrice"
										placeholder="최소가격(0원 부터)" min="0" id="minPrice">

								</div>
								<div class="form-group" style="width: 22%">
									<input type="number" class="form-control" name="maxPrice"
										placeholder="최대가격" min="0">

								</div>
								<div class="form-group" style="width: 15%">
									<input type="radio" name="distance" value="1"><b>1km</b>
									<input type="radio" name="distance" value="3"><b>3km</b><br>
									<input type="radio" name="distance" value="10"><b>10km</b>
									<input type="radio" name="distance" value="0" checked="checked"><b>제한없음</b>
									<input type="hidden" name="sLat" id="sLat" value="37.5327619">
									<input type="hidden" name="sLng" id="sLng" value="127.0139427">
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<button type="submit" class="btn btn-default">
									<i class="fa fa-search"></i>
								</button>
								<button type="button" class="btn btn-info"
									onclick="currentLocation()">
									<i class="fa fa-map-marker geolocation" data-toggle="tooltip"
										data-placement="bottom" title="내 위치 찾기"></i>
								</button>
								<!-- /.form-group -->
							</div>
							<!-- /.input-row -->
						</form>
						<!-- /.main-search -->

					</div>
					<!-- /.search-bar -->
				</div>
				<!-- end Map -->
				<!--Items List-->
				<div class="items-list">
					<div class="inner">
						<header>
							<div class="row">
								<div class="col-sm-6">
									<h3>검색 결과</h3>
								</div>
								<div class="col-sm-6">
									<a href="listing-grid.html" class="btn icon "><i
										class="fa fa-th"></i>Grid</a> <a href="listing-list.html"
										class="btn icon"><i class="fa fa-th-list"></i>List</a>
								</div>
							</div>
						</header>
						<ul class="results list">
							<c:forEach items="${errandsList}" var="errands" varStatus="state">
								<input type="hidden" id="lat${state.index}"
									value="${errands.errandsPos.latitude}">
								<input type="hidden" id="lng${state.index}"
									value="${errands.errandsPos.longitude}">
								<input type="hidden" id="addr${state.index}"
									value="${errands.errandsPos.addr}">
								<li onclick="clickDetail(${errands.errandsNum})">
									<div class="item">
										<a class="image">
											<div class="inner">
												<div class="item-specific">
													<c:if test="${errands.hashes.size()	 != 0}">
														<c:forEach items="${errands.hashes}" var="hash">
															<span class="label label-info">#${hash}</span>
														</c:forEach>
													</c:if>

												</div>
												<c:if test="${errands.errandsPhoto != null}">
													<img
														src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
												</c:if>
												<c:if test="${errands.errandsPhoto == null}">
													<img
														src="${pageContext.request.contextPath}/resources/img/errands/img.png" />
												</c:if>
											</div>

										</a>
										<div class="wrapper">
											<a href="#" id="' + json.data[a].id + '">
												<h3>${errands.title}</h3>
											</a>
											<figure>${errands.errandsPos.addr}</figure>
											<div class="price">
												<fmt:formatNumber value="${errands.errandsPrice}" />
												원
											</div>
											<span class="label label-success">${errands.errandsReply.size()}명
												지원!</span>
											<div class="info">
												<div class="type">
													<span>${errands.endTime}</span>
												</div>
											</div>
										</div>
									</div>

								</li>
							</c:forEach>
						</ul>
					</div>
					<!--results-->
				</div>
				<!--end Items List-->
			</div>
			<!-- end Map Canvas-->
			<!--Featured-->
			<section id="featured"
				class="block background-color-grey-dark equal-height">
				<div class="container">
					<header>
						<h2>최고의 심부름꾼</h2>
					</header>
					<div class="row">
						<c:forEach items="${rankedList}" var="ranked" end="3"
							varStatus="status">
							<div class="col-md-3 col-sm-3">
								<div class="item featured">
									<div class="image">
											<div class="overlay">
												<div class="inner">
													<div class="content">
														<p>정확성: ${ranked.gpaList[0].responseAccuracy}</p>
														<p>친절성: ${ranked.gpaList[0].responseKindness}</p>
														<p>신속성: ${ranked.gpaList[0].responseSpeed}</p>
														<p>매너: ${ranked.gpaList[0].requestManners}</p>
													</div>
												</div>
											</div> <c:if test="${status.index == 0}">
												<div class="icon">
													<i class="fa fa-thumbs-up"></i>
												</div>
											</c:if><img
											src="${pageContext.request.contextPath}/users/${ranked.userId}/${ranked.selfImg}"
											alt="">
										</a>
									</div>
									<div class="wrapper"><h3>${ranked.userId}</h3></a>
										<figure>${ranked.addr}
										</figure>
										<div class="info">
											<div class="type">
												<c:forEach items="${ranked.hashList}" var="hash" end="5">
													<span class="label label-info">${hash.hashtag}</span>
												</c:forEach>
											</div>
											<div class="rating"
												data-rating="${(ranked.gpaList[0].responseAccuracy + ranked.gpaList[0].responseKindness + ranked.gpaList[0].responseSpeed + ranked.gpaList[0].requestManners) / 4}"></div>
										</div>
									</div>
								</div>
								<!-- /.item-->
							</div>
						</c:forEach>
					</div>
					<!--/.row-->
				</div>
				<!--/.container-->
			</section>
			<!--end Featured-->

			
			<section class="block equal-height">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<header>
								<h2>
									<i class="fa fa-money"></i>돈되는 심부름
								</h2>
							</header>
							<div class="row">
								<c:forEach items="${moneyList}" var="money" varStatus="status">
									<div class="col-md-4 col-sm-4">
										<div class="item">
											<div class="image">
												<a
													href="${pageContext.request.contextPath}/errand/detailView?num=${money.errandsNum}">
													<div class="overlay">
														<div class="inner">
															<div class="content">
																<h4>내용</h4>
																<p>${money.content}</p>
															</div>
														</div>
													</div> <c:if test="${status.index == 0}">
														<div class="icon">
															<i class="fa fa-thumbs-up"></i>
														</div>
													</c:if> <c:if test="${money.errandsPhoto != null}">
														<img
															src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
													</c:if> <c:if test="${money.errandsPhoto == null}">
														<img
															src="${pageContext.request.contextPath}/resources/img/errands/img.png" />
													</c:if>
												</a>
											</div>
											<div class="wrapper">
												<a
													href="${pageContext.request.contextPath}/errand/detailView?num=${money.errandsNum}"><h3>${money.title}</h3></a>
												<figure>${money.errandsPos.addr}
												</figure>
												<div class="price">
													<fmt:formatNumber value="${money.errandsPrice}" />
													원
												</div>
												<span class="label label-success">${money.errandsReply.size()}명
													지원중!</span>
												<div class="info">
													<div class="type">
														<c:forEach items="${money.hashes}" var="hash">
															<span class="label label-primary">${hash}</span>
														</c:forEach>
													</div>
													<div class="rating"
														data-rating="${money.requestUser.gpaList[0].requestManners}"></div>
												</div>
											</div>
										</div>
										<!-- /.item-->
									</div>
								</c:forEach>

							</div>
							<!--/.row-->



							<!--end Categories-->
						</div>
						<!--/.col-md-9-->
					</div>
					<!--/.row-->
				</div>
			</section>

		
			<!--Subscribe-->
			<section id="subscribe" class="block">
				<div class="container">
					<header>
						<h2>구독하기</h2>
					</header>
					<form class="subscribe form-inline border-less-inputs" action="?"
						method="post" role="form">
						<div class="input-group">
							<input type="email" class="form-control" id="subscribe_email"
								placeholder="메일을 작성해서 DoThing의 새로운 소식을 받아보세요!"> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-default btn-large">
									구독완료<i class="fa fa-angle-right"></i>
								</button>
							</span>
						</div>
					</form>
					<!--/.subscribe-->
				</div>
				<!--/.container-->
			</section>
			<!--end Subscribe-->
			
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
	<script>
	var lat = 37.5327619;
	var lng = 127.0139427;
	// 지도에 표시할 원을 생성합니다
	 var circle = new daum.maps.Circle({
	     center : new daum.maps.LatLng(lat, lng),  // 원의 중심좌표 입니다 
	     radius: f.distance.value * 1000, // 미터 단위의 원의 반지름입니다 
	     strokeWeight: 5, // 선의 두께입니다 
	     strokeColor: '#75B8FA', // 선의 색깔입니다
	     strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
	     strokeStyle: 'dashed', // 선의 스타일 입니다
	     fillColor: '#CFE7FF', // 채우기 색깔입니다
	     fillOpacity: 0.7  // 채우기 불투명도 입니다   
	 }); 
	 var sLat = document.getElementById("sLat");
	 var sLng = document.getElementById("sLng");
    // Scrollbar on "Results" section
    if( $('.items-list').length > 0 ){
        $(".items-list").mCustomScrollbar({
            mouseWheel:{ scrollAmount: 350}
        });
    }
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new daum.maps.LatLng(lat, lng), // 지도의 중심좌표
				level : 6 // 지도의 확대 레벨
			};
	
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption);
		function currentLocation(){
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
				function nowLocation(position) {
					lat = position.coords.latitude;
					lng = position.coords.longitude;
					// 지도 레벨을 4로 설정한다
					map.setLevel(3, {anchor: new daum.maps.LatLng(lat, lng)});
					
					// 마커가 표시될 위치입니다 
					var markerPosition  = new daum.maps.LatLng(lat, lng); 

					// 마커를 생성합니다
					var marker = new daum.maps.Marker({
					    position: markerPosition
					});

					// 마커가 지도 위에 표시되도록 설정합니다
					marker.setMap(null);    
					marker.setMap(map);
					
					  var moveLatLon = new daum.maps.LatLng(lat, lng);
					    
					    // 지도 중심을 이동 시킵니다
					    map.setCenter(moveLatLon);

				},
				function(error) {
					console.log(error);
				}
			);
		}
		}
		function mapRe() {
			map.relayout();
		}
		$('.map .toggle-navigation').click(function() {
			$('.map-canvas').toggleClass('results-collapsed');
			setTimeout("mapRe()", 1000)
	
		});
		// Set if language is RTL and load Owl Carousel
	
		$(window).load(function() {
			var rtl = false; // Use RTL
			initializeOwl(rtl);
		});
	
		map.relayout();
		setTimeout("mapRe()", 3000);
		
		
		 var lat = new Array();
         var lng = new Array();
         var addr = new Array();
         var size = ${errandsList.size()};
         for(var i=0; i<size; i++){
        	 lat.push(document.getElementById("lat"+i).value);
        	 lng.push(document.getElementById("lng"+i).value);
        	 addr.push(document.getElementById("addr"+i).value);
         }
         
         var posArr = new Array();
         
         
         for(var i=0; i<size; i++){
        	 var positions = {
            		 title : addr[i],
            		 latlng :  new daum.maps.LatLng(lat[i], lng[i])
             };
        	 posArr.push(positions);
         }
         
         // 마커 이미지의 이미지 주소입니다
         var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      
         for (var i = 0; i < posArr.length; i++) {
      
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new daum.maps.Size(24, 35);
      
            // 마커 이미지를 생성합니다    
            var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);
      
            // 마커를 생성합니다
            var marker = new daum.maps.Marker({
               map : map, // 마커를 표시할 지도
               position : posArr[i].latlng, // 마커를 표시할 위치
               title : posArr[i].title,
               image : markerImage // 마커 이미지 
            });
            
         }
         function moveCenter(){
         var moveLatLon = new daum.maps.LatLng(lat[0], lng[0]);
		    // 지도 중심을 이동 시킵니다
		    map.panTo(moveLatLon);
		 }
         setTimeout("moveCenter()", 1000)
         
         
         // 지도가 움직일때마다 중심 좌표 표시
         var geocoder = new daum.maps.services.Geocoder();
         
         function searchAddrFromCoords(coords, callback) {
        	    // 좌표로 행정동 주소 정보를 요청합니다
        	    geocoder.coord2addr(coords, callback);         
        	}
         
      // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
         function displayCenterInfo(status, result) {
             if (status === daum.maps.services.Status.OK) {
                 var infoDiv = document.getElementById('curAddr');
                 infoDiv.innerHTML =  "<b>현재 주소:"+result[0].fullName+"</b>";
             }    
         }
         searchAddrFromCoords(map.getCenter(), displayCenterInfo);
         daum.maps.event.addListener(map, 'idle', function() {
        	 var mLat = map.getCenter().getLat();
        	 var mLng = map.getCenter().getLng();
        	 circle.setPosition(new daum.maps.LatLng(mLat, mLng));
        	 circle.setRadius(f.distance.value * 1000);
        	 circle.setMap(null);
        	circle.setMap(map);
        	    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        	    sLat.value = mLat;
        	    sLng.value = mLng;
        	});
         $("input[type='radio']").click(function(){
        	 var mLat = map.getCenter().getLat();
        	 var mLng = map.getCenter().getLng();
        	 circle.setPosition(new daum.maps.LatLng(mLat, mLng));
        	 circle.setRadius(f.distance.value * 1000);
        	 circle.setMap(null);
        	circle.setMap(map);
         });
	</script>
</body>
</html>