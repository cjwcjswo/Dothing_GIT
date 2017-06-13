<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/signUp.css">

<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>

<script type="text/javascript">

$(function(){
	
	$("#userId").keyup(function(){
		 if($(this).val()==''){
			 $('#form span').text('중복여부');
			 return;
		 }
		 $.ajax({
				url:"${pageContext.request.contextPath}/user/check",
				type:"post",
				data:"userId="+$(this).val()+"&_csrf=${_csrf.token}",
				dataType:"text",
				success:function(result){	
					if(result=="사용중입니다"){
						$('#btn').attr("disabled",true);
					}else{
						$('#btn').attr("disabled",false);
					}
					$('#form span').text(result);
				},
				error:function(err){
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
		var login_addr = document.getElementById("addr");
		var login_daddr = document.getElementById("addr-detail");
		var login_pic = document.getElementById("upload");
		
		//아이디 입력여부 검사
		if (login_id.value == "") { //id의 값이 null이면 창 띄우고 커서 옮김
			alert("아이디를 입력하세요");
			login_id.focus();
			return false;
		};

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
		};

		//아이디 길이체크
		if (login_id.value.length < 3 || login_id.value.length > 12) {
			alert("아이디를 3~12자 까지 입력해주세요.")
			login_id.focus()
			login_id.select()
			return false;
		};

		//패스워드 입력여부 검사
		if (login_pw.value == "") {
			alert("비밀번호를 입력하세요");
			login_pw.focus();
			return false;
		};

		//패스워드 길이체크
		if (login_pw.value.length < 4 || login_pw.value.length > 8) {
			alert("패스워드를 4~8자 까지 입력해주세요.")
			login_pw.focus()
			login_pw.select()
			return false;
		};

		//패스워드와 패스워드 확인 일치여부 체크
		if (login_pw.value != login_pw2.value) {
			alert("패스워드가 일치하지않습니다")
			login_pw.value = ""
			login_pw2.value = ""
			return false;
		};
		//이메일 입력여부 검사
		if (login_email.value == "") {
			alert("이메일을 입력하세요");
			login_email.focus();
			return false;
		};

		//이메일 형식체크 (@,'.' 가 있아야함) 
		if (((login_email.value.indexOf('@')) <= 0) || (login_email.value.indexOf('.') <= 0)) {
			alert("정상적인 이메일이 아닙니다.")
			
			login_email.focus();
			return false;
		};
		//이름 입력여부 검사
		if (login_name.value == "") {
			alert("이름을 입력하세요");
			login_name.focus();
			return false;
		};

		//전화번호 입력여부 검사
		if (login_pnum.value == "") {
			alert("전화번호를 입력하세요");
			login_pnum.focus();
			return false;
		};

		//전화번호 길이체크
		if (login_pnum.value.length < 10 || login_pnum.value.length > 11) {
			alert("전화번호를 다시 입력해 주세요.")
			login_pw.focus()
			login_pw.select()
			return false;
		};

		//성별 입력여부 검사
		  var oRadio = document.getElementsByName("sex");  // 객체를 배열로 생성
		  var sCheck = "N";      // 체크박스 체크유무 체크
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
		};

		//상세주소 입력여부 검사
		if (login_daddr.value == "") {
			alert("상세주소를 입력하세요");
			login_daddr.focus();
			return false;
		};

		//프로필사진 입력여부 검사
		if (login_pic.value == "") {
			alert("프로필사진을 입력하세요");
			login_pic.focus();
			return false;
		};
		return true;
	}
	

</script>

</head>
<body>
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder"></div>
			<form method="post" id="form"
				action="${pageContext.request.contextPath}/user/join"
				enctype="multipart/form-data" onSubmit="return checkValid();">

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">

				<h2 class="text-center">
					<strong>Create</strong> an account.
				</h2>

				<div class="form-group" align="center" id="holder">
					<img
						src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
						class="img-circle" width="200" height="200">

				</div>

				<div class="form-group">
					<input class="form-control" type="text" name="userId" id="userId"
						placeholder="Id">
				</div>
				
				<div class="form-group">
					<span class="form-control" placeholder="ID중복여부">ID중복여부</span>
				</div>

				<div class="form-group">
					<input class="form-control" type="password" name="password" id="password"
						placeholder="Password">
				</div>

				<div class="form-group">
					<input class="form-control" type="password" name="password-repeat" id="password-repeat"
						placeholder="Password (repeat)">
				</div>

				<div class="form-group">
					<input class="form-control" type="text" name="email" id="email"
						placeholder="email">
				</div>

				<div class="form-group">
					<input class="form-control" type="text" name="name" id="name"
						placeholder="name">
				</div>

				<div class="form-group">

					<input class="form-control" type="text" name="phone" id="phone"
						placeholder="phone"> <a href="#"><span
						class="glyphicon glyphicon-phone-alt" style="margin: auto"></span>핸드폰
						인증하기</a>

				</div>


				<div class=" form=group">
					<label class="radio-inline"> <input type="radio" name="sex" id="man"
						value="man">Man
					</label> <label class="radio-inline"> <input type="radio" id="woman"
						name="sex" value="woman">Woman
					</label>
				</div>
				<br>

				<div class="form-group">
					<input class="form-control" type="text" name="address" id="addr"
						placeholder="address" /> <input class="form-control" type="text"
						name="addr" id="addr-detail" placeholder="상세주소" /> <a href="#"><span
						class="glyphicon glyphicon-home" style="margin: auto"></span>주소찾기</a>
				</div>

				<div class="form-group">
					<a href="#"><span class="glyphicon glyphicon-picture"
						style="margin: auto"></span>프로필 사진 올리기</a> <input class="form-control"
						type="file" name="selfImgFile" placeholder="picture" id="upload" />
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




				<br>
				<div class="form-group">
					<div class="checkbox">
						<label class="control-label"> <input type="checkbox">I
							agree to the license terms.
						</label>
					</div>
				</div>


				<div class="form-group">
					<button class="btn btn-primary btn-block" type="submit" id="btn">Sign
						Up</button>
				</div>
				<a href="#" class="already">You already have an account? Login
					here.</a>
			</form>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>