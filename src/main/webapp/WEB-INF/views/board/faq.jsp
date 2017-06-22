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
										<!-- /.content-->
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
								<!-- /.faq-single-->
								
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