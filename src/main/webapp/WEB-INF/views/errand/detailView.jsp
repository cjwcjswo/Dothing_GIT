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
<script>
	(function(e) {
		var t,
			o = {
				className : "autosizejs",
				append : "",
				callback : !1,
				resizeDelay : 10
			},
			i = '<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>',
			n = [ "fontFamily", "fontSize", "fontWeight", "fontStyle", "letterSpacing", "textTransform", "wordSpacing", "textIndent" ],
			s = e(i).data("autosize", !0)[0];
		s.style.lineHeight = "99px", "99px" === e(s).css("lineHeight") && n.push("lineHeight"), s.style.lineHeight = "", e.fn.autosize = function(i) {
			return this.length ? (i = e.extend({}, o, i || {}), s.parentNode !== document.body && e(document.body).append(s), this.each(function() {
				function o() {
					var t,
						o;
					"getComputedStyle" in window ? (t = window.getComputedStyle(u, null), o = u.getBoundingClientRect().width, e.each([ "paddingLeft", "paddingRight", "borderLeftWidth", "borderRightWidth" ], function(e, i) {
						o -= parseInt(t[i], 10)
					}), s.style.width = o + "px") : s.style.width = Math.max(p.width(), 0) + "px"
				}
				function a() {
					var a = {};
					if (t = u, s.className = i.className, d = parseInt(p.css("maxHeight"), 10), e.each(n, function(e, t) {
							a[t] = p.css(t)
						}), e(s).css(a), o(), window.chrome) {
						var r = u.style.width;
						u.style.width = "0px", u.offsetWidth, u.style.width = r
					}
				}
				function r() {
					var e,
						n;
					t !== u ? a() : o(), s.value = u.value + i.append, s.style.overflowY = u.style.overflowY, n = parseInt(u.style.height, 10), s.scrollTop = 0, s.scrollTop = 9e4, e = s.scrollTop, d && e > d ? (u.style.overflowY = "scroll", e = d) : (u.style.overflowY = "hidden", c > e && (e = c)), e += w, n !== e && (u.style.height = e + "px", f && i.callback.call(u, u))
				}
				function l() {
					clearTimeout(h), h = setTimeout(function() {
						var e = p.width();
						e !== g && (g = e, r())
					}, parseInt(i.resizeDelay, 10))
				}
				var d,
					c,
					h,
					u = this,
					p = e(u),
					w = 0,
					f = e.isFunction(i.callback),
					z = {
						height : u.style.height,
						overflow : u.style.overflow,
						overflowY : u.style.overflowY,
						wordWrap : u.style.wordWrap,
						resize : u.style.resize
					},
					g = p.width();
				p.data("autosize") || (p.data("autosize", !0), ("border-box" === p.css("box-sizing") || "border-box" === p.css("-moz-box-sizing") || "border-box" === p.css("-webkit-box-sizing")) && (w = p.outerHeight() - p.height()), c = Math.max(parseInt(p.css("minHeight"), 10) - w || 0, p.height()), p.css({
					overflow : "hidden",
					overflowY : "hidden",
					wordWrap : "break-word",
					resize : "none" === p.css("resize") || "vertical" === p.css("resize") ? "none" : "horizontal"
				}), "onpropertychange" in u ? "oninput" in u ? p.on("input.autosize keyup.autosize", r) : p.on("propertychange.autosize", function() {
					"value" === event.propertyName && r()
				}) : p.on("input.autosize", r), i.resizeDelay !== !1 && e(window).on("resize.autosize", l), p.on("autosize.resize", r), p.on("autosize.resizeIncludeStyle", function() {
					t = null, r()
				}), p.on("autosize.destroy", function() {
					t = null, clearTimeout(h), e(window).off("resize", l), p.off("autosize").off(".autosize").css(z).removeData("autosize")
				}), r())
			})) : this
		}
	})(window.jQuery || window.$);

	var __slice = [].slice;
	(function(e, t) {
		var n;
		n = function() {
			function t(t, n) {
				var r,
					i,
					s,
					o = this;
				this.options = e.extend({}, this.defaults, n);
				this.$el = t;
				s = this.defaults;
				for (r in s) {
					i = s[r];
					if (this.$el.data(r) != null) {
						this.options[r] = this.$el.data(r)
					}
				}
				this.createStars();this.syncRating();this.$el.on("mouseover.starrr", "span", function(e) {
					return o.syncRating(o.$el.find("span").index(e.currentTarget) + 1)
				});this.$el.on("mouseout.starrr", function() {
					return o.syncRating()
				});this.$el.on("click.starrr", "span", function(e) {
					return o.setRating(o.$el.find("span").index(e.currentTarget) + 1)
				});this.$el.on("starrr:change", this.options.change)
			}
			t.prototype.defaults = {
				rating : void 0,
				numStars : 5,
				change : function(e, t) {}
			};
			t.prototype.createStars = function() {
				var e,
					t,
					n;
				n = [];
				for (e = 1, t = this.options.numStars; 1 <= t ? e <= t : e >= t; 1 <= t ? e++ : e--) {
					n.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"))
				}
				return n
			};
			t.prototype.setRating = function(e) {
				if (this.options.rating === e) {
					e = void 0
				}
				this.options.rating = e;this.syncRating();return this.$el.trigger("starrr:change", e)
			};
			t.prototype.syncRating = function(e) {
				var t,
					n,
					r,
					i;
				e || (e = this.options.rating);
				if (e) {
					for (t = n = 0, i = e - 1; 0 <= i ? n <= i : n >= i; t = 0 <= i ? ++n : --n) {
						this.$el.find("span").eq(t).removeClass("glyphicon-star-empty").addClass("glyphicon-star")
					}
				}
				if (e && e < 5) {
					for (t = r = e; e <= 4 ? r <= 4 : r >= 4; t = e <= 4 ? ++r : --r) {
						this.$el.find("span").eq(t).removeClass("glyphicon-star").addClass("glyphicon-star-empty")
					}
				}
				if (!e) {
					return this.$el.find("span").removeClass("glyphicon-star").addClass("glyphicon-star-empty")
				}
			};return t
		}();return e.fn.extend({
			starrr : function() {
				var t,
					r;
				r = arguments[0], t = 2 <= arguments.length ? __slice.call(arguments, 1) : [];return this.each(function() {
					var i;
					i = e(this).data("star-rating");
					if (!i) {
						e(this).data("star-rating", i = new n(e(this), r))
					}
					if (typeof r === "string") {
						return i[r].apply(i, t)
					}
				})
			}
		})
	})(window.jQuery, window);$(function() {
		return $(".starrr").starrr()
	})

	$(function() {

		$('#new-review').autosize({
			append : "\n"
		});

		var reviewBox = $('#post-review-box');
		var newReview = $('#new-review');
		var openReviewBtn = $('#open-review-box');
		var closeReviewBtn = $('#close-review-box');
		var ratingsField = $('#ratings-hidden');

		openReviewBtn.click(function(e) {
			reviewBox.slideDown(400, function() {
				$('#new-review').trigger('autosize.resize');
				newReview.focus();
			});
			openReviewBtn.fadeOut(100);
			closeReviewBtn.show();
		});

		closeReviewBtn.click(function(e) {
			e.preventDefault();
			reviewBox.slideUp(300, function() {
				newReview.focus();
				openReviewBtn.fadeIn(200);
			});
			closeReviewBtn.hide();

		});

		$('.starrr').on('starrr:change', function(e, value) {
			ratingsField.val(value);
		});
	});
</script>

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


</head>
<body>

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
				<div class="card">
					<div class="container-fluid">
						<div class="wrapper row" style="width: 100%">
							<div class="details col-md-6" style="width:100%; border:2px solid red">
								<h3 class="product-title">${errands.title}</h3>
								<div class="rating">

									<span class="review-no">${errands.errandsReply.size()}개
										신청 중</span>
								</div>
								<p class="product-description">${errands.content}</p>
								<h4 class="price">
									물건 값: <span>${errands.productPrice}</span>
								</h4>
								<h4 class="price">
									심부름 값: <span>${errands.errandsPrice}</span>
								</h4>
								<h5 class="sizes">
									sizes: <span class="size" data-toggle="tooltip" title="small">s</span>
									<span class="size" data-toggle="tooltip" title="medium">m</span>
									<span class="size" data-toggle="tooltip" title="large">l</span>
									<span class="size" data-toggle="tooltip" title="xtra large">xl</span>
								</h5>
								<h5 class="colors">
									colors: <span class="color orange not-available"
										data-toggle="tooltip" title="Not In store"></span> <span
										class="color green"></span> <span class="color blue"></span>
								</h5>
								<div class="row" style="margin-top: 40px; width:100%;">
									<div class="col-md-6" style="width:100%;">
										<div class="container-fluid" style="width:100%">
											<div class="text-right">
												<a class="btn btn-success btn-green" href="#reviews-anchor"
													id="open-review-box">제가 할래요!</a>
											</div>

											<div class="row" id="post-review-box" style="display: none;">
												<div class="col-md-12">
													<form accept-charset="UTF-8" action="" method="post">
														<input id="ratings-hidden" name="rating" type="hidden">
														<textarea class="form-control animated" cols="50"
															id="new-review" name="comment" placeholder="댓글을 입력하세요"
															rows="5"></textarea>

														<br>

														<div class="text-right">

															<a class="btn btn-danger btn-sm" href="#"
																id="close-review-box"
																style="display: none; margin-right: 10px;"> <span
																class="glyphicon glyphicon-remove"></span>취소하기
															</a>
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
																	<div class="col-xs-3 col-md-2">
																		<img src="http://placehold.it/80"
																			class="img-circle img-responsive" alt="" width="80px"
																			height="80px" />
																	</div>
																	<div class="col-xs-9 col-md-10">
																		<div>
																			<div class="mic-info">
																				By: <a href="#">${reply.user.userId}</a><br>
																				${reply.replyDate}<br>
																				예상도착시간: ${reply.arrivalTime}
																			</div>
																		</div>
																		<div class="comment-text">
																		${reply.replyContent}
																			<button class="btn btn-info btn-sm" type="button"
																				data-toggle="modal" data-target="#myModal">이놈</button>

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
						<strong>Elf Cafe</strong> <br> 2135 Sunset Blvd <br> Los
						Angeles, CA 90026 <br> <abbr title="Phone">P:</abbr> (213)
						484-6829
					</address>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6 text-right">
					<p>
						<em>Date: 1st November, 2013</em>
					</p>
					<p>
						<em>Receipt #: 34522677W</em>
					</p>
				</div>
			</div>
			<div class="row">
				<div class="text-center">
					<h1>Receipt</h1>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>Product</th>
							<th>#</th>
							<th class="text-center">Price</th>
							<th class="text-center">Total</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="col-md-9"><em>Baked Rodopa Sheep Feta</em>
								</h4></td>
							<td class="col-md-1" style="text-align: center">2</td>
							<td class="col-md-1 text-center">$13</td>
							<td class="col-md-1 text-center">$26</td>
						</tr>
						<tr>
							<td class="col-md-9"><em>Lebanese Cabbage Salad</em>
								</h4></td>
							<td class="col-md-1" style="text-align: center">1</td>
							<td class="col-md-1 text-center">$8</td>
							<td class="col-md-1 text-center">$8</td>
						</tr>
						<tr>
							<td class="col-md-9"><em>Baked Tart with Thyme and
									Garlic</em>
								</h4></td>
							<td class="col-md-1" style="text-align: center">3</td>
							<td class="col-md-1 text-center">$16</td>
							<td class="col-md-1 text-center">$48</td>
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
									<strong>$6.94</strong>
								</p>
								<p>
									<strong>$6.94</strong>
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
									<strong>$31.53</strong>
								</h4></td>
						</tr>
					</tbody>
				</table>
				<a class="btn btn-success btn-lg btn-block" href="chat"> 심부름
					시작하기   <span class="glyphicon glyphicon-chevron-right"></span>
				</a> <br>
				<button type="button" class="btn btn-default btn-lg btn-block"
					data-dismiss="modal">취소</button>

			</div>




		</div>
	</div>
</body>
</html>
