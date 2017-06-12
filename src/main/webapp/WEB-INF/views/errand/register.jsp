<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

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

.title {
	font-weight: bold;
	display: block;
}

.bAddr {
	padding: 5px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}
</style>
<script>
	function checkValid() {
		var form = document.f;
		if (form.title.value.trim() == "") {
			alert("제목을 입력하세요");
			return false;
		}
		if (form.content.value.trim() == "") {
			alert("내용을 입력하세요");
			return false;
		}
		if (form.productPrice.value.trim() == "") {
			alert("물품 가격을 입력하세요.")
			return false;
		}
		if (form.errandsPrice.value.trim() == "") {
			alert("심부름 가격을 입력하세요.")
			return false;
		}
		if(form.endTime.value.trim() == ""){
			alert("시간을 입력하세요");
			return false;
		}
		if (form.productPrice.value < 0 || form.errandsPrice.value < 0) {
			alert("가격이 올바르지 않습니다");
			return false;
		}
		if (form.preAddress.value.trim() == "") {
			alert("주소를 입력하세요");
			return false;
		}
		if (form.detailAddress.value.trim() == "") {
			alert("상세 주소를 입력하세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-sm"
			style="background-color: #B9EEFF; margin-top: 2%; color: white;">
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<h1 class="h2" style="margin-top: -2%;">새로운 심부름을 등록하세요~</h1>
				</div>
			</div>
		</div>
	</div>
	<form name="f" action="insert" method="post"
		enctype="multipart/form-data" onSubmit="return checkValid();">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="well">
						<br> <br>
						<h4 style="line-height: 20%;">
							<i class="fa fa-home fa-1x"
								style="line-height: 6%; color: #339966"></i> 작성자 ID
						</h4>
						<p style="margin-top: 6%; line-height: 35%">
							<input type="text" class="form-control" readonly="readonly"
								name="requestUser.userId" value="<security:authentication property="principal.userId"/>">
						</p>
						<br />
						<h4 style="line-height: 20%;">
							<i class="fa fa-user fa-1x"
								style="line-height: 6%; color: #339966"></i> 원하는 배달 시간:
						</h4>
						<hr>

						<input type="datetime-local" class="form-control" name="endTime" />
						<br />
						<h4 style="line-height: 20%;">
							<i class="fa fa-yelp fa-1x"
								style="line-height: 6%; color: #339966"></i> 심부름 제목
						</h4>
						<p style="margin-top: 6%; line-height: 35%">
							<input type="text" class="form-control" name="title">
						</p>
						<br>
						<h4 style="line-height: 20%;">
							<i class="fa fa-home fa-1x"
								style="line-height: 6%; color: #339966"></i> 예상 물건 가격
						</h4>
						<br> <input type="number" class="form-control"
							name="productPrice" min="0">

						<h4 style="line-height: 20%;">
							<i class="fa fa-home fa-1x"
								style="line-height: 6%; color: #339966"></i> 원하는 심부름 가격
						</h4>
						<br> <input type="number" class="form-control"
							name="errandsPrice" min="0">
						<hr>

						<h4 style="line-height: 20%;">
							<i class="fa fa-home fa-1x"
								style="line-height: 6%; color: #339966"></i> 심부름 요청 내용
						</h4>

						<div class="form-group">
							<hr>

							<textarea class="form-control" rows="5" id="comment"
								name="content"></textarea>
						</div>

				


					</div>
				</div>
				<div class="col-sm-6">
					<div id="map" style="width: 565px; height: 430px; z-index:0"></div>
					<br>


					<div class="form-group">
						<label for="address"><span
							class="glyphicon glyphicon-home"></span>주소입력</label> <input type="text"
							class="form-control" id="sample5_address" name="preAddress"
							readonly="readonly"><br>

					</div>


					<div class="form-group">
						<label for="address"><span
							class="glyphicon glyphicon-home"></span>상세주소입력</label> <input type="text"
							class="form-control" name="detailAddress" id="detailAddress"><br>
						<button type="button" class="btn btn-primary btn-sm"
							onclick="sample5_execDaumPostcode()">주소찾기</button>
						<input type="hidden" name="errandsPos.latitude" id="latitude">
						<input type="hidden" name="errandsPos.longitude" id="longitude">
					</div>
					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script type="text/javascript"
						src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
					<script
						src="${pageContext.request.contextPath}/resources/js/errandsAddress.js"></script>


					<p align="right">
						사진 올리기 <a><span class="glyphicon glyphicon-picture"></span></a>
					</p>





					<div class="row"
						style="border: 3px black inset; width: 200px; height: 200px; margin: auto; box-sizing: content-box;"
						id="holder"></div>
					<br> <input type="file" class="form-control" value="찾기"
						id="upload" name="errandsPhotoFile">
					<div class="row" align="center">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit" class="btn btn-info btn-circle btn-lg">
							<i class="glyphicon glyphicon-ok"></i>
						</button>
						<button type="reset" class="btn btn-warning btn-circle btn-lg">
							<i class="glyphicon glyphicon-remove"></i>
						</button>
					</div>
					<script>
						var upload = document.getElementById('upload'),
							holder = document.getElementById('holder');
					
					
						upload.onchange = function(e) {
							e.preventDefault();
					
							var file = upload.files[0],
								reader = new FileReader();
							reader.onload = function(event) {
								var img = new Image();
								img.src = event.target.result;
								img.width = 200;
								img.height = 200;
								holder.innerHTML = '';
								holder.appendChild(img);
							};
							reader.readAsDataURL(file);
					
							return false;
						};
					</script>

				</div>

			</div>
		</div>

	</form>
	<br>
</body>
</html>