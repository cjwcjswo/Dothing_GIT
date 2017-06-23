<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DoThing</title>
<script async
	src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<style type="text/css">
.cuadro_intro_hover {
	padding: 0px;
	position: relative;
	overflow: hidden;
	height: 200px;
}

.cuadro_intro_hover:hover .caption {
	opacity: 1;
	transform: translateY(-150px);
	-webkit-transform: translateY(-150px);
	-moz-transform: translateY(-150px);
	-ms-transform: translateY(-150px);
	-o-transform: translateY(-150px);
}

.cuadro_intro_hover img {
	z-index: 4;
}

.cuadro_intro_hover .caption {
	position: absolute;
	top: 150px;
	-webkit-transition: all 0.3s ease-in-out;
	-moz-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	-ms-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;
	width: 100%;
}

.cuadro_intro_hover .blur {
	background-color: rgba(0, 0, 0, 0.7);
	height: 300px;
	z-index: 5;
	position: absolute;
	width: 100%;
}

.cuadro_intro_hover .caption-text {
	z-index: 10;
	color: #fff;
	position: absolute;
	height: 300px;
	text-align: center;
	top: -20px;
	width: 100%;
}
</style>
</head>
<body>
	<br>
	<br>
	<!--Page Footer-->
	<footer id="page-footer">
		<div class="row">
			<div class="col-lg-12">
				<div class="col-lg-3 col-md-6">
					<div class="container">


						<div class="col-lg-3">
							<div class="cuadro_intro_hover "
								style="background-color: #cccccc;">
								<p style="text-align: center; margin-top: 20px;">
									<img src="http://placehold.it/500x330" class="img-responsive"
										alt="">
								</p>
								<div class="caption">
									<div class="blur"></div>
									<div class="caption-text">
										<h3
											style="border-top: 2px solid white; border-bottom: 2px solid white; padding: 10px;">여기에
											광고</h3>
										<p>Loren ipsum dolor si amet ipsum dolor si amet ipsum
											dolor...</p>
										<a class=" btn btn-default" href="#"><span
											class="glyphicon glyphicon-plus"> INFO</span></a>
									</div>
								</div>
							</div>

						</div>
						<div class="col-lg-3">
							<div class="cuadro_intro_hover "
								style="background-color: #cccccc;">
								<p style="text-align: center; margin-top: 20px;">
									<img src="http://placehold.it/500x330" class="img-responsive"
										alt="">
								</p>
								<div class="caption">
									<div class="blur"></div>
									<div class="caption-text">
										<h3
											style="border-top: 2px solid white; border-bottom: 2px solid white; padding: 10px;">여기에
											광고</h3>
										<p>Loren ipsum dolor si amet ipsum dolor si amet ipsum
											dolor...</p>
										<a class=" btn btn-default" href="#"><span
											class="glyphicon glyphicon-plus"> INFO</span></a>
									</div>
								</div>
							</div>

						</div>


						<div class="col-lg-3 col-md-6">
							<h3>바로가기</h3>
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/user/loginForm"><i
										class="fa fa-user"></i> 로그인</a></li>
								<li><a
									href="${pageContext.request.contextPath}/user/signIn"><i
										class="fa fa-user-plus"></i> 회원가입</a></li>
								<li><a
									href="${pageContext.request.contextPath}/errand/listing"><i
										class="fa fa-shopping-basket"></i> 심부름목록</a></li>
								<li><a
									href="${pageContext.request.contextPath}/errand/register"><i
										class="fa fa-plus-circle"></i> 심부름신청</a></li>
								<li><a href="${pageContext.request.contextPath}/board/faq"><i
										class="fa fa-question"></i> FAQ게시판</a></li>
								<li><a
									href="${pageContext.request.contextPath}/board/noticeBoardList"><i
										class="fa fa-info"></i> 공지사항</a></li>

							</ul>
						</div>

						<div class="col-lg-3 col-md-6">
							<h3>Contact:</h3>
							<p>질문이 있으시거나 피드백을 원하시나요?</p>
							<p>
								<a href="${pageContext.request.contextPath}/etc/contact"
									title="Contact me!"><i class="fa fa-envelope"></i> Contact</a>
							</p>
							<h3>Follow:</h3>


							<a href="" id="gh" target="_blank" title="Twitter"><span
								class="fa-stack fa-lg"> <i
									class="fa fa-square-o fa-stack-2x"></i> <i
									class="fa fa-twitter fa-stack-1x"></i>
							</span> Twitter</a>







						</div>
						<br />
						<div id="fb-root"></div>
						<script>(function(d, s, id) {
								var js,
									fjs = d.getElementsByTagName(s)[0];
								if (d.getElementById(id)) return;
								js = d.createElement(s);
								js.id = id;
								js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&appId=1583286941899718&version=v2.2";
								fjs.parentNode.insertBefore(js, fjs);
							}(document, 'script', 'facebook-jssdk'));
						</script>

						<div class="fb-like" data-href="" data-layout="standard"
							data-action="like" data-show-faces="true" data-share="true"></div>

						<a href="https://twitter.com/share" class="twitter-share-button"
							data-url="">Tweet</a>
						<script>!function(d, s, id) {
								var js,
									fjs = d.getElementsByTagName(s)[0],
									p = /^http:/.test(d.location) ? 'http' : 'https';
								if (!d.getElementById(id)) {
									js = d.createElement(s);
									js.id = id;
									js.src = p + '://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js, fjs);
								}
							}(document, 'script', 'twitter-wjs');
						</script>

						<div class="g-plusone" data-annotation="inline" data-width="300"
							data-href=""></div>


						<script type="text/javascript">
							(function() {
								var po = document.createElement('script');
								po.type = 'text/javascript';
								po.async = true;
								po.src = 'https://apis.google.com/js/platform.js';
								var s = document.getElementsByTagName('script')[0];
								s.parentNode.insertBefore(po, s);
							})();
						</script>
						<hr>

						<div class="footer-bottom">
							<div class="footer-bottom">
								<div class="container">
									<span class="left">(C) ThemeStarz, All rights reserved</span> <span
										class="right"> <a href="#page-top" class="to-top roll"><i
											class="fa fa-angle-up"></i></a>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</footer>
	<!--end Page Footer-->
	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.2.1.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/smoothscroll.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery.nouislider.all.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/icheck.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/maps.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/before.load.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/custom.js"></script>


</body>
</html>