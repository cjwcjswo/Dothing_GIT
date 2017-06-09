<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Navigation-with-Button1.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.js"> --%>


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
					<li role="presentation"><a href="${pageContext.request.contextPath}/errand/errand">심부름 하기</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath}/errand/register">심부름 신청하기</a></li>
					
					

				</ul> 
				<p class="navbar-text navbar-right actions">
					<a href="${pageContext.request.contextPath}/user/loginForm"
						class="navbar-link login">로그인</a> <a
						class="btn btn-default action-button" role="button" href="${pageContext.request.contextPath}/user/signIn">Sign
						Up</a>
				</p>
			</div>
		</div>
		</nav>
	</div>
	<div style="height:10px"></div>
</body>

</html>
