<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=e110b99859ef72282b675950ede50536"></script>

<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=APIKEY&libraries=services,clusterer,drawing"></script>

<style>
@import url('https://fonts.googleapis.com/css?family=Lato:300,400');

html, body {
	ont-family: 'open sans';
	margin: 0;
	height: 100%;
	padding: 0;
}

.navbar-default {
	font-size: 1.15em;
	font-weight: 400;
	background-color: lightSkyBlue;
	padding: 10px;
	border: 0px;
	border-radius: 0px;
}

.navbar-default .navbar-nav>li>a {
	color: white;
}

.navbar-default .navbar-nav>li>a:hover {
	color: #cbf0ff;
}

.navbar-default .navbar-brand {
	color: #002433;
}

.navbar-default .navbar-brand:hover {
	color: #ffffff;
	text-shadow: 1px -1px 8px #b3e9ff;
}

.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:hover,
	.navbar-default .navbar-nav>.open>a:focus {
	background-color: #004059;
	color: white;
}

.navbar-default .navbar-toggle {
	border: none;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
	border: none;
}

.navbar-default .navbar-toggle:hover, .navbar-default .navbar-toggle:focus
	{
	background-color: #002433;
}

.navbar-default .navbar-toggle .icon-bar {
	background-color: #ffffff;
}

.dropdown-menu {
	background-color: #004059;
	color: white;
	border: 0px;
	border-radius: 2px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.dropdown-menu>li>a {
	background-color: #004059;
	color: white;
}

.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus {
	background-color: #004059;
	color: white;
}

.dropdown-menu .divider {
	height: 1px;
	margin: 9px 0;
	overflow: hidden;
	background-color: #003246;
}

.navbar-default .navbar-nav .open .dropdown-menu>li>a {
	color: #ffffff;
}

@media ( max-width : 767px) {
	.dropdown-menu>li>a {
		background-color: #006b96;
		color: #ffffff;
	}
	.dropdown-menu>li>a:hover {
		color: #ffffff;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>li>a:focus,
		.navbar-default .navbar-nav .open .dropdown-menu>li>a:hover {
		color: #ffffff;
		background-color: transparent;
	}
	.dropdown-menu .divider {
		height: 1px;
		margin: 9px 0;
		overflow: hidden;
		background-color: #005577;
	}
	
} 

/* END Media Query */
@import
	url("http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,400italic")
	;

@import
	url("//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css")
	;

.event-list {
	list-style: none;
	font-family: 'Lato', sans-serif;
	margin: 0px;
	padding: 0px;
}

.event-list>li {
	background-color: rgb(255, 255, 255);
	box-shadow: 0px 0px 5px rgb(51, 51, 51);
	box-shadow: 0px 0px 5px rgba(51, 51, 51, 0.7);
	padding: 0px;
	margin: 0px 0px 20px;
}

.event-list>li>time {
	display: inline-block;
	width: 100%;
	color: rgb(255, 255, 255);
	background-color: rgb(197, 44, 102);
	padding: 5px;
	text-align: center;
	text-transform: uppercase;
}

.event-list>li:nth-child(even)>time {
	background-color: rgb(165, 82, 167);
}

.event-list>li>time>span {
	display: none;
}

.event-list>li>time>.day {
	display: block;
	font-size: 56pt;
	font-weight: 100;
	line-height: 1;
}

.event-list>li time>.month {
	display: block;
	font-size: 24pt;
	font-weight: 900;
	line-height: 1;
}

.event-list>li>img {
	width: 100%;
}

.event-list>li>.info {
	padding-top: 5px;
	text-align: center;
}

.event-list>li>.info>.title {
	font-size: 17pt;
	font-weight: 700;
	margin: 0px;
}

.event-list>li>.info>.desc {
	font-size: 13pt;
	font-weight: 300;
	margin: 0px;
}

.event-list>li>.info>ul, .event-list>li>.social>ul {
	display: table;
	list-style: none;
	margin: 10px 0px 0px;
	padding: 0px;
	width: 100%;
	text-align: center;
}

.event-list>li>.social>ul {
	margin: 0px;
}

.event-list>li>.info>ul>li, .event-list>li>.social>ul>li {
	display: table-cell;
	cursor: pointer;
	color: rgb(30, 30, 30);
	font-size: 11pt;
	font-weight: 300;
	padding: 3px 0px;
}

.event-list>li>.info>ul>li>a {
	display: block;
	width: 100%;
	color: rgb(30, 30, 30);
	text-decoration: none;
}

.event-list>li>.social>ul>li {
	padding: 0px;
}

.event-list>li>.social>ul>li>a {
	padding: 3px 0px;
}

.event-list>li>.info>ul>li:hover, .event-list>li>.social>ul>li:hover {
	color: rgb(30, 30, 30);
	background-color: rgb(200, 200, 200);
}

.facebook a, .twitter a, .google-plus a {
	display: block;
	width: 100%;
	color: rgb(75, 110, 168) !important;
}

.twitter a {
	color: rgb(79, 213, 248) !important;
}

.google-plus a {
	color: rgb(221, 75, 57) !important;
}

.facebook:hover a {
	color: rgb(255, 255, 255) !important;
	background-color: rgb(75, 110, 168) !important;
}

.twitter:hover a {
	color: rgb(255, 255, 255) !important;
	background-color: rgb(79, 213, 248) !important;
}

.google-plus:hover a {
	color: rgb(255, 255, 255) !important;
	background-color: rgb(221, 75, 57) !important;
}

@media ( min-width : 768px) {
	.event-list>li {
		position: relative;
		display: block;
		width: 100%;
		height: 120px;
		padding: 0px;
	}
	.event-list>li>time, .event-list>li>img {
		display: inline-block;
	}
	.event-list>li>time, .event-list>li>img {
		width: 120px;
		float: left;
	}
	.event-list>li>.info {
		background-color: rgb(245, 245, 245);
		overflow: hidden;
	}
	.event-list>li>time, .event-list>li>img {
		width: 120px;
		height: 120px;
		padding: 0px;
		margin: 0px;
	}
	.event-list>li>.info {
		position: relative;
		height: 120px;
		text-align: left;
		padding-right: 40px;
	}
	.event-list>li>.info>.title, .event-list>li>.info>.desc {
		padding: 0px 10px;
	}
	.event-list>li>.info>ul {
		position: absolute;
		left: 0px;
		bottom: 0px;
	}
	.event-list>li>.social {
		position: absolute;
		top: 0px;
		right: 0px;
		display: block;
		width: 40px;
	}
	.event-list>li>.social>ul {
		border-left: 1px solid rgb(230, 230, 230);
	}
	.event-list>li>.social>ul>li {
		display: block;
		padding: 0px;
	}
	.event-list>li>.social>ul>li>a {
		display: block;
		width: 40px;
		padding: 10px 0px 9px;
	}
}

.panel-google-plus {
	position: relative;
	border-radius: 0px;
	border: 1px solid rgb(216, 216, 216);
	font-family: 'Roboto', sans-serif;
}

.panel-google-plus>.dropdown {
	position: absolute;
	top: 5px;
	right: 15px;
}

.panel-google-plus>.dropdown>span>span {
	font-size: 10px;
}

.panel-google-plus>.dropdown>.dropdown-menu {
	left: initial;
	right: 0px;
	border-radius: 2px;
}

.panel-google-plus>.panel-google-plus-tags {
	position: absolute;
	top: 35px;
	right: -3px;
}

.panel-google-plus>.panel-google-plus-tags>ul {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.panel-google-plus>.panel-google-plus-tags>ul:hover {
	box-shadow: 0px 0px 3px rgb(0, 0, 0);
	box-shadow: 0px 0px 3px rgba(0, 0, 0, 0.25);
}

.panel-google-plus>.panel-google-plus-tags>ul>li {
	display: block;
	right: 0px;
	width: 0px;
	padding: 5px 0px 5px 0px;
	background-color: rgb(245, 245, 245);
	font-size: 12px;
	overflow: hidden;
}

.panel-google-plus>.panel-google-plus-tags>ul>li::after {
	content: "";
	position: absolute;
	top: 0px;
	right: 0px;
	height: 100%;
	border-right: 3px solid rgb(66, 127, 237);
}

.panel-google-plus>.panel-google-plus-tags>ul:hover>li,
	.panel-google-plus>.panel-google-plus-tags>ul>li:first-child {
	padding: 5px 15px 5px 10px;
	width: auto;
	cursor: pointer;
	margin-left: auto;
}

.panel-google-plus>.panel-google-plus-tags>ul:hover>li {
	background-color: rgb(255, 255, 255);
}

.panel-google-plus>.panel-google-plus-tags>ul>li:hover {
	background-color: rgb(66, 127, 237);
	color: rgb(255, 255, 255);
}

.panel-google-plus>.panel-heading, .panel-google-plus>.panel-footer {
	background-color: rgb(255, 255, 255);
	border-width: 0px;
}

.panel-google-plus>.panel-heading {
	margin-top: 20px;
	padding-bottom: 5px;
}

.panel-google-plus>.panel-heading>img {
	margin-right: 15px;
}

.panel-google-plus>.panel-heading>h3 {
	margin: 0px;
	font-size: 14px;
	font-weight: 700;
}

.panel-google-plus>.panel-heading>h5 {
	color: rgb(153, 153, 153);
	font-size: 12px;
	font-weight: 400;
}

.panel-google-plus>.panel-body {
	padding-top: 5px;
	font-size: 13px;
}

.panel-google-plus>.panel-body>.panel-google-plus-image {
	display: block;
	text-align: center;
	background-color: rgb(245, 245, 245);
	border: 1px solid rgb(217, 217, 217);
}

.panel-google-plus>.panel-body>.panel-google-plus-image>img {
	max-width: 100%;
}

.panel-google-plus>.panel-footer {
	font-size: 14px;
	font-weight: 700;
	min-height: 54px;
}

.panel-google-plus>.panel-footer>.btn {
	float: left;
	margin-right: 8px;
}

.panel-google-plus>.panel-footer>.input-placeholder {
	display: block;
	margin-left: 98px;
	color: rgb(153, 153, 153);
	font-size: 12px;
	font-weight: 400;
	padding: 8px 6px 7px;
	border: 1px solid rgb(217, 217, 217);
	border-radius: 2px;
	box-shadow: rgba(0, 0, 0, 0.0470588) 0px 1px 0px 0px;
}

.panel-google-plus.panel-google-plus-show-comment>.panel-footer>.input-placeholder
	{
	display: none;
}

.panel-google-plus>.panel-google-plus-comment {
	display: none;
	padding: 10px 20px 15px;
	border-top: 1px solid rgb(229, 229, 229);
	background-color: rgb(245, 245, 245);
}

.panel-google-plus.panel-google-plus-show-comment>.panel-google-plus-comment
	{
	display: block;
}
/*.panel-google-plus > .panel-google-plus-comment > img {
    float: left;   
}*/
.panel-google-plus>.panel-google-plus-comment>.panel-google-plus-textarea
	{
	float: right;
	width: calc(100% - 56px);
}

.panel-google-plus>.panel-google-plus-comment>.panel-google-plus-textarea>textarea
	{
	display: block;
	/*margin-left: 60px;
    width: calc(100% - 56px);*/
	width: 100%;
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(217, 217, 217);
	box-shadow: rgba(0, 0, 0, 0.0470588) 0px 1px 0px 0px;
	resize: vertical;
}

.panel-google-plus>.panel-google-plus-comment>.panel-google-plus-textarea>.btn
	{
	margin-top: 10px;
	margin-right: 8px;
	width: 100%;
}

@media ( min-width : 992px) {
	.panel-google-plus>.panel-google-plus-comment>.panel-google-plus-textarea>.btn
		{
		width: auto;
	}
}

.panel-google-plus .btn {
	border-radius: 3px;
}

.panel-google-plus .btn-default {
	border: 1px solid rgb(217, 217, 217);
	box-shadow: rgba(0, 0, 0, 0.0470588) 0px 1px 0px 0px;
}

.panel-google-plus .btn-default:hover, .panel-google-plus .btn-default:focus,
	.panel-google-plus .btn-default:active {
	background-color: rgb(255, 255, 255);
	border-color: rgb(0, 0, 0);
}

/*  */
.project {
	margin-bottom: 30px;
	vertical-align: top;
	margin-right: 30px;
	float: left;
	cursor: pointer;
	width: 100%;
}

.project figure {
	position: relative;
	display: inline-block;
}

.project figure img {
	width: 100%;
}

.btn-warning bnt-action {
	margin: 0% 0% auto;
}

figcaption .project-details {
	display: block;
	font-size: 8px;
	/*line-height: 33px;*/
	color: #000;
	/*height: 27px;*/
	width: 100%;
	margin: 0 auto 5px auto;
	/*margin-bottom: 5px;*/
	overflow: hidden;
}

.project figure:hover figcaption {
	background: #d81e05;
}

.project figure:hover figcaption .project-details {
	color: #fff;
}

figcaption .project-price {
	position: absolute;
	right: 15px;
	top: 12px;
	font-size: 12px;
	text-align: right;
	margin-top: 8px;
	letter-spacing: -1px;
	-webkit-font-smoothing: antialiased;
}

figcaption .project-creator {
	font-size: 12px;
	color: #545454;
	display: block;
}

figcaption .project-creator {
	font-size: 12px;
	color: #545454;
	display: block;
}

.project figure .actions button {
	padding: 13px 20px;
	font-size: 12px;
	top: 32%;
	position: absolute;
	left: 50%;
	width: 90%;
	margin-left: -45%;
	line-height: 18px;
	letter-spacing: 1px;
}

.project figure:hover .actions {
	background-color: rgba(115, 15, 2, .8);
	top: 40%;
	font-size: 2em;
	font-weight: 700;
}

.project figure .actions {
	display: block;
	position: absolute;
	bottom: 0px;
	top: 70%;
	left: 0;
	right: 0;
	z-index: 1;
	opacity: 1;
	background-color: rgba(29, 29, 29, .5);
	-ms-transition: all .2s ease-out;
	-webkit-transition: all .2s ease-out;
	-moz-transition: all .2s ease-out;
	-o-transition: all .2s ease-out;
	transition: all .2s ease-out;
	color: white;
	font-size: 1.5em;
	padding: 2%;
	font-weight: bold;
	text-align: center;
}

@media ( min-width : 992px) {
	.project figure .actions {
		top: 60%;
		left: 0;
		right: 0;
		font-size: 1.5em;
		padding: 2%;
	}
	.project figure:hover .actions {
		top: 50%;
		font-size: 1.8em;
		padding-top: 0%;
	}
	@media ( min-width : 1200px) {
		.project figure .actions {
			top: 70%;
			left: 0;
			right: 0;
			font-size: 1em;
			padding: 2%;
		}
		.project figure:hover .actions {
			top: 50%;
			font-size: 1.3em;
		}
	}
}

#content {
	width: 100%;
	height: 100%;
}

#map {
	margin-left: 2.5%;
	position: fixed;
	height: 100%;
	width: 45%;
	z-index: 1;
}

.col-md-6 {
	width: 100%;
}

.glyphicon {
	margin-right: 5px;
}

.section-box h2 {
	margin-top: 0px;
}

.section-box h2 a {
	font-size: 15px;
}

.glyphicon-heart {
	color: #e74c3c;
}

.glyphicon-comment {
	color: #27ae60;
}

.separator {
	padding-right: 5px;
	padding-left: 5px;
}

.section-box hr {
	margin-top: 0;
	margin-bottom: 5px;
	border: 0;
	border-top: 1px solid rgb(199, 199, 199);
}

#tb {
	z-index: -1;
	width: 100%;
	margin-left: 100%;
}
</style>


</head>
<body>

	<div style="height: 15%;"></div>

	<div class="container" id="content">
		<div class="row" id="">
			<!--  필터  -->

		</div>
		<div class="row" style="width: 100%; height: 100%;">
			<div class="col-xs-4" id="map">
				<!-- 지도 삽입 부분 -->

				<iframe width="100%" height="500px" frameborder="0" scrolling="no"
					marginheight="0" marginwidth="0"
					src="https://maps.google.co.uk/maps?f=q&source=s_q&hl=en&geocode=&q=15+Springfield+Way,+Hythe,+CT21+5SH&aq=t&sll=52.8382,-2.327815&sspn=8.047465,13.666992&ie=UTF8&hq=&hnear=15+Springfield+Way,+Hythe+CT21+5SH,+United+Kingdom&t=m&z=14&ll=51.077429,1.121722&output=embed"></iframe>


			</div>
			<div class="col-xs-6" style="width: 50%">
				<!-- 심부름 리스트  -->
				<table id="tb">
					<c:forEach items="${errandsList}" var="errands">
						<tr>
							<th>
								<div class="col-md-6">
									<div class="well well-sm">
										<div class="row">
											<div class="col-xs-3 col-md-3 text-center">
												<img
													src="${pageContext.request.contextPath}/errands/${errands.errandsPhoto}"
													alt="bootsnipp" class="img-rounded img-responsive" />
											</div>
											<div class="col-xs-9 col-md-9 section-box">
												<h2>
													${errands.title}
												</h2>
												<p>${errands.content}<br>
												마감일시 : ${errands.endTime}</p>
												<hr />
												<div class="row rating-desc">
													<div class="col-md-12">
														<span class="glyphicon glyphicon-heart"></span><span
															class="glyphicon glyphicon-heart"> </span><span
															class="glyphicon glyphicon-heart"></span><span
															class="glyphicon glyphicon-heart"> </span><span
															class="glyphicon glyphicon-heart"></span>(36)<span
															class="separator">|</span> <span
															class="glyphicon glyphicon-comment"></span>(5 Comments)
															 
															<a  class="btn btn-danger" href="orderForm">신청하기</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</th>
						</tr>
					</c:forEach>
					<tr>
						<th>
							<ul class="pagination" style="margin-left: 35%;">
								<li class="disabled"><a href="#">«</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">»</a></li>
							</ul>
						</th>
					</tr>
				</table>




			</div>




		</div>

	</div>


</body>
</html>
