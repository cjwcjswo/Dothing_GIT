<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
<script
	src="${pageContext.request.contextPath}/resources/js/detailView.js"></script>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=900302937c725fa5d96ac225cbc2db10&libraries=services"></script>

<style>
@import url('https://fonts.googleapis.com/css?family=Lato:300,400');

html, body {
	font-family: 'open sans';
	margin: 0;
	height: 100%;
	padding: 0;
}

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
	height: 100%;
	width: 45%;
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

img {
	max-width: 100%;
}

.preview {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
}

@media screen and (max-width: 996px) {
	.preview {
		margin-bottom: 20px;
	}
}

.preview-pic {
	-webkit-box-flex: 1;
	-webkit-flex-grow: 1;
	-ms-flex-positive: 1;
	flex-grow: 1;
}

.tab-content {
	overflow: hidden;
}

.tab-content img {
	width: 100%;
	-webkit-animation-name: opacity;
	animation-name: opacity;
	-webkit-animation-duration: .3s;
	animation-duration: .3s;
}

.card {
	background: #eee;
	padding: 3em;
	line-height: 1.5em;
}

@media screen and (min-width: 997px) {
	.wrapper {
		display: -webkit-box;
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
	}
}

.details {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
}

.colors {
	-webkit-box-flex: 1;
	-webkit-flex-grow: 1;
	-ms-flex-positive: 1;
	flex-grow: 1;
}

.product-title, .price, .sizes, .colors {
	text-transform: UPPERCASE;
	font-weight: bold;
}

.checked, .price span {
	color: #ff9f1a;
}

.product-title, .rating, .product-description, .price, .vote, .sizes {
	margin-bottom: 15px;
}

.product-title {
	margin-top: 0;
}

.size {
	margin-right: 10px;
}

.size:first-of-type {
	margin-left: 40px;
}

.color {
	display: inline-block;
	vertical-align: middle;
	margin-right: 10px;
	height: 2em;
	width: 2em;
	border-radius: 2px;
}

.color:first-of-type {
	margin-left: 20px;
}

.add-to-cart, .like {
	background: #ff9f1a;
	padding: 1.2em 1.5em;
	border: none;
	text-transform: UPPERCASE;
	font-weight: bold;
	color: #fff;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
}

.add-to-cart:hover, .like:hover {
	background: #b36800;
	color: #fff;
}

.not-available {
	text-align: center;
	line-height: 2em;
}

.not-available:before {
	font-family: fontawesome;
	content: "\f00d";
	color: #fff;
}

.orange {
	background: #ff9f1a;
}

.green {
	background: #85ad00;
}

.blue {
	background: #0076ad;
}

.tooltip-inner {
	padding: 1.3em;
}

@
-webkit-keyframes opacity { 0% {
	opacity: 0;
	-webkit-transform: scale(3);
	transform: scale(3);
}

/*# sourceMappingURL=style.css.map */

/*
	댓글

*/
.animated {
	-webkit-transition: height 0.2s;
	-moz-transition: height 0.2s;
	transition: height 0.2s;
}

/* 댓글 2 */
.widget .panel-body {
	padding: 0px;
}

.widget .list-group {
	margin-bottom: 0;
}

.widget .panel-title {
	display: inline
}

.widget .label-info {
	float: right;
}

.widget li.list-group-item {
	border-radius: 0;
	border: 0;
	border-top: 1px solid #ddd;
}

.widget li.list-group-item:hover {
	background-color: rgba(86, 61, 124, .1);
}

.widget .mic-info {
	color: #666666;
	font-size: 11px;
}

.widget .action {
	margin-top: 5px;
}

.widget .comment-text {
	font-size: 12px;
}

.widget .btn-block {
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
}
</style>
<script>
	$(function() {
		function leadingZeros(n, digits) {
			var zero = '';
			n = n.toString();

			if (n.length < digits) {
				for (var i = 0; i < digits - n.length; i++)
					zero += '0';
			}
			return zero + n;
		}

		$(document).on("click", "button[data-target='#myModal']", function() {
			var d = new Date();
			$("#date").html(d.getFullYear() + "-" + leadingZeros((d.getMonth() + 1), 2) + "-" + leadingZeros(d.getDate(), 2) + " "
				+ d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
			$("#sims").html("<h2>심부름꾼: " + $(this).attr("id") + "</h2>");
		})
	});
	function checkValid() {
		var form = document.f;
		if (form.replyContent.value.trim() == "") {
			alert("댓글 내용을 입력하세요");
			return false;
		}
		if (form.arrivalTime.value.trim() == "") {
			alert("시간을 입력하세요");
			return false;
		}
		return true;
	}
	function delErrands(){
		if("${errands.requestUser.userId}" != "<security:authentication property='principal.userId'/>"){
			alert("작성자가 아닙니다");
			return;
		}else{
			location.href="${pageContext.request.contextPath}/errand/deleteErrands?num=${errands.errandsNum}&id=<security:authentication property='principal.userId'/>";
		}
	}
	function replaceHash(){		
		var hash = /#\S\S*/gi;
		var comment = document.getElementById("comment").innerHTML;
		var i = 0;
		var reComment = comment.replace(hash, "<a>$&</a>");
		document.getElementById("comment").innerHTML = reComment;
	}

	
</script>

</head>
<body onload="replaceHash()">

	<div class="container" id="content">
		<div class="row" id="">
			<!--  필터  -->

		</div>
		<div class="row" style="width: 100%; height: 100%;">
			<div class="col-xs-4" id="map">
				<!-- 지도 삽입 부분 -->

				<div id="daumMap" style="width: 100%; height: 500px; z-index:0"></div>

			</div>
			<div class="col-xs-6">
				<div class="card" style="width: 100%;">
					<div class="container-fluid" style="width: 100%;">
						<div class="wrapper row" style="width: 100%;">
							<div class="container-fluid" style="width: 100%;">
								<a onclick="delErrands();"><span class="glyphicon glyphicon-trash"
									style="margin: auto"></span>심부름 삭제하기</a>
								<h2 class="product-title">${errands.title}</h2>
								<table>
									<tr>
										<td style="width: 70%">
											<div class="rating">

												<span class="review-no"><a>${errands.errandsReply.size()}명
														신청 중</a></span>
											</div>
											<h3 class="product-title">설명</h3>
											<div class="product-description" id="comment">${errands.content}</div>
											<h4 class="price">
												물건 값: <span>${errands.productPrice}</span>
											</h4>
											<h4 class="price">
												심부름 값: <span>${errands.errandsPrice}</span>
											</h4>
										</td>
										<td style="width: 30%"><c:if
												test="${errands.errandsPhoto != null}">
												<img
													src="${pageContext.request.contextPath}/errands/${errands.errandsNum}/${errands.errandsPhoto}"
													alt="bootsnipp" class="img-rounded img-responsive" />
											</c:if> <c:if test="${errands.errandsPhoto == null}">
												<img
													src="${pageContext.request.contextPath}/resources/img/errands/img.png"
													alt="bootsnipp" class="img-rounded img-responsive" />
											</c:if></td>
									</tr>
								</table>


								<div class="row" style="width: 110%;">
									<div class="col-md-6" style="width: 100%;">
										<div class="container-fluid" style="width: 100%">
											<div class="text-right">
												<a class="btn btn-success btn-green" href="#reviews-anchor"
													id="open-review-box">제가 할래요!</a>
											</div>

											<div class="row" id="post-review-box" style="display: none;">
												<div class="col-md-12">
													<input id="ratings-hidden" name="rating" type="hidden">
													<form name="f" method="post" action="insertReply" onsubmit="return checkValid()">
														<textarea class="form-control animated" cols="50"
															id="new-review" name="replyContent"
															placeholder="댓글을 입력하세요" rows="5"></textarea>
														<br>
														<div class="col-xs-6 alert alert-info">
															<strong>Info!</strong> 도착예정 시간 입력!
														</div>
														<input type="datetime-local" class="form-control"
															name="arrivalTime" /> <br>

														<div class="text-right">

															<a class="btn btn-danger btn-sm" href="#"
																id="close-review-box"
																style="display: none; margin-right: 10px;"> <span
																class="glyphicon glyphicon-remove"></span>취소하기
															</a> <input type="hidden" name="errands.errandsNum"
																value="${errands.errandsNum}"> <input
																type="hidden" name="user.userId" value="<security:authentication property='principal.userId'/>">
															<input type="hidden" name="${_csrf.parameterName}"
																value="${_csrf.token}" />
															<button class="btn btn-success btn-lg" type="submit">등록하기</button>
														</div>
													</form>
												</div>
											</div>

											<hr>
											<c:forEach items="${errands.errandsReply}" var="reply">
												<div class="row">


													<div class="panel-body">
														<ul class="list-group">
															<li class="list-group-item">
																<div class="row">

																	<div class="col-xs-3 col-md-2" style="width: 30%">
																		<img src="http://placehold.it/80"
																			class="img-circle img-responsive" alt="" width="80px"
																			height="80px" />
																	</div>
																	<div class="col-xs-9 col-md-10" style="width: 70%">
																		<div>
																			<div class="mic-info">
																				By: <a href="#">${reply.user.userId}</a><br>
																				${reply.replyDate}<br> 예상도착시간:
																				${reply.arrivalTime}
																			</div>
																		</div>
																		<div class="comment-text">
																			${reply.replyContent}
																			<button class="btn btn-info btn-sm" type="button"
																				data-toggle="modal" data-target="#myModal"
																				id="${reply.user.userId}">이놈</button>

																		</div>
																	</div>
																</div>
															</li>


														</ul>

													</div>

												</div>
											</c:forEach>



										</div>
									</div>



								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>




	</div>
	<!-- modal -->
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div
			class="modal-dialog well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">

			<!-- Modal content-->


			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6">
					<address>
						<strong>주소</strong> <br> ${errands.errandsPos.addr}
					</address>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6 text-right">
					<p>
						<em id="date"></em>
					</p>
				</div>
			</div>
			<div class="row">
				<div class="text-center">
					<h1>심부름 명세서</h1>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th class="text-center"></th>
							<th class="text-center">Total</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="col-md-9"><em>물건 값</em>
								</h4></td>
							<td class="col-md-1" style="text-align: center"></td>
							<td class="col-md-1 text-center"></td>
							<td class="col-md-1 text-center">${errands.productPrice}원</td>
						</tr>
						<tr>
							<td class="col-md-9"><em>심부름 값</em>
								</h4></td>
							<td class="col-md-1" style="text-align: center"></td>
							<td class="col-md-1 text-center"></td>
							<td class="col-md-1 text-center">${errands.errandsPrice}원</td>
						</tr>
						<tr>
							<td> </td>
							<td> </td>
							<td class="text-right">
								<p>
									<strong>Subtotal: </strong>
								</p>
								<p>
									<strong>Tax: </strong>
								</p>
							</td>
							<td class="text-center">
								<p>
									<strong>${errands.errandsPrice + errands.productPrice}원</strong>
								</p>
								<p>
									<strong>${(errands.errandsPrice + errands.productPrice) * 0.1}원</strong>
								</p>
							</td>
						</tr>
						<tr>
							<td> </td>
							<td> </td>
							<td class="text-right"><h4>
									<strong>Total: </strong>
								</h4></td>
							<td class="text-center text-danger"><h4>
									<strong>${(errands.errandsPrice + errands.productPrice) * 0.9}원</strong>
								</h4></td>
						</tr>
					</tbody>
				</table>
				<div id="sims"></div>
				<a class="btn btn-success btn-lg btn-block" href='chat?errandsNum=${errands.errandsNum}'> 심부름
					시작하기   <span class="glyphicon glyphicon-chevron-right"></span>
				</a> <br>
				<button type="button" class="btn btn-default btn-lg btn-block"
					data-dismiss="modal">취소</button>

			</div>




		</div>
	</div>

	<script>
		var mapContainer = document.getElementById('daumMap'), // 지도를 표시할 div 
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
</body>
</html>
