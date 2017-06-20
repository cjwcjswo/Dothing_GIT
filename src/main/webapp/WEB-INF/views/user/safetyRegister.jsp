<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>Spotter - Universal Directory Listing HTML Template</title>
<script>
	function checkValid(){
		if (document.getElementById("upload").value == "") {
			alert("사진을 넣어주세요!");
			document.getElementById("upload").focus();
			return false;
		}
		return true;
	}
</script>
</head>

<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">

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
					<div class="search-bar horizontal collapse"
						id="redefine-search-form"></div>
					<!-- /.search-bar -->
					<div class="breadcrumb-wrapper">
						<div class="container">

							<ol class="breadcrumb">
								<li><a href="${pageContext.request.contextPath}/"><i
										class="fa fa-home"></i></a></li>
								<li class="active">안전심부름꾼 신청</li>
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
						<header>
							<ul class="nav nav-pills">
								<li><a
									href="${pageContext.request.contextPath}/user/myPage"><h1
											class="page-title">개인 정보</h1></a></li>
								<li><a
									href="${pageContext.request.contextPath}/errand/myRequest"><h1
											class="page-title">심부름 요청 및 수행 내역</h1></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/user/safetyRegister"><h1
											class="page-title">안전심부름꾼 신청</h1></a></li>
								<li><a href="safetyRegister.jsp"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>

							</ul>
						</header>
						<div class="row">

							<div class="col-md-9 col-sm-9">
								<section>
									<h3>
										<i class="fa fa-thumbs-up"></i>인증 심부름꾼 여부<br>
										<c:if test="${member.ssnImg == null && !isSafety}">
											<div class="alert alert-danger" role="alert">안전심부름꾼이
												아닙니다. 신청해주세요!</div>
										</c:if>
										<c:if test="${member.ssnImg != null && !isSafety}">
											<div class="alert alert-danger" role="alert">운영자가 현재
												심사중입니다!</div>
										</c:if>
										<c:if test="${member.ssnImg != null && isSafety}">
											<div class="alert alert-success" role="alert">안전심부름꾼입니다
												:)</div>
										</c:if>
									</h3>
									<c:if test="${!(member.ssnImg != null && isSafety)}">
										<form
											action="${pageContext.request.contextPath}/user/submitSafety"
											enctype="multipart/form-data" method="post" onSubmit="return checkValid();">
											<div class="form-group" align="center" id="holder">
												<c:if test="${member.ssnImg != null}">
													<img
														src="${pageContext.request.contextPath}/users/${member.userId}/ssn/${member.ssnImg}"
														class="img-circle" width="200" height="200">
												</c:if>
												<c:if test="${member.ssnImg == null}">
													<img
														src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
														class="img-circle" width="200" height="200">
												</c:if>
											</div>
											<input class="form-control" type="file" name="ssnImgFile"
												placeholder="picture" id="upload" /> <input type="hidden"
												name="${_csrf.parameterName}" value="${_csrf.token}" />
											<p>
											<h3>
												<i class="fa fa-info"></i>신분증의 생년월일 앞자리를 가린 후 올려주세요!

											</h3>
											</p>
											추후에 관리자가 심사 후 인증 심부름꾼으로 등록해드립니다.
											<div class="form-group">
												<button type="submit" class="btn btn-large btn-default"
													id="submit">등록</button>
											</div>
										</form>
									</c:if>

								</section>
							
							</div>
						</div>
					</section>
				</div>
				<!-- end Page Content-->
			</div>
			<!-- end Page Canvas-->

	<!-- end Outer Wrapper-->
	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
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
</body>
</html>