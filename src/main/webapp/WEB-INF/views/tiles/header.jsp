<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Do Thing</title>

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
				<a href="${pageContext.request.contextPath}/"><img
					src="${pageContext.request.contextPath}/assets/img/logo.png"
					alt="logo"></a>
			</div>
			<nav class="navigation-items">
				<div class="wrapper">
					<!-- <ul class="main-navigation navigation-top-header"></ul> -->


					<ul class="user-area">
						<security:authorize access="isAnonymous()">
							<li><a
								href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
							<li><a href="${pageContext.request.contextPath}/user/signIn"><strong>회원가입</strong></a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li><a href="${pageContext.request.contextPath}/user/myPage"><security:authentication
										property="principal.name" /> 님</a> 환영합니다.</li>
							<li>보유 포인트: <fmt:formatNumber pattern="#,###">
									<security:authentication
										property="principal.point.currentPoint" />
								</fmt:formatNumber></li>
							<li><a href="javascript:logout();">로그아웃</a></li>
							<li><a
								href="${pageContext.request.contextPath}/errand/errand">심부름
									목록</a></li>
							<a href="${pageContext.request.contextPath}/errand/register"
								class="submit-item">
									<i class="fa fa-plus"></i>
							</a>
							<div class="toggle-navigation">
								<div class="icon">
									<div class="line"></div>
									<div class="line"></div>
									<div class="line"></div>
								</div>
							</div>
						</security:authorize>



					</ul>


				</div>
			</nav>
		</div>
	</div>
	<!-- end Navigation-->
	<form id="logoutForm"
		action="${pageContext.request.contextPath}/user/logout" method="post"
		style="display: none">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>

</html>
