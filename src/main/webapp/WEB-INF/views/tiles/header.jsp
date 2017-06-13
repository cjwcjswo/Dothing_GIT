<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	function logout() {
		document.getElementById("logoutForm").submit();
	}
</script>

</head>
<body>
	<!-- Navigation-->
	<div class="header">
		<div class="wrapper">
			<div class="brand">
				<a href="index-directory.html"><img
					src="${pageContext.request.contextPath}/assets/img/logo.png"
					alt="logo"></a>
			</div>
			<nav class="navigation-items">
				<div class="wrapper">
					<ul class="main-navigation navigation-top-header"></ul>
					<ul class="user-area">
						<li><a href="sign-in.html">로그인</a></li>
						<li><a href="register.html"><strong>회원가입</strong></a></li>
					</ul>
					<a href="${pageContext.request.contextPath}/errand/register" class="submit-item">
						<div class="content">
							<span>심부름 등록</span>
						</div>
						<div class="icon">
							<i class="fa fa-plus"></i>
						</div>
					</a>
					<div class="toggle-navigation">
						<div class="icon">
							<div class="line"></div>
							<div class="line"></div>
							<div class="line"></div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- end Navigation-->
</body>

</html>
