<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	$(function(){
		
		 $("#search").click(function(){	
			 alert(89);
			 location.href="${pageCotnext.request.contextPath}/controller/crawl?pageName=ediya&productName="+$("#inputProductName").val();
		});
		 
		
		$("button[name=order]").click(function(){
			var img = $(this).parent().parent().children().children().first().attr('src');//��ǰ �̹���
			var name = $(this).parent().parent().children().children().first().next().text();//��ǰ �̸�
			location.href="${pageContext.request.contextPath}/errand/register?img="+img+"&name="+name;
		});
	});
</script>
</head>
<body >

<div class="container">

<style>
	#product{
		height:200px; 
	}
	
</style>

<%-- <a href="${pageContext.request.contextPath}/crawl?pageName=ediya">�̵��</a> --%>

	�̵�� ��ǰ �˻�
<!-- <div class="form-group">
		<input type="text" class="form-control" id="inputProductName"> <button></button>
</div> -->
<div class="input-group">
	<input id="inputProductName" type="text" placeholder="��ǰ�� �˻�..." /> 
		<button id="search">�˻�</button>
</div><p>

	<div id="productList">
		  <c:forEach items="${crawlData}" var="item">
			<div id="product" class="col-xs-3 col-md-3 col-lg-3">
			<a href="${item.link}">
				<img src="${item.img}"/>
				<div style="text-align: center;" id="${state.count}">${item.name}</div>
			</a>
				<div>
					<button class="btn btn-primary btn-sm" name="order">�ֹ��ϱ�</button>
				</div>
			</div>
		</c:forEach> 
	</div>
</div>


	

</body>
</html>