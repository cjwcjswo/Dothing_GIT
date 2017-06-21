<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.imgSelect {
	cursor: pointer;
}

.popupLayer {
	position:;
	display: none;
	background-color: #ffffff;
	border: solid 2px #d0d0d0;
	width: 350px;
	height: 150px;
	padding: 10px;
}

.popupLayer div {
	position:;
	top: 5px;
	right: 5px
}
</style>
<script type="text/javascript">

	function closeLayer(obj) {
		$(obj).parent().parent().hide();
	}

	$(function() {

		/* 클릭 클릭시 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
		$('.imgSelect').click(function(e) {
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = $('.popupLayer').width();
			var oHeight = $('.popupLayer').height();

			// 레이어가 나타날 위치를 셋팅한다.
			var divLeft = e.clientX + 10;
			var divTop = e.clientY + 5;

			// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
			if (divLeft + oWidth > sWidth)
				divLeft -= oWidth;
			if (divTop + oHeight > sHeight)
				divTop -= oHeight;

			// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
			if (divLeft < 0)
				divLeft = 0;
			if (divTop < 0)
				divTop = 0;

			$('.popupLayer').css({
				"top" : divTop,
				"left" : divLeft,
				"position" : "absolute"
			}).show();
		});

	});
</script>
</head>

<body>


	<a class="imgSelect">아이디</a>

	<!-- 폼 레이어  -->
	<div class="popupLayer" >
		<div align="right">
			<span onClick="closeLayer(this)" style="cursor: pointer;" title="닫기"><i
				class="fa fa-close"></i></span>
		</div>

		<div class="span3">
			<div class="col-sm-3" style="padding: 0% ;width=50px; height=50px;" >
				<img
					src="${pageContext.request.contextPath}/assets/img/main/shakehands.png"
					class="img-rounded" alt="Cinque Terre" width="50px" height="50px">
			</div>
			<div class="col-sm-9" style="padding: 0%">
				<p>
					<strong>테스터</strong>
				</p>
				<div class="row">
					<strong class="pull-left">총 평점</strong> <i class="fa fa-star"></i><i
						class="fa fa-star"></i><i class="fa fa-star"></i><i
						class="fa fa-star"></i>
				</div>
				<div class="row">
				<p>
					<strong>#태그</strong>
					<strong>#태그</strong>
					<strong>#태그</strong>
					<strong>#태그</strong>
				</p>
				
				</div>
				
				<span class=" badge badge-warning">안전 심부름꾼</span> <span
					class=" badge badge-info">인기 심부름꾼</span>
			</div>
		</div>

	</div>


</body>
</html>