<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat/chat.css"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/receipt.css"
	rel="stylesheet" type="text/css">
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

<title>상세보기</title>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/tags/bootstrap-tagsinput.js"></script>
<script>
	$(function() {
		var currentId = "<security:authentication property='principal.userId'/>";
		$(document).on("click", "#complete", function() {
			$(".bootstrap-tagsinput span").remove();
			if("${currentId}" == "${errands.responseUser.userId}"){
			$('#ac1').attr("checked", true);
			var errandsNum = ${errands.errandsNum};
			$.ajax({
				url : "${pageContext.request.contextPath}/user/selectMember",
				type : "post",
				data : "id=${errands.requestUser.userId}&_csrf=${_csrf.token}",
				dataType : "json",
				success : function(result) {
					$("#resmodalId").html(result.name);
					$("#responseId").val(currentId);
					$("#resmodalImg").attr("src", "${pageContext.request.contextPath}/users/" + result.userId + "/" + result.selfImg);
				},
				error : function(error) {
					console.log(error);
				}
			})





			$("#resModal").modal('toggle');
			//현재사용자가 요청자일경우
			}else if("${currentId}" == "${errands.requestUser.userId}"){
				$('#ac1').attr("checked", true);
				$('#sp1').attr("checked", true);
				$('#kn1').attr("checked", true);
				$.ajax({
					url : "${pageContext.request.contextPath}/user/selectMember",
					type : "post",
					data : "id=${errands.responseUser.userId}&_csrf=${_csrf.token}",
					dataType : "json",
					success : function(result) {
						$("#reqmodalId").html(result.name);
						$("#reqmodalImg").attr("src", "${pageContext.request.contextPath}/users/" + result.userId + "/" + result.selfImg);
					},
					error : function(error) {
						console.log(error);
					}
				})





				$("#reqModal").modal('toggle');
			}
		});


	});
</script>



<!-- SocketJS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/sockjs.js"></script>
<script type="text/javascript">
   var sender = '${currentId}';
   var today = '<%=new java.text.SimpleDateFormat("MM/dd HH시mm분").format(new java.util.Date())%>';
   var receiver ="";
   var receiverPhoto = "";
   if("${currentId}" == "${errands.requestUser.userId}"){
	   receiverPhoto = "${requestSelfImg}";
	   receiver = "${errands.responseUser.userId}";
   }else {
	   receiver = "${errands.requestUser.userId}";
	   receiverPhoto = "${responseSelfImg}";
   }
function initChatting(){
		 ws.send('${errands.errandsNum}#/separator/#');
		document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
	}
	setTimeout("initChatting()", 500);
   $(function() {
	
	
	   $('#inputText').keyup(function(e) {
	          if (e.keyCode == 13)
	             sendMessage();
	    });
	   $("#send").click(function() {
	         sendMessage();
	      });
	   
	   function settingWS(){
	   ws.onclose = function() {
			if ("${currentId}" != "") {
				ws = new SockJS("${pageContext.request.contextPath}/websocket");
				settingWS();
				//setTimeout("initChatting()", 500);
			}
		}
	   
	   ws.onmessage = function(e){
		   var alertArr = e.data.split(':');
			if (alertArr[0] == "댓글") {
				sendAlert("<a href='${pageContext.request.contextPath}/errand/detailView?num="
						+ alertArr[1].split("번")[0]
						+ "'>"
						+ alertArr[1]
						+ "</a>");

			} else if (alertArr[0] == "심부름") {
				//이미지가 없는경우
				var imgAttr = "";
				if (alertArr[3] == "EMPTY") {
					imgAttr = "${pageContext.request.contextPath}/resources/img/errands/img.png";
				} else {
					imgAttr = "${pageContext.request.contextPath}/errands/"
							+ alertArr[1] + "/" + alertArr[3];
				}
				$(document)
						.on(
								"click",
								".writeAlert",
								function() {
									location.href = "${pageContext.request.contextPath}/errand/detailView?num="
											+ alertArr[1];
								});
				$("#writeImg").attr("src", imgAttr);
				$("#writeDes").html(
						"<h4>" + alertArr[1] + "번 심부름이 등록됬습니다.</h4> <br>"
								+ alertArr[2]);
				$('.writeAlert').fadeIn(400).delay(5000).fadeOut(400);

			} else if (alertArr[0] == "선택") {
				sendAlert("<a href='${pageContext.request.contextPath}/errand/detailView?num="
						+ alertArr[1].split("번")[0]
						+ "'>"
						+ alertArr[1]
						+ "</a>");
			} else if (alertArr[0] == "알림") {
				if ("${currentId}" == "") {
					$(document)
							.on(
									"click",
									"#chatAlert",
									function() {
										location.href = "${pageContext.request.contextPath}/errand/detailView?num="
												+ alertArr[1];
									});
					$("#balloon").html("<span>" + alertArr[2] + "</span>")
					$("#balloonImg").attr(
							"src",
							"${pageContext.request.contextPath}/users/"
									+ alertArr[4] + "/" + alertArr[3]);
					$('#chatAlert').fadeIn(400).delay(5000).fadeOut(400);
				}
			} else {
			
				var arr = e.data.split('#/separator/#');
				var notice = e.data.substring(0,2);
				if(notice != '알림'){
					//59#/separator/#tester#/separator/#ggaa#/separator/#06/17 09:27
					var str = '';
				
					if (arr[1] == sender) {
						str = '<div class="row msg_container base_sent"><div class="col-xs-10 col-md-10">' +
							'<div class="messages msg_sent"><p>' + arr[2] + '</p>' +
							'<time datetime="2009-11-13T20:00">' + 
							'<c:if test="${currentId eq requestId}">' +
								'${requestUserName}' +
							'</c:if>'+
							'<c:if test="${currentId eq responseId}">' +
								'${responseUserName}' +
							'</c:if>'+
							'•' + arr[3] + '</time>' +
							'</div></div><div class="col-md-2 col-xs-2 avatar">' +
							'<img src="${pageContext.request.contextPath}/users/${currentId}/${currentUser.selfImg}"' +
							' class="img-responsive"></div></div>';
					} else {
						str = '<div class="row msg_container base_receive">' +
							'<div class="col-md-2 col-xs-2 avatar">' +
							'<c:if test="${currentId eq errands.requestUser.userId}">' +
							'<img' +
							' src="${pageContext.request.contextPath}/users/${errands.responseUser.userId}/${responseSelfImg}"' +
							' class=" img-responsive ">' +
							'</c:if>' +
							'<c:if test="${currentId eq errands.responseUser.userId}">' +
							'<img' +
							' src="${pageContext.request.contextPath}/users/${errands.requestUser.userId}/${requestSelfImg}"' +
							' class=" img-responsive "> ' +
							'</c:if>' +
							'</div>' +
							'<div class="col-xs-10 col-md-10">' +
							'<div class="messages msg_receive">' +
							'<p>' + arr[2] + '</p>' +
							'<time datetime="2009-11-13T20:00">' +
							'<c:if test="${currentId eq requestId}">' +
								'${responseUserName}' +
							'</c:if>'+
							'<c:if test="${currentId eq responseId}">' +
								'${requestUserName}' +
							'</c:if>'+
							' • ' + arr[3] + '</time>' +
							'</div>' +
							'</div>' +
							'</div>';
					}
	
					$('#chatList').append(str);
					//스크롤 맨 아래로
					document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
				}}
	   }
	   }
	   settingWS();
	   
	   
	   function sendMessage() {
	      //WebSocket으로 메시지를 전달한다.
	      var msg = $('#inputText').val();
			//separator -> #/separator/#
			
		  ws.send("${errands.errandsNum}#/separator/#"+sender+"#/separator/#"+msg+"#/separator/#"+today);
			ws.send("알림:"+receiver+":${errands.errandsNum}:"+msg+":"+receiverPhoto+":"+sender);
		  $('#inputText').val('');
	      $('#inputText').focus();
		}

	 /*    if(${list == null}){
			chatLoad();
	    }
		function chatLoad(){
			location.href='${pageContext.request.contextPath}/errand/detailView?num=${errands.errandsNum}';
			document.getElementById('chatList').scrollTop = document.getElementById('chatList').scrollHeight;
			//location.href='${pageContext.request.contextPath}/errand/chatLoads?errandsNum=${errands.errandsNum}';
		}; */
		
	    /* $(document).on("click", "#send", function(){
			var msg = $('#inputText').val();
			//separator -> #/separator/#
			ws.send(${errands.errandsNum}+"#/separator/#"+sender+"#/separator/#"+msg+"#/separator/#"+today);
			$('#inputText').val('');
		}); */
	   	



			  
   	

      $("#close").click(function() {
         $("#myModal").modal('toggle');
      });	

   });
   var ct="";
	function leadingZeros(n, digits) {
       var zero = '';
       n = n.toString();

       if (n.length < digits) {
          for (var i = 0; i < digits - n.length; i++)
             zero += '0';
       }
       return zero + n;
    }
	function currentTime(){
		var d = new Date();
		ct = d.getFullYear() + "-" + leadingZeros((d.getMonth() + 1),2) + "-" + leadingZeros(d.getDate(),2) + "T" + leadingZeros(d.getHours(),2) + ":" + leadingZeros(d.getMinutes(),2);
	}
   function checkValid() {
      var form = document.f;
      var gt= "${errands.endTime}";
      gt = gt.replace(" ", "T");

      if (form.replyContent.value.trim() == "") {
         alert("댓글 내용을 입력하세요");
         return false;
      }
      if (form.arrivalTime.value.trim() == "") {
         alert("시간을 입력하세요");
         return false;
      }
      if(form.arrivalTime.value > gt){
			alert("시간이 마감 시간보다 느립니다");
			form.arrivalTime.focus();
			return false;
		}
      currentTime();
		if(form.arrivalTime.value < ct){
			alert("도착예정시간이 현재 시간보다 작습니다");
			form.arrivalTime.focus();
			return false;
		}
      ws.send("댓글:${errands.requestUser.userId}:${errands.errandsNum}번 글에 댓글이 등록되었습니다");
      return true;
   }

   function delErrands() {
      if ("${errands.requestUser.userId}" != "<security:authentication property='principal.userId'/>") {
         alert("작성자가 아닙니다");
         return;
      } else {
         location.href = "${pageContext.request.contextPath}/errand/deleteErrands?num=${errands.errandsNum}&id=<security:authentication property='principal.userId'/>";
      }
   }
   function clickUser(id, predict, content, photo, name) {
      var src = "${pageContext.request.contextPath}/users/" + id + "/" + photo;
      var imgSrc = "<img class='img-responsive' src='" + src + "' style='width: 71px; border-radius: 43px;'>";
      $('#myModal').modal('toggle');
      $("#errandsTitle").html("${errands.title}");
      $("#errandsId").html(name);
      $("#errandsPredict").html(predict);
      $("#errandsContent").html(content);
      $("#errandsUserImg").html(imgSrc);
      $("#responseIdHidden").val(id);
   }
   function startProcess() {
      var responseId = document.getElementById("responseIdHidden").value;
      location.href = "${pageContext.request.contextPath}/errand/startErrand?num=${errands.errandsNum}&responseId=" + responseId;
   }
   
</script>

</head>

<body onunload=""
	class="page-subpage page-item-detail navigation-off-canvas"
	id="page-top">

	<!-- end Navigation-->
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
						<li class="active">${errands.errandsNum}번심부름</li>
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

			<div id="map-detail"></div>
			<section class="container">
				<div class="row">
					<!--Item Detail Content-->
					<div class="col-md-12">
						<section class="block" id="main-content">
							<header class="page-title">
								<div class="title">
									<h1>${errands.title}</h1>
									<c:if
										test="${errands.arrivalTime != null and errands.finishTime != null}">
										<div class="alert alert-success" role="alert">완료된 심부름입니다
											:)</div>
									</c:if>
									<c:if
										test="${errands.arrivalTime != null and errands.finishTime == null}">
										<div class="alert alert-warning" role="alert">요청자 확인
											대기중입니다 :)</div>
									</c:if>
									<c:if
										test="${errands.arrivalTime == null and errands.finishTime != null}">
										<div class="alert alert-warning" role="alert">심부름꾼 확인
											대기중입니다 :)</div>
									</c:if>
								</div>
								<div class="info">
									<div class="type">
										<div id="hashList">
											<c:if test="${errands.hashes.size()    != 0}">
												<c:forEach items="${errands.hashes}" var="hash">
													<span class="label label-info">#${hash}</span>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</header>
							<div class="row">
								<!--Detail Sidebar-->
								<aside class="col-md-3" id="detail-sidebar">
									<!--Contact-->
									<section>
										<header>
											<h3>Contact</h3>
										</header>
										<address>
											<img
												src="${pageContext.request.contextPath}/users/${errands.requestUser.userId}/${requestSelfImg}"
												width="100%">
											<figure>
												<div class="info">
													<i class="fa fa-child"></i> <span>${errands.requestUser.name}</span>
												</div>
												<div class="info">
													<i class="fa fa-calendar"></i> <span>${errands.endTime}
														까지</span>
												</div>
												<div class="info">
													<i class="fa fa-globe"></i> <span>${errands.errandsPos.addr}</span>
												</div>
											</figure>
										</address>
									</section>
									<!--end Contact-->
									<!--Rating-->
									<section class="clearfix">
										<header class="pull-left">
											<a href="#reviews" class="roll"><h3>평점</h3></a>
										</header>
										<c:choose>
											<c:when test="${errands.requestUser.gpaList.size() == 0}">
												<figure class="rating big pull-right" data-rating="0"></figure>
											</c:when>
											<c:otherwise>
												<c:set var="reqtotal" value="0" />
												<c:set var="reqCount" value="0" />
												<c:forEach items="${errands.requestUser.gpaList}" var="gpa">
													<c:if test="${gpa.responseKindness == 0}">
														<c:set var="reqtotal"
															value="${reqtotal + gpa.requestManners}" />
														<c:set var="reqCount" value="${reqCount + 1 }" />
													</c:if>
												</c:forEach>
												<figure class="rating big pull-right"
													data-rating="${reqtotal/reqCount}"></figure>
											</c:otherwise>
										</c:choose>
									</section>
									<!--end Rating-->
									<!--Events-->
									<section>
										<header>
											<h3>해시태그 리뷰</h3>
										</header>
										<figure>
											<div class="expandable-content collapsed show-60"
												id="detail-sidebar-event">
												<div class="content">


													<c:if test="${errands.requestUser.hashList.size()== 0}">
                                          등록된 해시태그가 없습니다.
                                       </c:if>
													<c:forEach items="${errands.requestUser.hashList}"
														var="hash">
														<span class="label label-success">${hash.hashtag}</span>
													</c:forEach>

												</div>
											</div>
											<a href="#" class="show-more expand-content"
												data-expand="#detail-sidebar-event">더보기</a>
										</figure>

									</section>
									<!--end Events-->
									<c:if test="${errands.responseUser.userId == null }">
										<button type="button" onclick="delErrands()"
											class="btn btn-default">삭제하기</button>
									</c:if>
								</aside>
								<!--end Detail Sidebar-->
								<!--Content-->
								<div class="col-md-8">
									<section>
										<article class="item-gallery">


											<c:if test="${errands.errandsPhoto != 'EMPTY'}">
												<img
													src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}" />
											</c:if>



										</article>
										<!-- /.item-gallery -->
										<article class="block">
											<header>
												<h2>상세설명</h2>
												<c:if test="${errands.responseUser.userId != null}">
													<h3>
														<span class="label label-warning">심부름꾼:
															${errands.responseUser.name}</span>
													</h3>
												</c:if>
											</header>
											<p>
											<div id="comment">
												<pre>${errands.content}</pre>
											</div>
											</p>
										</article>
										<article class="block">
											<header>
												<h2>돈 내용</h2>
											</header>
											<ul class="bullets">

												<li>물품 가격 : <fmt:formatNumber
														value="${errands.productPrice}" />원
												</li>
												<li>심부름가격 : <fmt:formatNumber
														value="${errands.errandsPrice}" />원
												</li>

												<li>Total : <fmt:formatNumber
														value="${errands.errandsPrice + errands.productPrice}" />원
												</li>
											</ul>
										</article>


										<!-- /.block -->
									</section>
									<!--Reviews-->
									<c:set value="0" var="count" />
									<section class="block" id="reviews">
										<header class="clearfix">
											<h2 class="pull-left">심부름 요청 댓글</h2>
											<a href="#write-review"
												class="btn framed icon pull-right roll">댓글로 심부름 신청하기 <i
												class="fa fa-pencil"></i>
											</a>
										</header>
										<!--   <article class="clearfix overall-rating">
                                                <strong class="pull-left">Over Rating</strong>
                                                <figure class="rating big color pull-right" data-rating="4"></figure>
                                                /.rating
                                            </article>/.overall-rating -->
										<section class="reviews">
											<article class="review">
												<c:forEach items="${errands.errandsReply}" var="reply">
													<c:if test="${currentId == reply.user.userId}">
														<c:set value="${count + 1}" var="count" />
													</c:if>
													<figure class="author">
														<img
															src="${pageContext.request.contextPath}/users/${reply.user.userId}/${reply.user.selfImg}"
															alt="" style="margin-top: 15%; margin-left: 15%">

													</figure>
													<!-- /.author-->

													<div class="wrapper">
														<c:if
															test="${reply.user.userId == errands.responseUser.userId}">
															<i class="fa fa-user-o"></i>
														</c:if>
														<h5 class="imgSelect">${reply.user.name}</h5>
														<c:if test="${reply.user.auth == 2}">
															<span class="label label-danger"><i
																class="fa fa-thumbs-o-up">안전심부름꾼</i></span>
														</c:if>
														<c:forEach items="${reply.user.hashList}" var="hash"
															end="5">
															<span class="label label-primary">${hash.hashtag}</span>
														</c:forEach>
														<c:set value="0" var="restotal" />

														<c:set value="0" var="resCount" />
														<c:forEach items="${reply.user.gpaList}" var="gpa">
															<c:if test="${gpa.requestManners == 0}">
																<c:set
																	value="${restotal + (gpa.responseKindness + gpa.responseSpeed + gpa.responseAccuracy)/3}"
																	var="restotal" />
																<c:set value="${resCount + 1}" var="resCount" />
															</c:if>
														</c:forEach>
														<figure class="rating big pull-right"
															data-rating="${restotal/resCount}"></figure>
														<div class="date">
															<b>예상 도착</b><br>${reply.arrivalTime}</div>
														<c:if test="${currentId == reply.user.userId}">
															<button type="button"
																class="btn framed icon pull-right roll"
																onclick="location.href='${pageContext.request.contextPath}/errand/deleteReply?num=${reply.replyNum}&eNum=${errands.errandsNum}'">
																삭제</button>
														</c:if>
														<c:if
															test="${(currentId == errands.requestUser.userId) && (errands.responseUser.userId == null)}">
															<button type="button"
																class="btn framed icon pull-right roll"
																onclick="clickUser('${reply.user.userId}','${reply.arrivalTime}', '${reply.replyContent}', '${reply.user.selfImg}', '${reply.user.name}')">
																선택<i class="fa fa-check"></i>
															</button>
														</c:if>
														<p>${reply.replyContent}</p>

													</div>
												</c:forEach>
												<!-- /.wrapper-->
											</article>
											<!-- /.review -->

										</section>
										<!-- /.reviews-->
									</section>
									<!-- /#reviews -->
									<!--end Reviews-->
									<!--Review Form-->
									<c:if test="${errands.requestUser.userId != currentId}">
										<c:if test="${errands.responseUser.userId == null }">
											<c:if test="${count != 1}">
												<section id="write-review">
													<header>
														<h2>심부름 댓글 등록</h2>
													</header>
													<form name="f" method="post" action="insertReply"
														onsubmit="return checkValid()"
														class="background-color-white">
														<div class="row">
															<div class="col-md-8">
																<!-- /.form-group -->
																<div class="form-group">
																	<label for="form-review-message">댓글 입력</label>
																	<textarea class="form-control" id="form-review-message"
																		name="replyContent" rows="3" required=""
																		placeholder=""></textarea>
																</div>
																<div class="form-group">
																	<label for="form-review-email">도착예정시간(※마감시간을
																		확인하세요)</label> <input type="datetime-local"
																		class="form-control" name="arrivalTime" />
																</div>
																<input type="hidden" name="errands.errandsNum"
																	value="${errands.errandsNum}"> <input
																	type="hidden" name="user.userId"
																	value="<security:authentication property='principal.userId'/>">
																<input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<!-- /.form-group -->
																<div class="form-group">
																	<button type="submit" class="btn btn-default">등록하기</button>
																</div>
																<!-- /.form-group -->
															</div>


														</div>
													</form>
													<!-- /.main-search -->
												</section>
											</c:if>
										</c:if>
									</c:if>
									<!--end Review Form-->
								</div>
								<!-- /.col-md-8-->
							</div>
							<!-- /.row -->
						</section>
						<!-- /#main-content-->
					</div>
					<!-- /.col-md-8-->

				</div>
				<!-- /.row-->
			</section>
			<!-- /.container-->
		</div>

		<!-- 요청자 평가 모달 -->
		<!-- Modal -->
		<div class="modal fade" id="reqModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog" style="width: 50%">
				<form name="reqf" id="reqf"
					action="${pageContext.request.contextPath}/errand/confirmErrand"
					method="post">
					<input type="hidden" name="requestId"
						value="${errandsList[0].requestUser.userId}" />
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="fa fa-close" data-dismiss="modal"></button>
							<h4 class="modal-title" id="reqmyModalLabel">평가해주세요!</h4>
						</div>
						<div class="modal-body">
							<center>
								<img src="" name="aboutme" width="140" height="140" border="0"
									class="img-circle" id="reqmodalImg">
								<h3 id="reqmodalId">심부름꾼 이름</h3>
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
									<input type="text" id="reqevalTag" data-role="tagsinput"
										placeholder="태그로 사용자를 평가해주세요!" name="evalTag" />
								</p>
								<br>
							</center>
						</div>
						<div class="modal-footer">
							<center>
								<button type="button" class="btn btn-default"
									onclick="document.getElementById('reqf').submit()">심부름
									완료</button>
							</center>
						</div>
					</div>
					<input type="hidden" id="reqerrandsNum" name="errandsNum"
						value="${errands.errandsNum}"> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<!-- 요청자 평가 끝 -->
		<!-- 배달꾼 평가 -->
		<div class="modal fade" id="resModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog" style="width: 50%">
				<form name="resf"
					action="${pageContext.request.contextPath}/errand/confirmErrand"
					method="post">
					<input type="hidden" name="responseId" id="responseId" />
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="fa fa-close" data-dismiss="modal"></button>
							<h4 class="modal-title" id="myModalLabel">평가해주세요!</h4>
						</div>
						<div class="modal-body">
							<center>
								<img src="" name="aboutme" width="140" height="140" border="0"
									class="img-circle" id="resmodalImg">
								<h3 id="resmodalId">심부름꾼 이름</h3>
								<div class="stars" style="width: 60%">
									<table style="width: 100%;">
										<tr>
											<th>매너</th>
											<th><input class="star star-5" id="ma5" type="radio"
												name="requestManners" value="5" /> <label
												class="star star-5" for="ma5"></label> <input
												class="star star-4" id="ma4" type="radio"
												name="requestManners" value="4" /> <label
												class="star star-4" for="ma4"></label> <input
												class="star star-3" id="ma3" type="radio"
												name="requestManners" value="3" /> <label
												class="star star-3" for="ma3"></label> <input
												class="star star-2" id="ma2" type="radio"
												name="requestManners" value="2" /> <label
												class="star star-2" for="ma2"></label> <input
												class="star star-1" id="ma1" type="radio"
												name="requestManners" checked="checked" value="1" /> <label
												class="star star-1" for="ma1"></label></th>
										</tr>
									</table>

								</div>
							</center>
							<hr>
							<center>
								<p class="text-left">
									<input type="text" id="resevalTag" data-role="tagsinput"
										placeholder="태그로 사용자를 평가해주세요!" name="evalTag" />
								</p>
								<br>
							</center>
						</div>
						<div class="modal-footer">
							<center>
								<button type="button" class="btn btn-default"
									onclick="document.resf.submit()">심부름 완료</button>
							</center>
						</div>
					</div>
					<input type="hidden" id="reserrandsNum" name="errandsNum"
						value="${errands.errandsNum}"> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<!-- 배달꾼 끝 -->
		<!-- end Page Content-->
	</div>
	<!-- end Page Canvas-->
	<!--  chat 시작 -->
	<c:if
		test="${errands.responseUser.userId == currentId || 
  					errands.requestUser.userId == currentId &&
  					 errands.responseUser.userId != null}">
		<div class="row chat-window col-xs-5 col-md-3" id="chat_window_1"
			style="margin-left: 10px;">
			<div class="col-xs-12 col-md-12">
				<div class="panel panel-default" id="chat">
					<div class="panel-heading top-bar">
						<div class="col-md-8 col-xs-8">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-comment"></span> Chat -
								<c:if test="${currentId == errands.responseUser.userId}">
                        	${errands.requestUser.name}
                        </c:if>
								<c:if test="${currentId == errands.requestUser.userId}">
                        	${errands.responseUser.name}
                        </c:if>
							</h3>
						</div>
						<div class="col-md-4 col-xs-4" style="text-align: right;">
							<a href="#"><span id="minim_chat_window"
								class="glyphicon glyphicon-minus icon_minim"></span></a>
						</div>
					</div>
					<div>
						<div class="panel-body msg_container_base" id="chatList">
							<c:forEach items="${list}" var="items">
								<c:set var="msgInfo" value="${items}" />
								<c:set var="usernameNotTrim"
									value="${fn:substringBefore(msgInfo, '#/separator/#')}" />
								<c:set var="username" value="${fn:trim(usernameNotTrim)}" />
								<c:set var="startTemp" value="${usernameNotTrim}#/separator/#" />
								<c:set var="startIndex" value="${fn:length(startTemp)}" />
								<c:set var="endIndex"
									value="${fn:indexOf(msgInfo , '/#separator#/')}" />
								<c:set var="msg"
									value="${fn:substring(msgInfo, startIndex, endIndex)}" />
								<c:set var="timeStartTemp"
									value="${fn:substringBefore(msgInfo, '/#separator#/')}/#separator#/" />
								<c:set var="timeStartIndex" value="${fn:length(timeStartTemp)}" />
								<c:set var="time"
									value="${fn:substringAfter(msgInfo, '/#separator#/')}" />

								<%-- <c:set var="msg" value="${fn:substring(msgInfo, ${fn:length(msgInfo)}-${fn:length('/#separator#/06/15 10:32#startendtag#')})}"/> --%>
								<c:choose>
									<c:when test="${currentId eq username}">
										<div class="row msg_container base_sent">
											<div class="col-xs-10 col-md-10">
												<div class="messages msg_sent">
													<p>${msg}</p>
													<time datetime="2009-11-13T20:00">
														<c:if test="${currentId eq requestId}">
														${requestUserName}
													</c:if>
														<c:if test="${currentId eq responseId}">
														${responseUserName}
													</c:if>
														• ${time}
													</time>
												</div>
											</div>
											<div class="col-md-2 col-xs-2 avatar">
												<img
													src="${pageContext.request.contextPath}/users/${currentId}/${currentUser.selfImg}"
													class=" img-responsive ">
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="row msg_container base_receive">
											<div class="col-md-2 col-xs-2 avatar">
												<c:if test="${currentId eq errands.requestUser.userId}">
													<img
														src="${pageContext.request.contextPath}/users/${errands.responseUser.userId}/${responseSelfImg}"
														class=" img-responsive ">
												</c:if>
												<c:if test="${currentId eq errands.responseUser.userId}">
													<img
														src="${pageContext.request.contextPath}/users/${errands.requestUser.userId}/${requestSelfImg}"
														class=" img-responsive ">
												</c:if>
											</div>
											<div class="col-xs-10 col-md-10">
												<div class="messages msg_receive">
													<p>${msg}</p>
													<time datetime="2009-11-13T20:00">
														<c:if test="${currentId eq requestId}">
														${responseUserName}
													</c:if>
														<c:if test="${currentId eq responseId}">
														${requestUserName}
													</c:if>
														• ${time}
													</time>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<c:if
							test="${!((errands.arrivalTime != null) and (errands.finishTime != null))}">
							<div class="panel-footer">
								<div class="input-group">
									<input id="inputText" type="text"
										class="form-control input-sm chat_input"
										placeholder="Write your message here..." /> <span
										class="input-group-btn">
										<button class="btn btn-primary btn-sm" id="send">Send</button>
									</span>
								</div>
							</div>
						</c:if>

					</div>
				</div>
			</div>
		</div>
		<div class="btn-group dropup">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				<span class="glyphicon glyphicon-cog"></span> <span class="sr-only">Toggle
					Dropdown</span>
			</button>
			<c:if
				test="${!((errands.arrivalTime != null) and (errands.finishTime != null))}">
				<c:if
					test="${(errands.requestUser.userId == currentId) and (errands.finishTime == null)}">
					<ul class="dropdown-menu" role="menu">
						<li><a id="complete"><span id="chatComplete"
								class="fa fa-check"></span>심부름 완료</a></li>
					</ul>
				</c:if>
				<c:if
					test="${(errands.responseUser.userId == currentId) and (errands.arrivalTime == null)}">
					<ul class="dropdown-menu" role="menu">
						<li><a id="complete"><span id="chatComplete"
								class="fa fa-check"></span>심부름 완료</a></li>
					</ul>
				</c:if>
			</c:if>
		</div>
	</c:if>
	<!--  chat 끝 -->




	<!-- 심부름 선택시 나오는 영수증 모달창 -->

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">


			<div
				class="receipt-main col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
				<div class="row">
					<div class="receipt-header">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<div class="receipt-left" id="errandsUserImg"></div>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 text-right">
							<div class="receipt-right">
								<a href="#myModal" id="close"><i class="fa fa-close"></i></a>
								<h5 id="errandsTitle">심부름제목</h5>

								<p id="errandsAddr">
									${errands.errandsPos.addr} <i class="fa fa-location-arrow"></i>
								</p>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="receipt-header receipt-header-mid">
						<div class="col-xs-8 col-sm-8 col-md-8 text-left">
							<div class="receipt-right">
								<h5>
									회원 이름 <br> <small id="errandsId">  |   Lucky
										Number : 156</small>
								</h5>

							</div>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="receipt-left">
								<h1>명세서</h1>
							</div>
						</div>
					</div>
				</div>

				<div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>설명</th>
								<th>금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-md-9">물품 값</td>
								<td class="col-md-3"><i class="fa fa-krw"></i> <fmt:formatNumber
										value="${errands.productPrice}" /></td>
							</tr>
							<tr>
								<td class="col-md-9">심부름 삯</td>
								<td class="col-md-3"><i class="fa fa-krw"></i> <fmt:formatNumber
										value="${errands.errandsPrice}" /></td>
							</tr>
							<tr>
							<tr>

								<td class="text-right"><h2>
										<strong>Total: </strong>
									</h2></td>
								<td class="text-left text-danger"><h2>
										<strong><i class="fa fa-krw"></i> <fmt:formatNumber
												value="${errands.productPrice + errands.errandsPrice}" /></strong>
									</h2></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="row">
					<div class="receipt-header receipt-header-mid receipt-footer">
						<div class="col-xs-8 col-sm-8 col-md-8 text-left">
							<div class="receipt-right">
								<p>
									<b>도착예정시간 :</b>
								<p id="errandsPredict">2017-06-15 오후 2:21</p>
								<p>
									<b id="errandsContent"></b>
								</p>
							</div>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="receipt-left">
								<input type="hidden" id="responseIdHidden">
								<button class="btn framed icon pull-right roll"
									onclick="startProcess()">
									시작<i class="fa fa-check"></i>
								</button>
							</div>
							<div class="receipt-left"></div>
						</div>
					</div>
				</div>

			</div>
		</div>


	</div>
	<!-- 심부름 선택시 나오는 영수증 모달창 끝 -->


	<script>

    
    $(document).on('click', '.panel-heading span.icon_minim', function (e) {
        var $this = $(this);
        if (!$this.hasClass('panel-collapsed')) {
            $this.parents('.panel').find('.panel-body').slideUp();
            $this.addClass('panel-collapsed');
            $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
        } else {
            $this.parents('.panel').find('.panel-body').slideDown();
            $this.removeClass('panel-collapsed');
            $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
        }
    });
    $(document).on('focus', '.panel-footer input.chat_input', function (e) {
        var $this = $(this);
        if ($('#minim_chat_window').hasClass('panel-collapsed')) {
            $this.parents('.panel').find('.panel-body').slideDown();
            $('#minim_chat_window').removeClass('panel-collapsed');
            $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
        }
    });
    $(document).on('click', '#new_chat', function (e) {
        var size = $( ".chat-window:last-child" ).css("margin-left");
         size_total = parseInt(size) + 400;
        alert(size_total);
        var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
        clone.css("margin-left", size_total);
    });
    $(document).on('click', '.icon_close', function (e) {
        //$(this).parent().parent().parent().parent().remove();
        $( "#chat_window_1" ).remove();
    });
 // chat 부분 js 
      var mapContainer = document.getElementById('map-detail'), // 지도를 표시할 div 
         mapOption = {
            center : new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude}), // 지도의 중심좌표
            level : 3 // 지도의 확대 레벨
         };
   
      var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
   
      // 마커가 표시될 위치입니다 
      var markerPosition = new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude});
   
      // 마커를 생성합니다
      var marker = new daum.maps.Marker({
         position : markerPosition
      });
   
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
      
      var iwContent = '<div style="padding:5px;"><strong>주소</strong><br>${errands.errandsPos.addr}</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
       iwPosition = new daum.maps.LatLng(${errands.errandsPos.latitude}, ${errands.errandsPos.longitude}), //인포윈도우 표시 위치입니다
       iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
       var infowindow = new daum.maps.InfoWindow({
           position : iwPosition, 
           content : iwContent 
       });
         
       // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
       infowindow.open(map, marker); 

   </script>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>