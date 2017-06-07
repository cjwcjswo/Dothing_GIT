<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
.title {font-weight:bold;display:block;}
.bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
</head>
<body>

	<div id="map" style="width: 500px; height: 500px"></div>
	<input type="text" id="sample5_address" placeholder="주소" readonly="readonly">
	<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색">
	<br>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
			level : 5
		// 지도의 확대 레벨
		};

		//지도를 미리 생성
		var map = new daum.maps.Map(mapContainer, mapOption);
		//주소-좌표 변환 객체를 생성
		var geocoder = new daum.maps.services.Geocoder();
		//마커를 미리 생성
		var marker = new daum.maps.Marker({
			position : new daum.maps.LatLng(37.537187, 127.005476),
			map : map
		});
		
		 infowindow = new daum.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
		 daum.maps.event.addListener(map, 'click', function(mouseEvent) {
		     searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
		         if (status === daum.maps.services.Status.OK) {
		      
		             var detailAddr = '<div>주소 : ' + result[0].jibunAddress.name + '</div>';
		             
		             var content = '<div class="bAddr">' +
		                             '<span class="title">주소정보</span>' + 
		                             detailAddr + 
		                         '</div>';

		             // 마커를 클릭한 위치에 표시합니다 
		             marker.setPosition(mouseEvent.latLng);
		             marker.setMap(map);

		             // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
		             infowindow.setContent(content);
		             infowindow.open(map, marker);
		             
		             document.getElementById("sample5_address").value = result[0].jibunAddress.name;
		         }   
		     });
		 });
		 
		 function searchAddrFromCoords(coords, callback) {
			    // 좌표로 행정동 주소 정보를 요청합니다
			    geocoder.coord2addr(coords, callback);         
			}

			function searchDetailAddrFromCoords(coords, callback) {
			    // 좌표로 법정동 상세 주소 정보를 요청합니다
			    geocoder.coord2detailaddr(coords, callback);
			}

			
		function sample5_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var fullAddr = data.address; // 최종 주소 변수
							var extraAddr = ''; // 조합형 주소 변수

							// 기본 주소가 도로명 타입일때 조합한다.
							if (data.addressType === 'R') {
								//법정동명이 있을 경우 추가한다.
								if (data.bname !== '') {
									extraAddr += data.bname;
								}
								// 건물명이 있을 경우 추가한다.
								if (data.buildingName !== '') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
								fullAddr += (extraAddr !== '' ? ' ('
										+ extraAddr + ')' : '');
							}

							// 주소 정보를 해당 필드에 넣는다.
							document.getElementById("sample5_address").value = fullAddr;
							// 주소로 좌표를 검색
							geocoder.addr2coord(data.address, function(status,
									result) {
								// 정상적으로 검색이 완료됐으면
								if (status === daum.maps.services.Status.OK) {
									// 해당 주소에 대한 좌표를 받아서
									var coords = new daum.maps.LatLng(
											result.addr[0].lat,
											result.addr[0].lng);
									// 지도를 보여준다.
									mapContainer.style.display = "block";
									map.relayout();
									// 지도 중심을 변경한다.
									map.setCenter(coords);
									// 마커를 결과값으로 받은 위치로 옮긴다.
									marker.setPosition(coords);
									
								
						             var detailAddr = '<div>주소 : ' + fullAddr + '</div>';
						             
						             var content = '<div class="bAddr">' +
						                             '<span class="title">주소정보</span>' + 
						                             detailAddr + 
						                         '</div>';
						             infowindow.setContent(content);
						             infowindow.open(map, marker);
								}
							});
						}
					}).open();
		}
	</script>
</body>
</html>