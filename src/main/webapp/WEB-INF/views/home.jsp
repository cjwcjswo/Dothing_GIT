<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  태호
</h1>

<P>  The time on the server is ${serverTime}. </P>



<a href="user/loginForm">로그인폼</a>

<a href="errand/mapTest">맵테스트</a>


<a href="user/signIn">회원가입폼</a>

<a href="errand/chat">채팅</a>

<br>

<a href="errand/errands">심부름목록</a>
</body>
</html>
