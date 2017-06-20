<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link
	href="${pageContext.request.contextPath}/assets/fonts/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="sheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath}/assets/tags/bootstrap-tagsinput.css">

<style>
div.stars {
	width: 270px;
	display: inline-block;
}

input.star {
	display: none;
}

label.star {
	float: right;
	padding: 10px;
	font-size: 36px;
	color: #444;
	transition: all .2s;
}

input.star:checked ~ label.star:before {
	content: '\f005';
	color: #FD4;
	transition: all .25s;
}

input.star-5:checked ~ label.star:before {
	color: #FE7;
	text-shadow: 0 0 20px #952;
}

input.star-1:checked ~ label.star:before {
	color: #F62;
}

label.star:hover {
	transform: rotate(-15deg) scale(1.3);
}

label.star:before {
	content: '\f006';
	font-family: FontAwesome;
}
</style>

<title>심부름 내역</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/tags/bootstrap-tagsinput.js"></script>
<script>
	$(function() {
		$(document).on("click", "#cancleModal", function(){
			$("#cancleNum").val($(this).attr("cn"));
			$("#canclePoint").val($(this).attr("cp"));
		});
		$("#cancleBtn").click(function(){
			var num = $("#cancleNum").val();
			var point = $("#canclePoint").val();
			location.href = "${pageContext.request.contextPath}/errand/cancle?num="+num+"&point="+point;
		});
		$(document).on("click", "button[me='eval']", function() {
			$(".bootstrap-tagsinput span").remove();
			$('#ac1').attr("checked", true);
			$('#sp1').attr("checked", true);
			$('#kn1').attr("checked", true);
			var errandsNum = $(this).attr("id");
			$.ajax({
				url : "${pageContext.request.contextPath}/user/selectMember",
				type : "post",
				data : "id=" + $(this).attr("meId") + "&_csrf=${_csrf.token}",
				dataType : "json",
				success : function(result) {
					$("#modalId").html(result.userId);
					$("#errandsNum").val(errandsNum);
					$("#modalImg").attr("src", "${pageContext.request.contextPath}/users/" + result.userId + "/" + result.selfImg);
				},
				error : function(error) {
					console.log(error);
				}
			})





			$("#myModal").modal('toggle');
		});



	});
</script>
</head>

<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">

	<!-- Page Canvas-->
	<div id="page-canvas">
		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation">
			<header>Navigation</header>
			<div class="main-navigation navigation-off-canvas"></div>
		</nav>
		<!--end Off Canvas Navigation-->

		<!--Sub Header-->
		<section class="sub-header">
			<div class="search-bar horizontal collapse" id="redefine-search-form"></div>
			<!-- /.search-bar -->
			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="redefine-search">
						<a href="#redefine-search-form" class="inner"
							data-toggle="collapse" aria-expanded="false"
							aria-controls="redefine-search-form"> <span class="icon"></span>
							<span>Redefine Search</span>
						</a>
					</div>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}/"><i
								class="fa fa-home"></i></a></li>
						<li class="active">심부름 내역</li>
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
						<li><a href="${pageContext.request.contextPath}/user/myPage"><h1
									class="page-title">개인정보</h1></a></li>
						<li class="active"><a
							href="${pageContext.request.contextPath}/errand/myRequest"><h1
									class="page-title">심부름 요청 및 수행 내역</h1></a></li>
						<li><a
							href="${pageContext.request.contextPath}/user/safetyRegister"><h1
									class="page-title">안전심부름꾼 신청</h1></a></li>
						<li class=""><a href="safetyRegister.jsp"><h1
									class="page-title">포인트 충전 및 내역</h1></a></li>
					</ul>
				</header>
				<div class="row">
					<div class="col-md-3 col-sm-3">
						<aside id="sidebar">
							<ul class="navigation-sidebar list-unstyled">
								<li class="active"><a
									href="${pageContext.request.contextPath}/errand/myRequest">
										<i class="fa fa-check"></i> <span>심부름 요청 내역</span>
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/errand/myResponse">
										<i class="fa fa-clock-o"></i> <span> 심부름 수행 내역</span>
								</a></li>

							</ul>
						</aside>
					</div>
					<div class="col-md-9 col-sm-9" id="history">
						<section id="items">
							<c:forEach items="${errandsList}" var="errands">
								<div class="item list admin-view">
									<div class="image">
										<c:if test="${errands.errandsPhoto != null}">
											<img
												src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
										</c:if>
										<c:if test="${errands.errandsPhoto == null}">
											<img
												src="${pageContext.request.contextPath}/resources/img/errands/img.png" />
										</c:if>
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}">
											<div class="item-specific">
												<span title="Bedrooms"><i class="fa fa-krw">E: <fmt:formatNumber
															value="${errands.errandsPrice}" /></i> <i class="fa fa-krw">
														P:<fmt:formatNumber value="${errands.productPrice}" />
												</i></span>

											</div> <!-- <div class="icon">
												<i class="fa fa-thumbs-up"></i>
												인증 심부름꾼이면 별 아니면 여기 div 지우셈
											</div> <img src="assets/img/items/1.jpg" alt=""> -->
										</a>
									</div>
									<div class="wrapper">
										<a
											href="${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}"><h3>${errands.title}</h3></a>
										<a>${errands.content} </a>
										<figure>${errands.errandsPos.addr}
										</figure>
										<div class="info">
											<div class="type">
												<div class="price">${errands.errandsReply.size()}개댓글
													달림</div>
												<c:if test="${errands.hashes.size()	 != 0}">
													<c:forEach items="${errands.hashes}" var="hash">
														<span class="label label-info">#${hash}</span>
													</c:forEach>
												</c:if>
											</div>
											<c:if
												test="${errands.finishTime != null and errands.arrivalTime != null}">

												<c:forEach items="${errands.gpa}" var="gpa">
													<c:if test="${gpa.requestManners != 0}">
														<div class="rating" data-rating="${gpa.requestManners}"></div>
													</c:if>

												</c:forEach>

											</c:if>
											<br> <a>마감시간: ${errands.endTime}</a>
											<!-- 평점 -->
										</div>
									</div>
									<div class="description">
										<ul class="list-unstyled actions">

											<li><a href="#" class="hide-item"><i
													class="fa fa-eye"></i></a></li>
											<c:if test="${errands.startTime == null}">
												<li><i class="fa fa-close"></i>심부름꾼 대기중</li>
											</c:if>
											<c:if test="${errands.startTime != null}">
												<c:if test="${(errands.finishTime == null)}">
													<li><i class="fa fa-taxi"></i>요청 완료</li>
													<br>
													<span class="label label-warning">심부름꾼:
														${errands.responseUser.userId}</span><br>
													<button class="btn btn-danger" id="${errands.errandsNum}"
														me="eval" meId="${errands.responseUser.userId}">OK</button>
													<a data-toggle="modal" href="#myModal2"
														class="btn btn-danger" id="cancleModal" cn="${errands.errandsNum}"
														cp="${errands.errandsPrice + errands.productPrice}">삭제</a>
												</c:if>
											</c:if>
											<c:if
												test="${(errands.finishTime != null) && (errands.arrivalTime == null)}">
												<li><i class="fa fa-check"></i>심부름꾼 확인 대기중</li>
												<span class="label label-warning">심부름꾼:
													${errands.responseUser.userId}</span>
											</c:if>
											<c:if
												test="${(errands.finishTime != null) && (errands.arrivalTime != null)}">
												<li><i class="fa fa-check"></i>심부름 완료</li>
												<span class="label label-warning">심부름꾼:
													${errands.responseUser.userId}</span>
											</c:if>

										</ul>
									</div>
									<c:if
										test="${(errands.finishTime != null) && (errands.arrivalTime != null)}">
										<div class="ribbon">
											<!-- 완료 시 <! class="fa fa-check> 
                                        미완료시 <! class=fa fa-close" -->
										</div>
									</c:if>

								</div>
							</c:forEach>

						</section>
						<ul class="pagination" style="margin-left: 25%">
							<c:if test="${pm.previous}">
								<li><a
									href="${pageContext.request.contextPath}/errand/myRequest?page=${pm.lastPage - 5}"><span
										class="glyphicon glyphicon-chevron-left"></span></a></li>
							</c:if>
							<c:if test="${pm.startPage != pm.lastPage }">
								<c:forEach begin="${pm.startPage}" end="${pm.lastPage}"
									varStatus="state">
									<c:if
										test="${pm.currentPage == (pm.startPage + state.count-1)}">
										<li class="active"><a
											href='${pageContext.request.contextPath}/errand/myRequest?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
										</li>
									</c:if>
									<c:if
										test="${pm.currentPage != (pm.startPage + state.count-1)}">
										<li><a
											href='${pageContext.request.contextPath}/errand/myRequest?page=${pm.startPage + state.count-1}'>${pm.startPage + state.count-1}</a>
										</li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${pm.next}">
								<li><a
									href="${pageContext.request.contextPath}/errand/myRequest?page=${pm.lastPage + 1}"><span
										class="glyphicon glyphicon-chevron-right"></span></a></li>
							</c:if>

						</ul>
					</div>
				</div>
			</section>
		</div>
		<!-- end Page Content-->
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog" style="width: 50%">
				<form name="f" id="f"
					action="${pageContext.request.contextPath}/errand/confirmErrand"
					method="post">
					<input type="hidden" name="requestId"
						value="${errandsList[0].requestUser.userId}" />
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="fa fa-close" data-dismiss="modal"></button>
							<h4 class="modal-title" id="myModalLabel">평가해주세요!</h4>
						</div>
						<div class="modal-body">
							<center>
								<img src="" name="aboutme" width="140" height="140" border="0"
									class="img-circle" id="modalImg">
								<h3 id="modalId">심부름꾼 아이디</h3>
								<div class="stars" style="width: 60%">
									<table style="width: 100%;">
										<tr>
											<th>정확성</th>
											<th><input class="star star-5" id="ac5" type="radio"
												name="responseAccuracy" value="5" /> <label
												class="star star-5" for="ac5"></label> <input
												class="star star-4" id="ac4" type="radio"
												name="responseAccuracy" value="4" /> <label
												class="star star-4" for="ac4"></label> <input
												class="star star-3" id="ac3" type="radio"
												name="responseAccuracy" value="3" /> <label
												class="star star-3" for="ac3"></label> <input
												class="star star-2" id="ac2" type="radio"
												name="responseAccuracy" value="2" /> <label
												class="star star-2" for="ac2"></label> <input
												class="star star-1" id="ac1" type="radio"
												name="responseAccuracy" value="1" checked="checked" /> <label
												class="star star-1" for="ac1"></label></th>
										</tr>
										<tr>
											<th>신속함</th>
											<th><input class="star star-5" id="sp5" type="radio"
												name="responseSpeed" value="5" /> <label
												class="star star-5" for="sp5"></label> <input
												class="star star-4" id="sp4" type="radio"
												name="responseSpeed" value="4" /> <label
												class="star star-4" for="sp4"></label> <input
												class="star star-3" id="sp3" type="radio"
												name="responseSpeed" value="3" /> <label
												class="star star-3" for="sp3"></label> <input
												class="star star-2" id="sp2" type="radio"
												name="responseSpeed" value="2" /> <label
												class="star star-2" for="sp2"></label> <input
												class="star star-1" id="sp1" type="radio"
												name="responseSpeed" value="1" checked="checked" /> <label
												class="star star-1" for="sp1"></label></th>
										</tr>
										<tr>
											<th>친절도</th>
											<th><input class="star star-5" id="kn5" type="radio"
												name="responseKindness" value="5" /> <label
												class="star star-5" for="kn5"></label> <input
												class="star star-4" id="kn4" type="radio"
												name="responseKindness" value="4" /> <label
												class="star star-4" for="kn4"></label> <input
												class="star star-3" id="kn3" type="radio"
												name="responseKindness" value="3" /> <label
												class="star star-3" for="kn3"></label> <input
												class="star star-2" id="kn2" type="radio"
												name="responseKindness" value="2" /> <label
												class="star star-2" for="kn2"></label> <input
												class="star star-1" id="kn1" type="radio"
												name="responseKindness" value="1" checked="checked" /> <label
												class="star star-1" for="kn1"></label></th>
										</tr>
									</table>

								</div>
							</center>
							<hr>
							<center>
								<p class="text-left">
									<input type="text" id="evalTag" data-role="tagsinput"
										placeholder="태그로 사용자를 평가해주세요!" name="evalTag" />
								</p>
								<br>
							</center>
						</div>
						<div class="modal-footer">
							<center>
								<button type="button" class="btn btn-default"
									onclick="document.getElementById('f').submit()">심부름 완료</button>
							</center>
						</div>
					</div>
					<input type="hidden" id="errandsNum" name="errandsNum"> <input
						type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<!-- END MODAL1 -->
		<div id="myModal2" class="modal fade in">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<a class="btn btn-default" data-dismiss="modal"><span
							class="glyphicon glyphicon-remove"></span></a>
						<h4 class="modal-title">정말로 삭제하시겠습니까?</h4>
					</div>
					<div class="modal-body">
						<h4>심부름꾼이 배달중인지 확인해주세요!</h4>
						<p>배달중일경우 취소를 할 시 큰 불이익을 받을 수 있습니다!</p>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button class="btn btn-danger" data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span>취소
							</button>
							<button class="btn btn-primary" id="cancleBtn">
								<span class="glyphicon glyphicon-check"></span>삭제
							</button>
							<input type="hidden" id="cancleNum"/>
							<input type="hidden" id="canclePoint"/>
						</div>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dalog -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- end Page Canvas-->
	<script>
		
	</script>
</body>
</html>