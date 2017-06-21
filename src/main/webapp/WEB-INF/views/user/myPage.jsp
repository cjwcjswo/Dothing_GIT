<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>


<title>마이페이지</title>
<script>
	function checkValid() {
		var form = document.f;
		if (form.currentPassword.value.trim() == "") {
			alert("비밀번호를 입력하세요");
			form.currentPassword.focus();
			return false;
		}
		if (form.newPassword.value.trim() != "") {
			if (form.newPassword.value.length < 4 || form.newPassword.value.length > 8) {
				alert("패스워드를 4~8자 까지 입력해주세요.")
				form.newPassword.focus();
				return false;
			}

		}
		if (form.preAddr.value.trim() != "") {
			if (form.detailAddr.value.trim() == "") {
				alert("상세 주소를 입력하세요");
				form.detailAddr.focus();
				return false;
			}
		}
		return true;
	}
</script>
</head>

<body onunload=""
	class="page-subpage page-sign-in navigation-off-canvas" id="page-top">


	<!-- Page Canvas-->
	<div id="page-canvas">

		<nav class="off-canvas-navigation">
			<header>메뉴</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">

					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li><a href="#">마이페이지</a>
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
						<li class="active"><a
							href="${pageContext.request.contextPath}/user/myPage"><h1
									class="page-title">개인 정보</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/errand/myRequest"><h1
									class="page-title">심부름 요청 및 수행 내역</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/user/safetyRegister"><h1
									class="page-title">안전심부름꾼 신청</h1></a></li>
						<li class=""><a href="${pageContext.request.contextPath}/user/charge"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>
					</ul>
				</header>
				<form name="f" role="form" method="post" action="update"
					onsubmit="return checkValid()" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-9">

							<div class="row">
								<!--Profile Picture-->
								<div class="col-md-3 col-sm-3">
									<section>
										<h3>
											<i class="fa fa-image"></i>프로필 사진
										</h3>
										<div id="holder" style="">

											<img
												src="${pageContext.request.contextPath}/users/${member.userId}/${member.selfImg}"
												alt="">
										</div>
										<input type="file" name="selfImgFile" id="upload">
									</section>
								</div>
								<!--/.col-md-3-->

								<!--Contact Info-->
								<div class="col-md-9 col-sm-9">
									<section>
										<h3>
											<i class="fa fa-user"></i>개인 정보
										</h3>
										<div class="row">
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="name">이름</label> <input type="text"
														class="form-control" id="name" name="name"
														value="${member.name}" readonly="readonly">
												</div>
												<!--/.form-group-->
											</div>
											<!--/.col-md-3-->
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="email">Email</label> <input type="email"
														class="form-control" id="email" name="email"
														value="${member.email}" readonly="readonly">
												</div>
												<!--/.form-group-->
											</div>
											<!--/.col-md-3-->
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="mobile">Mobile</label> <input type="text"
														class="form-control" id="phone" name="phone"
														value="${member.phone}" pattern="\d*" readonly="readonly">
													<a href="#"><i class="fa fa-phone"></i> <span>핸드폰
															인증하기</span></a>
												</div>
												<!--/.form-group-->
											</div>
											<!--/.col-md-3-->
											<div class="col-md-6 col-sm-6">
												<div class="form-group">
													<label for="phone">포인트</label> <input type="text"
														class="form-control" id="point" name="currentPoint"
														pattern="\d*" value="${member.point.currentPoint }"
														disabled> <a href="#"><i class="fa fa-krw"></i>
														<span>포인트 충전하기</span></a>


												</div>
												<!--/.form-group-->

											</div>


											<!--/.col-md-3-->
										</div>
									</section>
									<section>
										<h3>
											<i class="fa fa-map-marker"></i>Address
										</h3>
										<div class="form-group">
											<input class="form-control" type="text" name="preAddr"
												id="sample5_address" placeholder="address"
												readonly="readonly" /> <br> <input
												class="form-control" type="text" name="detailAddr"
												placeholder="상세주소" />
											<button type="button" onclick="sample5_execDaumPostcode()">
												<span class="glyphicon glyphicon-home" style="margin: auto"></span>주소찾기
											</button>
											<br>현재 등록된 주소:
											<security:authentication property='principal.addr' />
										</div>
									</section>






									<!--Reviews-->
									<section class="block" id="reviews">
										<header class="clearfix">
											<h2 class="pull-left">평가 정보</h2>

										</header>
										<article class="clearfix overall-rating">
											<strong class="pull-left">총 평점</strong>
											<figure class="rating big color pull-right" data-rating="4"></figure>


											<!-- /.rating -->

										</article>

										<br>
										<!-- /.overall-rating-->
										<section class="reviews">
											<article class="review">
												<!-- /.author-->
												<div class="wrapper">
													<h5>해쉬태그 및 상세 평점</h5>
													<figure class="rating big color" data-rating="4"></figure>
													<p>#친절함 #빠름 #응응</p>
													<div class="individual-rating">
														<span>신속도</span>
														<figure class="rating" data-rating="4"></figure>
													</div>
													<!-- /.user-rating -->
													<div class="individual-rating">
														<span>정확도</span>
														<figure class="rating" data-rating="4"></figure>
													</div>

													<div class="individual-rating">
														<span>친절도</span>
														<figure class="rating" data-rating="4"></figure>
													</div>
													<!-- /.user-rating -->
												</div>
												<!-- /.wrapper-->
											</article>
											<!-- /.review -->

											<!-- /.review -->
										</section>
										<!-- /.reviews-->
									</section>
									<!-- /#reviews -->
									<!--end Reviews-->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
									<div class="form-group">
										<button type="submit" class="btn btn-large btn-default"
											id="submit">Save Changes</button>
									</div>
									<!-- /.form-group -->
								</div>
								<!--/.col-md-6-->
							</div>
						</div>
						<!--Password-->
						<div class="col-md-3 col-sm-9">

							<div class="form-group">
								<label for="currentPassword">현재 비밀번호</label> <input
									type="password" class="form-control" id="currentPassword"
									name="currentPassword">
							</div>
							<!--/.form-group-->
							<div class="form-group">
								<label for="newPassword">새로운 비밀번호</label> <input type="password"
									class="form-control" id="newPassword" name="newPassword">
							</div>
							<!--/.form-group-->
							<div class="form-group">
								<label for="confirm-new-password">새로운 비밀번호 재입력</label> <input
									type="password" class="form-control" id="renewPassword"
									name="renewPassword">
							</div>
							<!-- /.form-group -->

						</div>
						<!-- /.col-md-3-->
					</div>
				</form>
			</section>




		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
	<script>
	
		var upload = document.getElementById('upload'),
			holder = document.getElementById('holder');
	
	
		upload.onchange = function(e) {
			e.preventDefault();
	
			var file = upload.files[0],
				reader = new FileReader();
			reader.onload = function(event) {
				var img = new Image();
				img.src = event.target.result;
				img.width = 200;
				img.height = 200;
				holder.innerHTML = '';
				holder.appendChild(img);
			};
			reader.readAsDataURL(file);
	
			return false;
		};
	
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
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>