<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>loginForm.jsp입니다.</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Login-Form-Dark.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

</head>

<body>
	<div class="login-dark">
		<form method="post" action="${pageContext.request.contextPath}/login">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
			<h2 class="sr-only">Login Form</h2>
			<div class="illustration">
				<i class="icon ion-ios-locked-outline"></i>
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="id"
					placeholder="Id">
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="password"
					placeholder="Password">
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="submit">Log
					In</button>
			</div>
			<a href="#" class="forgot">Forgot your email or password?</a>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>



</html>