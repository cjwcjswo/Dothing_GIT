function sample5_execDaumPostcode() {
	var geocoder = new daum.maps.services.Geocoder();
	new daum.Postcode(
			{
				oncomplete : function(data) {
					if (data.userSelectedType == "R") {
						// userSelectedType : 검색 결과에서 사용자가 선택한 주소의 타입
						// return type : R - roadAddress, J : jibunAddress
						// TestApp 은 안드로이드에서 등록한 이름
						window.TestApp.setAddress(data.zonecode,
								data.roadAddress, data.buildingName);
					} else {
						window.TestApp.setAddress(data.zonecode,
								data.jibunAddress, data.buildingName);
					}
					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var fullAddr = data.address; // 최종 주소 변수
					var extraAddr = ''; // 조합형 주소 변수

					// 기본 주소가 도로명 타입일때 조합한다.
					if (data.addressType === 'R') {
						// 법정동명이 있을 경우 추가한다.
						if (data.bname !== '') {
							extraAddr += data.bname;
						}
						// 건물명이 있을 경우 추가한다.
						if (data.buildingName !== '') {
							extraAddr += (extraAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
						fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')'
								: '');
					}

					// 주소 정보를 해당 필드에 넣는다.
					document.getElementById("sample5_address").value = fullAddr;
					geocoder
							.addr2coord(
									data.address,
									function(status, result) {
										// 정상적으로 검색이 완료됐으면
										if (status === daum.maps.services.Status.OK) {
											// 해당 주소에 대한 좌표를 받아서
											var coords = new daum.maps.LatLng(
													result.addr[0].lat,
													result.addr[0].lng);
											document.getElementById("latitude").value = result.addr[0].lat;
											document
													.getElementById("longitude").value = result.addr[0].lng;

										}
									});
				}
			}).open();
}