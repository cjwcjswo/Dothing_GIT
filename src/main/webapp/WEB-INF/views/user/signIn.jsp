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

</head>
<body>
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder"></div>
			<form method="post">
				<h2 class="text-center">
					<strong>Create</strong> an account.
				</h2>

				<div class="form-group" align="center">
					<img src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
						class="img-circle" width="150" height="150">

				</div>


				<div class="form-group">
					<input class="form-control" type="id" name="id" placeholder="id">
				</div>


				<div class="form-group">
					<input class="form-control" type="password" name="password"
						placeholder="Password">
				</div>



				<div class="form-group">
					<input class="form-control" type="password" name="password-repeat"
						placeholder="Password (repeat)">
				</div>


				<div class="form-group">
					<input class="form-control" type="email" name="email"
						placeholder="email">
				</div>


				<div class="form-group">
					<input class="form-control" type="name" name="name"
						placeholder="name">
				</div>


				<div class="form-group">

					<input class="form-control" type="text" name="phone"
						placeholder="phone" disabled> <a href="#"><span
						class="glyphicon glyphicon-phone-alt" style="margin: auto"></span>핸드폰
						인증하기</a>

				</div>


				<div class=" form=group">
					<label class="radio-inline"><input type="radio"
						name="optradio">Man</label> <label class="radio-inline"><input
						type="radio" name="optradio">Woman</label>
				</div>
				<br>

				<div class="form-group">
					<input class="form-control" type="text" name="address"
						placeholder="address" disabled /> <input class="form-control"
						type="text" name="detailAddress" placeholder="상세주소" disabled /> <a
						href="#"><span class="glyphicon glyphicon-home"
						style="margin: auto"></span>주소찾기</a>
				</div>

				<div class="form-group">
					<input class="form-control" type="text" name="address"
						placeholder="address" disabled /> 
						<a href="#"><span class="glyphicon glyphicon-picture" style="margin: auto"></span>프로필 사진 올리기</a>
				</div>





				<br>
				<div class="form-group">
					<div class="checkbox">
						<label class="control-label"> <input type="checkbox">I
							agree to the license terms.
						</label>
					</div>
				</div>


				<div class="form-group">
					<button class="btn btn-primary btn-block" type="submit">Sign
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