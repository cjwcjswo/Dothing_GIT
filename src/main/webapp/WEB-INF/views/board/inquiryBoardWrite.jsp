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

</head>
<body>
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder"></div>
			<form method="post">
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
						type="text" name="id" placeholder="글 제목을 작성해 주세요">
				</div>

				<br>
				
				<div class="form-group">
					<label for="comment">Comment:</label>
					<textarea class="form-control input-lg" rows="5" id="comment"></textarea>
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