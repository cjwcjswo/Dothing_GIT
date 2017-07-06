<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


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
					<input type="hidden" name="userId" value="${id}"> <input
						type="hidden" name="name" value="${name}"> <input
						type="hidden" name="photo" value="${photo}"> <input
						type="hidden" name="sex" value="${gender}">
						<input type="hidden" name="password" value="${pw}"/>
					<div class="block">
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
						<input class="form-control" type="hidden" name="latitude"
							id="latitude" /> <input class="form-control" type="hidden"
							name="longitude" id="longitude" />
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

	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/addrInput.js"></script>
	<script>
		function checkValid() {
			var login_addr = document.f.preAddr
			var login_daddr = document.f.detailAddr;
			var login_pnum = document.f.phone;

			//주소 입력여부 검사
			if (login_addr.value == "") {
				swal("입력 오류!", "주소를 입력하세요", "error");
				login_addr.focus();
				return false;
			}

			//상세주소 입력여부 검사
			if (login_daddr.value == "") {
				alert("입력 오류!", "상세주소를 입력하세요", "error");
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