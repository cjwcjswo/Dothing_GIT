<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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



<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board/inquiryBoard/MUSA_panel-table.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board/inquiryBoard/User-Tasks-and-User-Activity-Streams---List-Group-Component.css">
</head>
<body>

	<ul class="list-group"></ul>
	<link
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
		rel='stylesheet' type='text/css'>

	<div class="container">
		<div class="row">


			<p></p>
			<p></p>

			<div class="col-md-10 col-md-offset-1">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">1:1 문의 게시판</h3>
							</div>
							<div class="col col-xs-6 text-right">
								<a type="button" class="btn btn-sm btn-primary btn-create" href="${pageContext.request.contextPath}/board/inquiryBoardWrite">글
									작성하기</a>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>
									<th><em class="fa fa-cog"></em></th>
									<th class="hidden-xs">글번호</th>
									<th>ID</th>
									<th>글제목</th>
									<th>날짜</th>
									<th>조회 수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="center"><a class="btn btn-default"><em
											class="fa fa-pencil"></em></a> <a class="btn btn-danger" data-toggle="modal" data-target="#myModal1"><em
											class="fa fa-trash" ></em></a></td>
									<td class="hidden-xs">1</td>
									<td>바바야가</td>
									<td><a href="${pageContext.request.contextPath}/board/inquiryBoardRead">심부름 좀 많이 올라 왔으면 좋겠어요</a></td>
									<td>2017-06-09</td>
									<td>5</td>
								</tr>
							</tbody>
						</table>

					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col col-xs-4">Page 1 of 5</div>
							<div class="col col-xs-8">
								<ul class="pagination hidden-xs pull-right">
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
								</ul>
								<ul class="pagination visible-xs pull-right">
									<li><a href="#">«</a></li>
									<li><a href="#">»</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!--  modal 부분 -->
	<!-- Modal -->
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">글 삭제하기</h4>
				</div>
				<div class="modal-body">
					<p>정말로 삭제 하시겠습니까?</p>
				</div>
				<div class="modal-footer">
				
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>

				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>