<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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

<script type="text/javascript">
function checkValid() {
    var f = window.document.writeForm;
		
	if ( f.notice_title.value == "") {
	    alert( "글제목을 입력하세요." );
	    f.noticeTitle.focus();
		return false;
    }
	if ( f.notice_content.value == "" ) {
		alert( "글내용을 입력하세요." );
		f.noticeContent.focus();
		return false;
	}	
    return true;
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
					<div class="container">
						<h2>Notice</h2>
						<span style="color:#9999ff;">여러분은 최고의 서비스 DoThing에 있습니다.</span>
						<form name="writeForm" action="${pageContext.request.contextPath}/board/noticeInsert" onSubmit='return checkValid()' method="post" style="width:70% ;margin-left:15%" align="center" >
							<input type="hidden" name="${_csrf.parameterName}"
                             value="${_csrf.token}">
							<div class="form-group">
								<label for="faq-form-email">제목</label>
								<input type="text"
									class="form-control" name="noticeTitle" id="faq-form-email" required="" >
							</div>
							<!-- /.form-group -->
							<div class="form-group">
								<label for="faq-form-question">게시물 작성</label>
								<textarea class="form-control" id="faq-form-question"
									rows="5" required="" name="noticeContent"></textarea>
							</div>
							<!-- /.form-group -->
							<div class="form-group">
							<span align="left"><a href="" ><i class="fa fa-backward"></i>돌아가기</a></span>
								<button type="submit" class="btn btn-default">등록하기</button>
							</div>
							<!-- /.form-group -->
						</form>

					</div>
					<!--/.col-md-6-->
				</div>
			</div>
			<!--Password-->
		</div>
		</div>

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
</body>
</html>