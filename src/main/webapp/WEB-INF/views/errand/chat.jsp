<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href='https://fonts.googleapis.com/css?family=Lato:400,700'
	rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat/normalize.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat/style.css">

</head>
<body>
	<button type="button" class="button js-trigger">Chat (45)</button>


	<nav class="chat">
	<h2 class="chat__users">Users Online: 45</h2>
	<ul class="chat__wrapper">
		<li class="chat__human"><img class="chat__avatar"
			src="https://robohash.org/joe" alt="" /> <span class="chat__name">Joe
				Richardson</span></li>

		<li class="chat__human"><img class="chat__avatar"
			src="https://robohash.org/nah" alt="" /> <span class="chat__name">Bill
				Gates</span></li>

		<li class="chat__human"><img class="chat__avatar"
			src="https://robohash.org/ok" alt="" /> <span class="chat__name">Steve
				Jobs</span></li>

		<li class="chat__human"><img class="chat__avatar"
			src="https://robohash.org/hi" alt="" /> <span class="chat__name">Mark
				Zuckerberg</span></li>

		<li class="chat__human"><img class="chat__avatar"
			src="https://robohash.org/bruh" alt="" /> <span class="chat__name">Denzel
				Washington</span></li>
	</ul>
	</nav>

	<div class="conversation">
		<div class="conversation__header">
			Denzel Washington <span class="close-msg">&times;</span>
		</div>
		<ul class="conversation__wrap">
			<li class="conversation__msg cf"><span>Hey!</span></li>

			<li class="conversation__msg cf"><span class="right">Yo!</span>
			</li>

			<li class="conversation__msg cf"><span>How Goes it?</span></li>

			<li class="conversation__msg cf"><span class="right">Bruh.</span>
			</li>
		</ul>

		<input class="input" type="text" placeholder="Enter Message" />
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/chat/chat.js"></script>




</body>
</html>