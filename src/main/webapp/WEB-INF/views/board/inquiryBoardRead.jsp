<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
function checkValid() {
    var f = window.document.f;
	
	if ( f.reply_content.value == "" ) {
		alert( "댓글 내용을 입력하세요." );
		return false;
	}	
    return true;
}
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board/inquiry/inquiryBoardRead.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-default widget">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-comment"></span>
					<h3 class="panel-title">1:1 게시판</h3>
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
								<div class="col-xs-2 col-md-1">
									<img src="http://placehold.it/80"
										class="img-circle img-responsive" alt="" />
								</div>
								<div class="col-xs-10 col-md-11">
									<div>
										<a href="#">${board.userId}</a>
										<div class="mic-info">
											By: ${board.boardDate}
										</div>
									</div>
									<div class="comment-text">${board.boardContent}</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="col">
					<div class="panel-body">
						<form name="f" role="form" method="post" action="${pageContext.request.contextPath}/board/insertReply"
						onsubmit="return checkValid()">

							<fieldset>
								<div class="form-group">
									<textarea class="form-control" name="replyContent" rows="3"
										placeholder="댓글 내용~~~" autofocus=""></textarea>
								</div>
								<input type="hidden" name="board.inquiryNum"
									value="${board.inquiryNum}">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
								<button type="submit" class="[ btn btn-success ]"
									data-loading-text="Loading...">댓글 달기</button>
							</fieldset>
						</form>
					</div>
				</div>


				<!-- 댓글 목록 시작 -->
				<div class="panel-body">
					<ul class="list-group">

						<c:choose>
							<c:when test="${empty requestScope.reply}">
								<tr>
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 댓글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${requestScope.reply}" var="replyDto">
									<li class="list-group-item">
										<div class="row">
											<div class="col-xs-2 col-md-1">
												<img src="http://placehold.it/80"
													class="img-circle img-responsive" alt="" />
											</div>
											<div class="col-xs-10 col-md-11">
												<div>
													<a href="#">운영자</a>
												</div>
												<div class="comment-text">${replyDto.replyContent}</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- 댓글 끝 -->
				<!--  페이지네이션 -->

				<div class="container" align="center">
					<ul class="pagination">
						<li class="disabled"><a href="#">«</a></li>
						<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
					</ul>
				</div>
				<!--  페이지 네이션 끝 -->
			</div>
		</div>
	</div>


</body>
</html>