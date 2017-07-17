<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>회원가입</title>

<script>
	function checkValid(){
		var f = document.form;
		if(f.userId.value.trim() == ""){
			swal("입력 오류!", "이메일을 입력해주세요.", "error");
			f.userId.focus();
			return false;
		}
		if(f.password.value.trim() == ""){
			swal("입력 오류!", "비밀번호를 입력해주세요.", "error");
			f.password.focus();
			return false;
		}
		if(f.password_repeat.value.trim() == ""){
			swal("입력 오류!", "비밀번호 확인란을 입력해주세요.", "error");
			f.password_repeat.focus();
			return false;
		}
		if(f.password.value.trim() != f.password_repeat.value.trim()){
			swal("입력 오류!", "비밀번호가 같지 않습니다", "error");
			f.password_repeat.focus();
			return false;
		}
		if(f.name.value.trim() == ""){
			swal("입력 오류!", "이름을 입력해주세요.", "error");
			f.name.focus();
			return false;
		}
		if(f.name.value.trim() == ""){
			swal("입력 오류!", "이름을 입력해주세요.", "error");
			f.name.focus();
			return false;
		}
		if(f.preAddr.value.trim() == ""){
			swal("입력 오류!", "주소를 입력해주세요.", "error");
			f.preAddr.focus();
			return false;
		}
		if(f.detailAddr.value.trim() == ""){
			swal("입력 오류!", "상세주소를 입력해주세요.", "error");
			f.detailAddr.focus();
			return false;
		}
		if(f.sex.value.trim() == ""){
			swal("입력 오류!", "성별을 입력해주세요.", "error");
			return false;
		}
		if(f.introduce.value.trim() == ""){
			swal("입력 오류!", "자기소개를 입력해주세요.", "error");
			f.introduce.focus();
			return false;
		}
		if(f.selfImgFile.value.trim() == ""){
			swal("입력 오류!", "프로필 사진을 넣어주세요.", "error");
			f.selfImgFile.focus();
			return false;
		}
		if(f.password.value.trim().length < 4){
			swal("입력 오류!", "비밀번호를 4자 이상으로 만들어주세요.", "error");
			f.password.focus();
			return false;
		}
		var ext = f.selfImgFile.value.split(".");
		ext = ext[1].toLowerCase();
		if(!(ext == "jpg" || ext == "jpeg" || ext == "png" || ext == "gif")){
			swal("입력 오류!", "파일의 확장자는 jpg, jpeg, png, gif만 가능합니다.", "error");
			return false;
		}
		if($("span[attr='dup']").text() != '사용가능합니다.'){
			alert($("span[attr='dup']").text());
			swal("입력 오류!", "아이디가 중복입니다!", "error");
			return false;
		}
		swal("확인해주세요", "인증메일이 발송됬습니다","success");
		return true;
	}
</script>
</head>

<body onunload=""
	class="page-subpage page-item-detail navigation-off-canvas"
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
									<label for="form-register-full-name">이메일</label> <input
										type="email" class="form-control" name="userId" id="userId">
								</div>
								<div class="form-group">
									<span class="form-control" placeholder="이메일중복여부" attr="dup">이메일중복여부</span>
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
										type="password" class="form-control" name="password_repeat"
										id="password-repeat">
								</div>

								<div class="form-group">
									<label for="form-register-full-name">이름</label> <input
										type="text" class="form-control" name="name" id="name">
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
								<input class="form-control" type="hidden" name="latitude"
									id="latitude" /> <input class="form-control" type="hidden"
									name="longitude" id="longitude" />
								<%-- 								<div class="center">

									<figure class="note">
										<a
											href="${pageContext.request.contextPath}/etc/terms-conditions"
											class="link">약관</a>에 동의하고 가입합니다.

									</figure>


								</div> --%>

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
	
		
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addrInput.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
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
					console.log(err);
				}
			})
		});
	})
</script>
	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>
