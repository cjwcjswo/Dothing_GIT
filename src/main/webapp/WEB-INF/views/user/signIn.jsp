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
			 $('#form span').text('�ߺ�����');
			 return;
		 }
		 $.ajax({
				url:"${pageContext.request.contextPath}/user/check",
				type:"post",
				data:"userId="+$(this).val()+"&_csrf=${_csrf.token}",
				dataType:"text",
				success:function(result){	
					if(result=="������Դϴ�"){
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
		var login_id = document.getElementById("userId"); //html ���� ������ id���� ����login_id �� ����
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
		
		//���̵� �Է¿��� �˻�
		if (login_id.value == "") { //id�� ���� null�̸� â ���� Ŀ�� �ű�
			alert("���̵� �Է��ϼ���");
			login_id.focus();
			return false;
		};

		//���̵� ��ȿ�� �˻�(����ҹ���, ���ڸ� ���)
		for (var i = 0; i < login_id.value.length; i++) {
			ch = login_id.value.charAt(i)
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')) {
				alert("���̵�� �ҹ���, ���ڸ� �Է°����մϴ�.")
				login_id.focus()
				login_id.select()
				login_id.value = ""
				return false;
			}
		};

		//���̵� ����üũ
		if (login_id.value.length < 3 || login_id.value.length > 12) {
			alert("���̵� 3~12�� ���� �Է����ּ���.")
			login_id.focus()
			login_id.select()
			return false;
		};

		//�н����� �Է¿��� �˻�
		if (login_pw.value == "") {
			alert("��й�ȣ�� �Է��ϼ���");
			login_pw.focus();
			return false;
		};

		//�н����� ����üũ
		if (login_pw.value.length < 4 || login_pw.value.length > 8) {
			alert("�н����带 4~8�� ���� �Է����ּ���.")
			login_pw.focus()
			login_pw.select()
			return false;
		};

		//�н������ �н����� Ȯ�� ��ġ���� üũ
		if (login_pw.value != login_pw2.value) {
			alert("�н����尡 ��ġ�����ʽ��ϴ�")
			login_pw.value = ""
			login_pw2.value = ""
			return false;
		};
		//�̸��� �Է¿��� �˻�
		if (login_email.value == "") {
			alert("�̸����� �Է��ϼ���");
			login_email.focus();
			return false;
		};

		//�̸��� ����üũ (@,'.' �� �־ƾ���) 
		if (((login_email.value.indexOf('@')) <= 0) || (login_email.value.indexOf('.') <= 0)) {
			alert("�������� �̸����� �ƴմϴ�.")
			
			login_email.focus();
			return false;
		};
		//�̸� �Է¿��� �˻�
		if (login_name.value == "") {
			alert("�̸��� �Է��ϼ���");
			login_name.focus();
			return false;
		};

		//��ȭ��ȣ �Է¿��� �˻�
		if (login_pnum.value == "") {
			alert("��ȭ��ȣ�� �Է��ϼ���");
			login_pnum.focus();
			return false;
		};

		//��ȭ��ȣ ����üũ
		if (login_pnum.value.length < 10 || login_pnum.value.length > 11) {
			alert("��ȭ��ȣ�� �ٽ� �Է��� �ּ���.")
			login_pw.focus()
			login_pw.select()
			return false;
		};

		//���� �Է¿��� �˻�
		  var oRadio = document.getElementsByName("sex");  // ��ü�� �迭�� ����
		  var sCheck = "N";      // üũ�ڽ� üũ���� üũ
		  for (i = 0; i < oRadio.length; i++) {
		       if (oRadio[i].checked) {
		           // üũ�� �Ǿ��ִٸ� ����������.
		           sCheck = "Y"           
		           break;
		       }
		 }
		// üũ �Ȱ� ���ٸ� �޼��� �ڽ��� ����
		 if (sCheck == "N") {
		     alert("������ �����ϼ���!")
		     return false;
		}
		
		//�ּ� �Է¿��� �˻�
		if (login_addr.value == "") {
			alert("�ּҸ� �Է��ϼ���");
			login_addr.focus();
			return false;
		};

		//���ּ� �Է¿��� �˻�
		if (login_daddr.value == "") {
			alert("���ּҸ� �Է��ϼ���");
			login_daddr.focus();
			return false;
		};

		//�����ʻ��� �Է¿��� �˻�
		if (login_pic.value == "") {
			alert("�����ʻ����� �Է��ϼ���");
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
					<span class="form-control" placeholder="ID�ߺ�����">ID�ߺ�����</span>
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
						class="glyphicon glyphicon-phone-alt" style="margin: auto"></span>�ڵ���
						�����ϱ�</a>

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
						name="addr" id="addr-detail" placeholder="���ּ�" /> <a href="#"><span
						class="glyphicon glyphicon-home" style="margin: auto"></span>�ּ�ã��</a>
				</div>

				<div class="form-group">
					<a href="#"><span class="glyphicon glyphicon-picture"
						style="margin: auto"></span>������ ���� �ø���</a> <input class="form-control"
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