<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
	<!-- google platform library -->
<script src="https://apis.google.com/js/platform.js" async defer></script>

<!-- specify client ID -->
<meta name="google-signin-client_id" content="852010525738-koadhapuooddd7np0govnv6lfgg5tsqf.apps.googleusercontent.com">

	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
<title>회원가입</title>

<script type="text/javascript">
	$(function() {

		$("#userId").keyup(function() {
			if ($(this).val() == '') {
				$('#form span[attr="dup"]').text('중복여부');
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/user/check",
				type : "post",
				data : "userId=" + $(this).val() + "&_csrf=${_csrf.token}",
				dataType : "text",
				success : function(result) {
					if (result == "사용중입니다") {
						$('#btn').attr("disabled", true);
					} else {
						$('#btn').attr("disabled", false);
					}
					$('#form span[attr="dup"]').text(result);
				},
				error : function(err) {
					alert("err = " + err)
				}
			})
		});
	})
	function checkValid() {
		var login_id = document.getElementById("userId"); //html 에서 설정한 id값을 변수login_id 에 저장
		var login_pw = document.getElementById("password");
		var login_pw2 = document.getElementById("password-repeat");
		var login_email = document.getElementById("email");
		var login_name = document.getElementById("name");
		var login_pnum = document.getElementById("phone");
		var login_man = document.getElementById("man");
		var login_woman = document.getElementById("woman");
		var login_addr = document.getElementById("sample5_address");
		var login_daddr = document.getElementById("addr-detail");
		var login_pic = document.getElementById("upload");

		//아이디 입력여부 검사
		if (login_id.value == "") { //id의 값이 null이면 창 띄우고 커서 옮김
			alert("아이디를 입력하세요");
			login_id.focus();
			return false;
		}
		;

		//아이디 유효성 검사(영어소문자, 숫자만 허용)
		for (var i = 0; i < login_id.value.length; i++) {
			ch = login_id.value.charAt(i)
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')) {
				alert("아이디는 소문자, 숫자만 입력가능합니다.")
				login_id.focus()
				login_id.select()
				login_id.value = ""
				return false;
			}
		}
		;

		//아이디 길이체크
		if (login_id.value.length < 3 || login_id.value.length > 12) {
			alert("아이디를 3~12자 까지 입력해주세요.")
			login_id.focus()
			login_id.select()
			return false;
		}
		;

		//패스워드 입력여부 검사
		if (login_pw.value == "") {
			alert("비밀번호를 입력하세요");
			login_pw.focus();
			return false;
		}
		;

		//패스워드 길이체크
		if (login_pw.value.length < 4 || login_pw.value.length > 8) {
			alert("패스워드를 4~8자 까지 입력해주세요.")
			login_pw.focus()
			login_pw.select()
			return false;
		}
		;

		//패스워드와 패스워드 확인 일치여부 체크
		if (login_pw.value != login_pw2.value) {
			alert("패스워드가 일치하지않습니다")
			login_pw.value = ""
			login_pw2.value = ""
			return false;
		}
		;
		//이메일 입력여부 검사
		if (login_email.value == "") {
			alert("이메일을 입력하세요");
			login_email.focus();
			return false;
		}
		;

		//이메일 형식체크 (@,'.' 가 있아야함) 
		if (((login_email.value.indexOf('@')) <= 0) || (login_email.value.indexOf('.') <= 0)) {
			alert("정상적인 이메일이 아닙니다.")

			login_email.focus();
			return false;
		}
		;
		//이름 입력여부 검사
		if (login_name.value == "") {
			alert("이름을 입력하세요");
			login_name.focus();
			return false;
		}
		;

		//전화번호 입력여부 검사
		if (login_pnum.value == "") {
			alert("전화번호를 입력하세요");
			login_pnum.focus();
			return false;
		}
		;

		//전화번호 길이체크
		if (login_pnum.value.length < 10 || login_pnum.value.length > 11) {
			alert("전화번호를 다시 입력해 주세요.")
			login_pw.focus()
			login_pw.select()
			return false;
		}
		;

		//성별 입력여부 검사
		var oRadio = document.getElementsByName("sex"); // 객체를 배열로 생성
		var sCheck = "N"; // 체크박스 체크유무 체크
		for (i = 0; i < oRadio.length; i++) {
			if (oRadio[i].checked) {
				// 체크가 되어있다면 빠져나간다.
				sCheck = "Y"
				break;
			}
		}
		// 체크 된게 없다면 메세지 박스를 띄운다
		if (sCheck == "N") {
			alert("성별을 선택하세요!")
			return false;
		}

		//주소 입력여부 검사
		if (login_addr.value == "") {
			alert("주소를 입력하세요");
			login_addr.focus();
			return false;
		}
		;

		//상세주소 입력여부 검사
		if (login_daddr.value == "") {
			alert("상세주소를 입력하세요");
			login_daddr.focus();
			return false;
		}
		;

		//프로필사진 입력여부 검사
		if (login_pic.value == "") {
			alert("프로필사진을 입력하세요");
			login_pic.focus();
			return false;
		}
		;
		return true;
	}
</script>
</head>

<body onunload="" class="page-subpage page-item-detail navigation-off-canvas"
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

						<li class="active">회원 가입</li>
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
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
							<header>
								<h1 class="page-title">회원가입</h1>
							</header>
							<hr>
							<form method="post" id="form" name="form"
								action="${pageContext.request.contextPath}/user/join"
								enctype="multipart/form-data" onSubmit="return checkValid();">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
								<div class="form-group" align="center" id="holder">
									<img
										src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
										class="img-circle" width="200" height="200">

								</div>
								<div class="form-group">
									<label for="form-register-full-name">ID</label> <input
										type="text" class="form-control" name="userId" id="userId">
								</div>
								<div class="form-group">
									<span class="form-control" placeholder="ID중복여부" attr="dup">ID중복여부</span>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label for="form-register-email">Email:</label> <input
										type="email" class="form-control" name="email" id="email">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label for="form-register-password">비밀번호:</label> <input
										type="password" class="form-control" name="password"
										id="password">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label for="form-register-confirm-password">비밀번호 확인 :</label> <input
										type="password" class="form-control" name="password-repeat"
										id="password-repeat">
								</div>

								<div class="form-group">
									<label for="form-register-full-name">이름</label> <input
										type="text" class="form-control" name="name" id="name">
								</div>

								<div class="form-group">
									<label for="form-register-full-name">핸드폰 번호</label> <input
										type="text" class="form-control" id="form-register-full-name"
										name="phone" id="phone"> <a href="#"><span
										class="glyphicon glyphicon-phone-alt" style="margin: auto"></span>핸드폰
										인증하기</a>
								</div>
								<br>
								<div class="form-group">
									<input class="form-control" type="text" name="preAddr"
										id="sample5_address" placeholder="주소" readonly="readonly" />
									<input class="form-control" type="text" name="detailAddr"
										id="addr-detail" placeholder="상세주소" />
									<button type="button" onClick="sample5_execDaumPostcode()">
										<span class="glyphicon glyphicon-home" style="margin: auto"></span>주소찾기
									</button>
								</div>
								<div class=" form-group">
									<label for="form-register-full-name">성별</label> <input
										type="radio" name="sex" id="man" value="man">Man <input
										type="radio" id="woman" name="sex" value="woman">Woman
								</div>
								
								<div class="form-group">
									<label for="form-register-full-name">자기소개</label>
									<textarea class="form-control" name="introduce" id="introduce"
									data-length="200" placeholder="200자 이내로 작성하세요."></textarea>
								</div>
								
								<div class="form-group">
									<label for="form-register-full-name">프로필 사진 업로드</label> <input
										class="form-control" type="file" name="selfImgFile"
										id="upload" />
								</div>


								<div class="form-group clearfix">
									<button type="submit" class="btn pull-right btn-default"
										id="account-submit">계정 생성하기</button>
								</div>
								<!-- /.form-group -->
							</form>
							<hr>
						</div>
					</div>
				</div>
			</section>
			<!-- /.block-->
		</div>
		<!-- end Page Content-->
	</div>


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
