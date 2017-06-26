<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>

<title>추가정보</title>

</head>

<body onunload="" class="page-subpage page-404 navigation-off-canvas"
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
					<h1 class="page-title" style="text-align: center; font-size: 30px">추가
						정보를 입력하세요</h1>
				</header>
				<form name="f" id="f" method="post"
					action="${pageContext.request.contextPath}/user/apiControl"
					onsubmit="return checkValid();">
					<input type="hidden" name="userId" value="${id}">
					<input type="hidden" name="email" value="${email}">
					<input type="hidden" name="name" value="${name}">
					<input type="hidden" name="photo" value="${photo}">
					<input type="hidden" name="sex" value="${gender}">
					<div class="block">
						<div class="form-group">
							<label for="form-register-full-name">핸드폰 번호</label> <input
								type="text" class="form-control" id="form-register-full-name"
								name="phone" id="phone"> <a href="#"><span
								class="glyphicon glyphicon-phone-alt" style="margin: auto"></span>핸드폰
								인증하기</a>
						</div>
						<div class="form-group">
							<input class="form-control" type="text" name="preAddr"
								id="sample5_address" placeholder="주소" readonly="readonly" /> <input
								class="form-control" type="text" name="detailAddr"
								id="addr-detail" placeholder="상세주소" />
							<button type="button" onClick="sample5_execDaumPostcode()">
								<span class="glyphicon glyphicon-home" style="margin: auto"></span>주소찾기
							</button>
						</div>
						<div class="form-group">
							<label for="form-register-full-name">자기소개</label>
							<textarea class="form-control" name="introduce" id="introduce"
								data-length="200" placeholder="200자 이내로 작성하세요."></textarea>
						</div>
						<
						<div class="form-group clearfix">
							<button type="submit" class="btn pull-right btn-default"
								id="account-submit">계정 생성하기</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
				</form>
			</section>
			<!-- /.block-->

		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->



	<script>
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

			}
		}).open();
}
</script>
<script>
function checkValid(){
var login_addr = document.f.preAddr
var login_daddr = document.f.detailAddr;
var login_pnum = document.f.phone;
//전화번호 입력여부 검사
if (login_pnum.value.trim() == "") {
	alert("전화번호를 입력하세요");
	login_pnum.focus();
	return false;
}

//전화번호 길이체크
if (login_pnum.value.length < 10 || login_pnum.value.length > 11) {
	alert("전화번호를 다시 입력해 주세요.");
	login_pnum.focus();
	login_pnum.select();
	return false;
}


//주소 입력여부 검사
if (login_addr.value == "") {
	alert("주소를 입력하세요");
	login_addr.focus();
	return false;
}


//상세주소 입력여부 검사
if (login_daddr.value == "") {
	alert("상세주소를 입력하세요");
	login_daddr.focus();
	return false;
}
return true;
}
</script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>