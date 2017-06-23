<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>

<body>

	<ul>
		<li><a href="#home" class="has-child" data-toggle="collapse"
			aria-expanded="false" aria-controls="home">게시판</a>
			<div class="collapse" id="home">
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/board/noticeBoardList">공지게시판</a></li>
					<li><a
						href="${pageContext.request.contextPath}/board/inquiryBoardList">1:1게시판</a></li>
					<li><a
						href="${pageContext.request.contextPath}/board/faq">FAQ게시판</a></li>
				</ul>
			</div></li>
		<security:authorize access="isAuthenticated()">



			<li><a href="#sub-level-1" class="has-child"
				data-toggle="collapse" aria-expanded="false"
				aria-controls="sub-level-1">심부름</a>
				<div class="collapse" id="sub-level-1">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/errand/errand">심부름
								목록</a></li>
						<li><a
							href="${pageContext.request.contextPath}/errand/register">심부름
								등록</a></li>
					</ul>

				</div></li>
			<li><a href="${pageContext.request.contextPath}/user/myPage">마이페이지</a></li>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<li><a
				href="${pageContext.request.contextPath}/admin/adminUserList">운영자모드</a>
			</li>
		</security:authorize>
	</ul>

	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>