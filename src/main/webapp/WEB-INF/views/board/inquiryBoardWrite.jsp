<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board/inquiryBoard/inquiryBoardWrite.css">

<script type="text/javascript">
function checkValid() {
    var f = window.document.writeForm;
		
	if ( f.inquiry_title.value == "") {
	    alert( "글제목을 입력하세요." );
	    f.modelNum.focus();
		return false;
    }
	if ( f.inquiry_content.value == "" ) {
		alert( "글내용을 입력하세요." );
		f.modelName.focus();
		return false;
	}	
    return true;
}
</script>
</head>
<body>
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder"></div>
			<form name="writeForm" method="post" action="${pageContext.request.contextPath}/board/insert" onSubmit='return checkValid()'>
				<input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
				<h2 class="text-center">
					<strong>1:1 게시판 작성하기</strong>
				</h2>
				<div class="form-group" align="center">
					<img
						src="${pageContext.request.contextPath}/resources/img/anonymous-icon-16523.png"
						class="img-circle" width="150" height="150">
				</div>
				<div class="form-group">
					<label for="id">글 제목</label> <input class="form-control"
						type="text" name="boardTitle" placeholder="글 제목을 작성해 주세요">
				</div>

				<br>

				<div class="form-group">
					<label for="comment">Comment:</label>
					<textarea class="form-control input-lg" rows="5" id="comment" name="boardContent"></textarea>
				</div>
				<div class="form-group">
					<button class="btn btn-primary btn-block" type="submit">작성완료</button>
				</div>

			</form>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

</body>


</html>