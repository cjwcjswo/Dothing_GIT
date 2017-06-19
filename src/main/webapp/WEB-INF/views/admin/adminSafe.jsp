<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>Spotter - Universal Directory Listing HTML Template</title>

<script type="text/javascript">

	//이미지 클릭시 원본 크기로 팝업 보기
	function doImgPop(img) {
		img1 = new Image();
		img1.src = (img);
		imgControll(img);
	}

	function imgControll(img) {
		if ((img1.width != 0) && (img1.height != 0)) {
			viewImage(img);
		} else {
			controller = "imgControll('" + img + "')";
			intervalID = setTimeout(controller, 20);
		}
	}
	function viewImage(img) {
		W = img1.width;
		H = img1.height;
		O = "width=" + W + ",height=" + H + ",scrollbars=yes";
		imgWin = window.open("", "", O);
		imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
		imgWin.document.write("<body topmargin=0 leftmargin=0>");
		imgWin.document.write("<img src=" + img + " onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
		imgWin.document.close();
	}
</script>


</head>

<body onunload=""
	class="page-subpage page-profile navigation-top-header" id="page-top">

	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<!-- Page Canvas-->
			<div id="page-canvas">



				<!--Page Content-->
				<div id="page-content">
					<section class="container">
						<header>
							<ul class="nav nav-pills">
								<li><a
									href="${pageContext.request.contextPath}/admin/adminMoney"><h1
											class="page-title">무통장 입금 확인</h1></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/adminSafe"><h1
											class="page-title">안전 심부름꾼 승인</h1></a></li>
								<li><a
									href="${pageContext.request.contextPath}/admin/adminUserList"><h1
											class="page-title">회원관리</h1></a></li>
							</ul>
						</header>
						<div class="row">
							<div class="col-md-9">

								<div class="row">

									<!--Contact Info-->
									<div class="col-md-9 col-sm-9">


										<div class="well">
											<c:forEach items="${memberList}" var="member">
												<div class="media">
													<a class="pull-left" href="#"><img class="media-object"
														src="${pageContext.request.contextPath }/users/${member.userId}/ssn/${member.ssnImg}"
														onclick="doImgPop('${pageContext.request.contextPath }/users/${member.userId}/ssn/${member.ssnImg}')"
														title="클릭하시면 원본크기로 보실 수 있습니다."
														style="width: 150px; height: 150px;"> </a>
													<!-- doImgPop('경로') 경로 부분에 원본 이미지 경로 넣어주셈 -->
													<div class="media-body">
														<h4 class="media-heading">아이디: ${member.userId}</h4>
														<h4 class="media-heading">이름: ${member.name}</h4>
														<h4 class="media-heading">핸드폰: ${member.phone}</h4>
														<h4 class="media-heading">주소: ${member.addr}</h4>
														<div class="form-group">
												<button type="button" class="btn btn-large btn-default"
													id="okay">확인</button>
											</div>
													</div>
												</div>
											</c:forEach>
										</div>



										<!-- 페이지네이션 -->

										<ul class="pager">
											<c:if test="${pm.previous}">
												<li><a
													href="${pageContext.request.contextPath}/admin/adminSafe?page=${pm.lastPage - 5}&id=${sid}"><span
														class="glyphicon glyphicon-chevron-left"></span></a></li>
											</c:if>
											<c:if test="${pm.startPage != pm.lastPage }">
												<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
													varStatus="state">
													<c:if
														test="${pm.currentPage == (pm.startPage + state.count-1)}">
														<li class="active"><a
															href='${pageContext.request.contextPath}/admin/adminSafe?page=${pm.startPage + state.count-1}&id=${sid}'>${pm.startPage + state.count-1}</a>
														</li>
													</c:if>
													<c:if
														test="${pm.currentPage != (pm.startPage + state.count-1)}">
														<li><a
															href='${pageContext.request.contextPath}/admin/adminSafe?page=${pm.startPage + state.count-1}&id=${sid}'>${pm.startPage + state.count-1}</a>
														</li>
													</c:if>
												</c:forEach>
											</c:if>
											<c:if test="${pm.next}">
												<li><a
													href="${pageContext.request.contextPath}/admin/adminSafe?page=${pm.lastPage + 1}&id=${sid}"><span
														class="glyphicon glyphicon-chevron-right"></span></a></li>
											</c:if>
										</ul>

										<!-- 페이지네이션 종료 -->


										<!-- /.form-group -->
									</div>
									<!--/.col-md-6-->
								</div>

							</div>

							<!-- /.col-md-3-->
						</div>
					</section>
				</div>
				<!-- end Page Content-->
			</div>
			<!-- end Page Canvas-->

		</div>
		<!-- end Inner Wrapper -->
	</div>
	<!-- end Outer Wrapper-->

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>