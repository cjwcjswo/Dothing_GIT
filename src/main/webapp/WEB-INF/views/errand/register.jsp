<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style type="text/css">
.btn-circle.btn-lg {
	width: 50px;
	height: 50px;
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 25px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-sm"
			style="background-color: #B9EEFF; margin-top: 2%; color: white;">
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<h1 class="h2" style="margin-top: -2%; color=blue;">새로운 심부름을 등록하세요~</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class="well">
					<h4 style="line-height: 20%;">
						<i class="fa fa-home fa-1x"
							style="line-height: 6%; color: #339966"></i> 작성자 ID
					</h4>
					<p style="margin-top: 6%; line-height: 35%">작성자 아이디 넣음</p>
					<br /> <br />
					<h4 style="line-height: 20%;">
						<i class="fa fa-envelope fa-1x"
							style="line-height: 6%; color: #339966"></i>작성 일시 :
					</h4>
					<p style="margin-top: 6%; line-height: 35%">시스템 현재 시간 넣음</p>
					<br /> <br />
					<h4 style="line-height: 20%;">
						<i class="fa fa-user fa-1x"
							style="line-height: 6%; color: #339966"></i> 원하는 배달 시간:
					</h4>
					<hr>

					<input type="datetime-local" class="form-control" /> <br />
					<h4 style="line-height: 20%;">
						<i class="fa fa-yelp fa-1x"
							style="line-height: 6%; color: #339966"></i> 심부름 이름 :
					</h4>
					<p style="margin-top: 6%; line-height: 35%">
						<input type="text" class="form-control">
					</p>

					<h4 style="line-height: 20%;">
						<i class="fa fa-home fa-1x"
							style="line-height: 6%; color: #339966"></i> 예상 물건 가격
					</h4>
					<hr>
					<input type="text" class="form-control">

					<h4 style="line-height: 20%;">
						<i class="fa fa-home fa-1x"
							style="line-height: 6%; color: #339966"></i> 원하는 심부름 가격
					</h4>
					<hr>
					<input type="text" class="form-control">
					<hr>

					<h4 style="line-height: 20%;">
						<i class="fa fa-home fa-1x"
							style="line-height: 6%; color: #339966"></i> 심부름 요청 내용
					</h4>

					<div class="form-group">
						<hr>

						<label for="comment">Comment:</label>
						<textarea class="form-control" rows="5" id="comment"></textarea>
					</div>

					<div class="form-group row">
						 <div class="col-sm-4">
							<label for="ex1">해시태그1</label> <input class="form-control"
								id="ex1" type="text" placeholder="#존나빨리">
						</div>
						 <div class="col-sm-4">
							<label for="ex1">해시태그2</label> <input class="form-control"
								id="ex1" type="text" placeholder="#맥도날드">
						</div>
						 <div class="col-sm-4">
							<label for="ex1">해시태그3</label> <input class="form-control"
								id="ex1" type="text" placeholder="#빅맥">
						</div>

					</div>


				</div>
			</div>
			<div class="col-sm-6">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96690.80542089987!2d29.864461132544537!3d40.77109282810726!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14cb4f66644bfb9d%3A0x82690ee7586b7eb9!2zxLB6bWl0LCBLb2NhZWxp!5e0!3m2!1str!2str!4v1480782606579"
					width="565" height="430" frameborder="0" style="border: 0"
					allowfullscreen></iframe>
				<br>


				<div class="form-group">
					<label for="address"><span class="glyphicon glyphicon-home"></span>주소입력</label>

					<input type="text" class="form-control" id="address" disabled><br>

				</div>


				<div class="form-group">
					<label for="address"><span class="glyphicon glyphicon-home"></span>상세주소입력</label>

					<input type="text" class="form-control" id="address" disabled><br>
					<button type="button" class="btn btn-primary btn-sm">주소찾기</button>
				</div>







				<p align="right">
					사진 올리기 <a href="#"><span class="glyphicon glyphicon-picture"></span></a>
				</p>





				<div class="row" 
					style="border: 1px solid red; width: 200px; height: 200px; margin: auto;" ></div>
				<br>

				<div class="row" align="center">
					<button type="button" class="btn btn-info btn-circle btn-lg">
						<i class="glyphicon glyphicon-ok"></i>
					</button>
					<button type="button" class="btn btn-warning btn-circle btn-lg">
						<i class="glyphicon glyphicon-remove"></i>
					</button>
				</div>


			</div>

		</div>
	</div>
	<br>
</body>
</html>