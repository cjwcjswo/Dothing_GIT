<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/summernote.css"
	rel="stylesheet">
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-select.min.css"
	type="text/css">


<title>심부름 등록</title>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<script
	src="${pageContext.request.contextPath}/resources/js/summernote.js"></script>
<script>
	var hashList = ${hashList};
	$(document).ready(function() {
		$('#summernote').summernote({
			toolbar: false,
			height: 300,
	        hint: {
	            match: /\B\#(\S*)$/,
	            mentions: hashList,
	            search: function (keyword, callback) {
	                callback($.grep(this.mentions, function (item) {
	                  return item.indexOf(keyword) == 0;
	                }));
	            },
	            content: function (item) {
	                return '#' + item;
	            }
	        }
		});
	});
</script>
<script>
	var ct = "";
	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();

		if (n.length < digits) {
			for (var i = 0; i < digits - n.length; i++)
				zero += '0';
		}
		return zero + n;
	}
	function currentTime() {
		var d = new Date();
		ct = d.getFullYear() + "-" + leadingZeros((d.getMonth() + 1), 2) + "-"
				+ leadingZeros(d.getDate(), 2) + "T"
				+ leadingZeros(d.getHours(), 2) + ":"
				+ leadingZeros(d.getMinutes(), 2);
	}
	function checkValid() {
		var form = document.f;
		form.content.value = $("#summernote").summernote('code');
		if (form.title.value.trim() == "") {
			swal("입력 오류", "제목을 입력하세요", "error");
			form.title.focus();
			return false;
		}
		if (form.content.value.trim() == "") {
			swal("입력 오류", "내용을 입력하세요", "error");
			form.content.focus();
			return false;
		}
		if (form.productPrice.value.trim() == "") {
			swal("입력 오류", "물품 가격을 입력하세요", "error");
			form.productPrice.focus();
			return false;
		}
		if (form.errandsPrice.value.trim() == "") {
			swal("입력 오류", "심부름 가격을 입력하세요", "error");
			form.errandsPrice.focus();
			return false;
		}
		if (form.endTime.value.trim() == "") {
			swal("입력 오류", "시간을 입력하세요", "error");
			form.endTime.focus();
			return false;
		}
		currentTime();
		if (form.endTime.value < ct) {
			swal("입력 오류", "시간이 현재시간보다 작습니다", "error");
			form.endTime.focus();
			return false;
		}

		if (form.productPrice.value < 0 || form.errandsPrice.value < 0) {
			swal("입력 오류", "가격이 올바르지 않습니다", "error");
			return false;
		}
		if (form.preAddress.value.trim() == "") {
			swal("입력 오류", "주소를 입력하세요", "error");
			form.preAddress.focus();
			return false;
		}
		if (form.detailAddress.value.trim() == "") {
			swal("입력 오류", "상세주소를 입력하세요", "error");
			form.detailAddress.focus();
			return false;
		}
		if (form.errandsPhotoFile.value != "") {
			if (form.errandsPhotoFile.value > 15) {
				swal("입력 오류", "파일 이름은 15자이내로 하세요", "error");
				return false;
			}
			var ext = (form.errandsPhotoFile.value).split(".")[1];

			if (!(ext == "jpg" || ext == "jpeg" || ext == "gif" || ext == "png")) {
				swal("입력 오류", "확장자가 jpg, jpeg, gif, png인 파일만 업로드 할 수 있습니다", "error");
				return false;
			}
		}
		return true;
	}
</script>

</head>

<body onunload="" class="page-subpage page-submit navigation-off-canvas"
	id="page-top">
	<!-- 알림창 -->
	<div class='error' style='display: none'>새로운 심부름이 등록되었습니다.</div>

	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>메뉴</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->

		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">

					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li><a
							href="${pageContext.request.contextPath}/errand/errand">심부름
								목록</a></li>
						<li class="active">심부름 등록하기</li>
					</ol>
					<!-- /.breadcrumb-->
				</div>
				<!-- /.container-->
			</div>
			<!-- /.breadcrumb-wrapper-->
		</section>
		<!--end Sub Header-->

		<!--Page Content-->
		<div id="page-content">
			<section class="container">
				<div class="row">
					<!--Content-->
					<div class="col-md-9">
						<header>
							<h1 class="page-title">새로운 심부름 등록하기</h1>
						</header>
						<form id="f" name="f" method="post" action="insert"
							onsubmit="return checkValid();" enctype="multipart/form-data">


							<input type="hidden" class="form-control"
								name="requestUser.userId"
								value="<security:authentication property="principal.userId"/>"
								readonly="readonly">

							<section>
								<div class="form-group large">
									<label for="title">심부름 제목</label> <input type="text"
										class="form-control" id="title" name="title">
								</div>
							</section>

							<!--/#address-contact-->
							<section>
								<h3>Map</h3>
								<div id="map" class="map-submit"></div>
							</section>
							<input type="hidden" name="errandsPos.latitude" id="latitude">
							<input type="hidden" name="errandsPos.longitude" id="longitude">
							<section>

								<div class="form-group large">
									<label for="title">심부름 받을 위치</label> <input type="text"
										class="form-control" id="sample5_address" name="preAddress"
										readonly="readonly">
								</div>
								<div class="form-group large">
									<label for="title">상세 주소</label> <input type="text"
										class="form-control" name="detailAddress" id="detailAddress">
								</div>
								<div class="col-md-3">
									<div class="menu-icon">
										<button type="button" onclick="sample5_execDaumPostcode()">
											<i class="fa fa-map-marker"></i>주소찾기
										</button>

									</div>
								</div>

							</section>
							<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
							<script type="text/javascript"
								src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>
							<script
								src="${pageContext.request.contextPath}/resources/js/errandsAddress.js"></script>
							<br>
							<section>
								<div class="form-group large">
									<label for="title">상세설명</label>
									<div id="summernote"></div>
									<input type="hidden"
										id="comment" name="content"></input>
								</div>
							</section>
							<!--Menu-->
							<section>
								<div class="form-group large">
									<h3>물품 가격 및 심부름 삯</h3>
									<div class="row">
										<div class="col-md-3">
											<!-- Nav tabs -->
											<ul class="nav nav-pills nav-stacked">
												<li class="active"><a href="#tab-menu"
													data-toggle="tab">심부름 값 결정해보기</a></li>


											</ul>
										</div>
										<div class="col-md-9">
											<!-- Tab panes -->
											<div class="tab-content menu min-height-160">
												<div class="tab-pane fade in active" id="tab-menu">
													<article>
														<div class="row">
															<div class="col-md-1">
																<div class="menu-icon">
																	<i class="fa fa-money"></i><span>1</span>
																</div>
															</div>
															<div class="col-md-11">
																<div class="row">
																	<div class="col-md-10">
																		<div class="form-group">
																			<input type="number" class="form-control"
																				name="errandsPrice" min="0"
																				placeholder="예)심부름 삯: (3,500) 괄호 부분만 작성해주세요">
																		</div>
																	</div>
																	<!-- /.col-md-10-->


																</div>
																<!-- /.row-->
																<div class="form-group">
																	<input type="number" class="form-control"
																		name="productPrice" min="0"
																		placeholder="예) 물품 가격 : (5,500원) 괄호 부분만 작성해주세요">
																</div>

															</div>
															<!--/.col-md-11-->
														</div>
														<!--/.row-->
													</article>
												</div>
											</div>
											<!--/#tab-menu-->




										</div>
										<!--end Tab panes-->
									</div>
									<!--/.col-md-9-->
								</div>
								<!--/.row-->
							</section>
							<!--end Menu-->
							<!--Gallery-->
							<table style="width: 100%;">
								<tr>
									<td>
										<section>
											<h3>사진</h3>
											<div id="holder"
												style="width: 300px; height: 300px; border: 3px dotted gray; box-sizing: content-box;">

											</div>
											<input name="errandsPhotoFile" type="file" id="upload">
											(※확장자가 jpg, jpeg, png, gif인 파일만 업로드 할 수 있습니다.)
										</section>
									</td>
									<!--end Gallery-->
									<!--Opening Hours-->
									<td><section>
											<h3>원하는 심부름 시간</h3>
											<div class="opening-hours">
												<div class="table-responsive">
													<table class="table">
														<tbody>
															<tr class="day">
																<td class="day-name" style="width: 20%;">마감일</td>
																<td><input type="datetime-local" name="endTime"></td>

															</tr>

														</tbody>
													</table>
												</div>
											</div>
										</section></td>
								</tr>
							</table>
							<!--end Opening Hours-->
							<hr>
							<section>
								<!-- 							<figure class="pull-left margin-top-15">
									<p>
										<a href="terms-conditions.html" class="link">약관에 동의</a> 하시면
										“제출하기 및 결제”옆의 제출하기 버튼을 클릭해주세요
									</p>
								</figure> -->
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group">
									<button type="submit" class="btn btn-default pull-right"
										id="submit">제출하기</button>
								</div>
								<!-- /.form-group -->
							</section>
						</form>
						<!--/#form-submit-->
					</div>

				</div>
			</section>
		</div>
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/icheck.min.js"></script>
	<script>
		var upload = document.getElementById('upload'), holder = document
				.getElementById('holder');

		upload.onchange = function(e) {
			e.preventDefault();

			var file = upload.files[0], reader = new FileReader();
			reader.onload = function(event) {
				var img = new Image();
				img.src = event.target.result;
				img.width = 300;
				img.height = 300;
				holder.innerHTML = '';
				holder.appendChild(img);
			};
			reader.readAsDataURL(file);

			return false;
		};
	</script>

</body>
</html>