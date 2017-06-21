<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body >

<div class="container">

<style>
	#product{
		height:200px; 
	}
	
</style>

<a href="${pageContext.request.contextPath}/crawl?pageName=ediya">이디야</a>
	<c:forEach items="${crawlData}" var="item">
		<div id="product" class="col-xs-3 col-md-3 col-lg-3">
		<a href="${item.link}">
			<img src="${item.img}"/>
			<div align="center">${item.name}</div>
			<div align="center"><button class="btn btn-primary btn-sm" id="order">주문하기</button></div>
		</a>
		</div>
	</c:forEach>
</div>
</body>
</html>