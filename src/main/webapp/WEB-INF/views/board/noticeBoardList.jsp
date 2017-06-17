<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<link href="assets/fonts/font-awesome.css" rel="stylesheet"
	type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/bootstrap-select.min.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/dropzone.css" type="text/css">
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<link rel="stylesheet" href="assets/css/user.style.css" type="text/css">


<style type="text/css">
body p {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 100px;
	height: 20px;
}
</style>

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
					<div class="container">
						<h2>Notice</h2>
						<span style="color: #9999ff;">�������� �ְ��� ���� DoThing�� �ֽ��ϴ�.</span>
						<table class="table table-hover">
							<thead align="left">
								<tr>
									<td align="left"><Strong>������</Strong></td>
								</tr>
							</thead>
								<c:choose>
									<c:when test="${empty requestScope.list}">
										<tr>
											<td colspan="5">
												<div align="center">
													<b><span style="font-size: 9pt;">��ϵ� �Խù��� �����ϴ�.</span></b>
												</div>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${requestScope.list}" var="boardDto">
											<tbody align="left">
												<tr>
													<td><a
														href="${pageContext.request.contextPath}/board/noticeBoardRead/${boardDto.noticeNum}"><h1>${boardDto.noticeTitle}</h1></a>

														<input type=hidden name="noticeNum"
														value="${boardDto.noticeNum}"> <input
														type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}"> <a href="#"
														onclick="location.href='${pageContext.request.contextPath}/board/noticeDelete?noticeNum=${boardDto.noticeNum}'">
															<i class="fa fa-trash-o" data-toggle="tooltip"
															title="�����ϱ�"></i>
													</a> <a><i class="fa fa-pencil" data-toggle="tooltip"
															title="�����ϱ�"></i></a> <span style="color: #ff513f;">${boardDto.noticeDate}</span>
													</td>
												</tr>
											</tbody>
										</c:forEach>
									</c:otherwise>
								</c:choose>		
						</table>
					</div>

					<!-- ���������̼� -->

					<!-- <ul class="pager">
						<li><a href="#">Previous</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">Next</a></li>
					</ul>
 -->

					<ul class="pagination" style="margin-left: 35%">
						<c:if test="${pm.previous}">
							<li><a
								href="${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.lastPage - 5}"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
						</c:if>
						<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
							varStatus="state">
							<c:if test="${pm.currentPage == (pm.startPage + state.count-1)}">
								<li class="active"><a
									href='${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
								</li>
							</c:if>
							<c:if test="${pm.currentPage != (pm.startPage + state.count-1)}">
								<li><a
									href='${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${pm.next}">
							<li><a
								href="${pageContext.request.contextPath}/board/noticeBoardList?page=${pm.lastPage + 1}"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</c:if>

					</ul>
					<!-- ���������̼� ���� -->

					<div class="form-group" align="center">
						<button type="submit" class="btn btn-large btn-default"
							id="submit"
							onclick="location.href='${pageContext.request.contextPath}/board/noticeBoardWrite'">���ۼ��ϱ�</button>
					</div>
					<!-- /.form-group -->
				</div>
				<!--/.col-md-6-->
			</div>

		</div>
		<!--Password-->

	</div>
	</section>

	<script type="text/javascript" src="assets/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="assets/js/before.load.js"></script>
	<script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/smoothscroll.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="assets/js/dropzone.min.js"></script>
	<script type="text/javascript" src="assets/js/custom.js"></script>
	<script type="text/javascript" src="assets/js/maps.js"></script>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->

	<script>
		if ($('#page-content').length > 0) {
			$("#page-content").mCustomScrollbar();
		}
	</script>
</body>
</html>