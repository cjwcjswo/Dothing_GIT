<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>1:1게시판</title>
</head>
<body onunload="" class="page-subpage page-faq navigation-off-canvas"
	id="page-top">
	<!-- Outer Wrapper-->
	<div id="outer-wrapper">
		<!-- Inner Wrapper -->
		<div id="inner-wrapper">

			<!-- Page Canvas-->
			<div id="page-canvas">


<<<<<<< HEAD
						<ol class="breadcrumb">
							<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i></a></li>
							<li>FAQ게시판</li>
						</ol>
						<!-- /.breadcrumb-->
					</div>
					<!-- /.container-->
				</div>
				<!-- /.breadcrumb-wrapper--> </section>
				<!--end Sub Header-->
=======


>>>>>>> d8486d1f3c85fdbefca4283b46e0fff3a3eb2d55

				<!--Page Content-->
				<div id="page-content">
					<section class="container">
						<div class="row">
							<!--Content-->
							<div class="col-md-9">
								<header>
									<h1 class="page-title">FAQ</h1>
								</header>
								<section class="faq-form">
									<figure class="clearfix">
										<i class="fa fa-question"></i>
										<div class="wrapper">
											<div class="pull-left">
												<strong>궁금하신 점을 못찾으셨나요?</strong>
												<h3>1:1 문의 게시판에 올리기</h3>
											</div>
											<a href="#form-faq" class="btn btn-default pull-right"
												data-toggle="collapse" aria-expanded="false"
												aria-controls="form-faq">질문하러 가기</a>
										</div>
									</figure>
									<div class="collapse" id="form-faq">
										<div class="">
											<form name="writeForm" role="form"
												action="${pageContext.request.contextPath}/board/insert"
												onSubmit='return checkValid()' method="post">
												<!-- <div class="form-group">
											<label for="faq-form-email">id</label> <input type="text"
												class="form-control" id="faq-form-email" required=""
												disabled>
										</div> -->
												<!-- /.form-group -->
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}">
												<div class="form-group">
													<label for="faq-form-email">제목</label> <input type="text"
														class="form-control" name="boardTitle" id="faq-form-email"
														required="">
												</div>

												<div class="form-group">
													<label for="faq-form-question">질문</label>
													<textarea class="form-control" id="faq-form-question"
														name="boardContent" rows="3" required=""></textarea>
												</div>
												<!-- /.form-group -->
												<div class="form-group">
													<button type="submit" class="btn btn-default">제출하기</button>
												</div>
												<!-- /.form-group -->
											</form>
											<!-- /form-->
										</div>
<<<<<<< HEAD
										<!-- /.form-group -->
									</form>
									<!-- /form-->
								</div>
								<!-- /.content-->
							</div>
							<!-- /#form-faq--> </section>
							<!-- /#faq-form-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>DOTHING은 어떤 서비스인가요?</h4>
								<div class="answer">
									<figure>사람과 사람을 연결해주는 중계플랫폼입니다.</figure>
									<p>도움이 필요한 사람, 도움을 주고 싶은 사람을 연결해주는 중계플랫폼입니다.
									다른 사람의 도움이 필요한데 주변에 요청할 사람이 없던 경험이 있습니다.
									저희 DOTHING은 사람과 사람을 연결해 도움을 주고 받을 수 있게 해줍니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>포인트를 환불 받을 수 있나요?</h4>
								<div class="answer">
									<figure>예 환불 받을 수 있습니다.</figure>
									<p>고객님의 단순 변심으로 인한 포인트 환불은 구매 후 7일 이내 요청해주시면 환불
									할 수 있습니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>어떻게 심부름꾼을 믿어요?</h4>
								<div class="answer">
									<figure>항상 신뢰를 주는 DOTHING입니다.</figure>
									<p>저희 DOTHING이 인증하는 안전 심부름꾼은 회사 규정에 의거한
									심부름꾼입니다. 또한 저희의 운영방침에 의해서 심부름꾼의 사기같은 경우에는
									100프로 포인트 환불이 가능합니다. 또한 법적 책임을 물을 것 입니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>이용 요금이 궁금합니다.</h4>
								<div class="answer">
									<figure>심부름 값은 심부름 요청자가 정하는 방식입니다.</figure>
									<p>심부름 요청자가 심부름 수행비를 등록해놓으면 심부름꾼들이 댓글과 채팅을 통하여
									   심부름 값을 조정할 수 있습니다.
									</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
							<article class="faq-single"> <i
								class="fa fa-question-circle"></i>
							<div class="wrapper">
								<h4>심부름꾼은 누구나 가능한가요?</h4>
								<div class="answer">
									<figure>예 누구나 도움을 줄 수 있는 방식입니다.</figure>
									<p>저희의 서비스는 사람과 사람 누구나 도움을 받거나 줄 수 있는 중계플랫폼입니다.</p>
								</div>
							</div>
							</article>
							<!-- /.faq-single-->
						</div>
						<!--Sidebar-->
						<div class="col-md-3">
							<aside id="sidebar"> <section> <header>
							<h2>New Places</h2>
							</header> <a href="item-detail.html" class="item-horizontal small">
								<h3>Cash Cow Restaurante</h3> <figure>63 Birch Street</figure>
								<div class="wrapper">
									<div class="image">
										<img src="assets/img/items/1.jpg" alt="">
=======
										<!-- /.content-->
>>>>>>> d8486d1f3c85fdbefca4283b46e0fff3a3eb2d55
									</div>
									<!-- /#form-faq-->
								</section>
								<!-- /#faq-form-->
								<article class="faq-single">
									<i class="fa fa-question-circle"></i>
									<div class="wrapper">
										<h4>어떻게 심부름꾼을 믿어요?</h4>
										<div class="answer">
											<figure>항상 신뢰를 주는 DOTHING입니다.
											</figure>
											<p>안전심부름꾼은 신원이 확인된 사람이라면 누구나 안전 심부름꾼으로 일할 수 있으며, 자신이 원하는
												시간과 장소에서 원하는 일만 택하여 일할 수 있습니다. 고객을 도와드리며 보람도 느끼고 추가적으로 수익이
												생길 수 있는 직업! DoThing에 가입하시고 안전 심부름꾼으로 인증 받아보세요~</p>

											<p>또한 심부름요청자는 각 심부름꾼의 해시태그 리뷰와 평점을 참고하여 본인이 가장 신뢰할 만 한
												심부름꾼을 선택할 수 있습니다. 심부름꾼 역시 요청자를 해시 태그 리뷰를 하고 평점을 줄 수 있으니 상호간의
												친절과 신뢰가 필수 입니다.</p>
										</div>
									</div>
								</article>
								<!-- /.faq-single-->
								<article class="faq-single">
									<i class="fa fa-question-circle"></i>
									<div class="wrapper">
										<h4>심부름꾼은 안전한가요?</h4>
										<div class="answer">
											<figure>DoThing 항상 고객의 안전을 생각합니다.
											</figure>
											<p>DoThing와 고객 간의 상호 평가와 신고를 관리자와의 문의를 통해 문제가 있는 회원들은 서비스
												이용에 제한을 받게됩니다. 고객은 심부름 댓글 신청들 중 최종 선택을 하기에 DoThing의 수행이력,
												평점, 금액 등을 고려해서 더 믿을 만한 심부름꾼에게 요청해주시면 됩니다.</p>
										</div>
									</div>
								</article>
								<!-- /.faq-single-->
								<article class="faq-single">
									<i class="fa fa-question-circle"></i>
									<div class="wrapper">
										<h4>결제를 완료했는데 심부름꾼이 연락두절이면 어떻게 되나요?</h4>
										<div class="answer">
											<figure>전액 환불입니다.
											</figure>
											<p>DoThing이 심부름 시작 시 받은 포인트를 환불해주고 심부름꾼은 포인트를 받지 못합니다.</p>
										</div>
									</div>
								</article>
								<!-- /.faq-single-->
								<article class="faq-single">
									<i class="fa fa-question-circle"></i>
									<div class="wrapper">
										<h4>심부름꾼이 음식을 사왔는데 고객이 연락두절이면?</h4>
										<div class="answer">
											<figure>Answer
											</figure>
											<p>심부름 완료를 하지 않으면 음식을 받을 수 없다. 
											먼저 계산된 포인트는 심부름꾼에게 지불하게 됩니다.</p>
										</div>
									</div>
								</article>
								
							</div>

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