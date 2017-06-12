<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/Navigation-with-Button1.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.js"> --%>

<script>
	function logout() {
		document.getElementById("logoutForm").submit();
	}
</script>

</head>
<body>
	<div>
		<nav class="navbar navbar-default navigation-clean-button" id="navbar">
		<div class="container">
			<div class="navbar-header">
				<a href="#" class="navbar-brand navbar-link">Do Thing</a>
				<button data-toggle="collapse" data-target="#navcol-1"
					class="navbar-toggle collapsed">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="nav navbar-nav">

					<li role="presentation"><a
						href="${pageContext.request.contextPath}/board/inquiryBoardList">1:1
							�Խ���</a></li>


					<li role="presentation"><a
						href="${pageContext.request.contextPath}/errand/errand">�ɺθ� �ϱ�</a></li>
					<li role="presentation"><a
						href="${pageContext.request.contextPath}/errand/register">�ɺθ�
							��û�ϱ�</a></li>
					<!-- ä�� �׽�Ʈ -->
					<li role="presentation"><a
						href="${pageContext.request.contextPath}/websocket">ä���׽�Ʈ</a></li>

				</ul>


				<p class="navbar-text navbar-right actions">
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.name" /> �� ȯ���մϴ�.
					
					<a class="btn btn-default action-button" role="button"
							href="javascript:logout();">�α׾ƿ�</a>

						<!-- �α׾ƿ��� ��������ť��Ƽ 4 ���ʹ� �α׾ƿ��� POST ������� �̵��ϸ� /logout url�� ��û�Ѵ�. (���� �������� ������)
				�׸��� _csrf �� ��û�Ķ���ͷ� ������ �Ѵ�. -->
						<form id="logoutForm"
							action="${pageContext.request.contextPath}/member/logout"
							method="post" style="display: none">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</security:authorize>

					<!-- ���� �ȵ����� -->
					<security:authorize access="!isAuthenticated()">
						<a href="${pageContext.request.contextPath}/user/loginForm"
							class="navbar-link login">�α���</a>
						<a class="btn btn-default action-button" role="button"
							href="${pageContext.request.contextPath}/user/signIn">Sign Up</a>
					</security:authorize>
				</p>
			</div>
		</div>
		</nav>
	</div>
	<div style="height: 10px"></div>
</body>

</html>
