<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body onload="document.f.submit()">
	<form name="f" id="f"
		action="login" method="post">
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="password" value="${password}"> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>