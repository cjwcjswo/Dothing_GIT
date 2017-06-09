<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
	
	
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/Article-List.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/dh-card-image-left-dark.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/Highlight-Phone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/Projects-Horizontal.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/Team-Boxed.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/Team-Grid.css">




</head>
<body>

	<div class="projects-horizontal"></div>
	<div class="carousel slide" data-ride="carousel" id="carousel-1">
		<div class="carousel-inner" role="listbox">
			<div class="item">
				<img
					src="http://placeholdit.imgix.net/~text?txtsize=42&amp;txt=Carousel+Image&amp;w=1400&amp;h=600"
					alt="Slide Image">
			</div>
			<div class="item">
				<img
					src="http://placeholdit.imgix.net/~text?txtsize=42&amp;txt=Carousel+Image&amp;w=1400&amp;h=600"
					alt="Slide Image">
			</div>
			<div class="item active">
				<img
					src="http://placeholdit.imgix.net/~text?txtsize=42&amp;txt=Carousel+Image&amp;w=1400&amp;h=600"
					alt="Slide Image">
			</div>
		</div>
		<div>
			<a class="left carousel-control" href="#carousel-1" role="button"
				data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i><span
				class="sr-only">Previous</span></a> <a class="right carousel-control"
				href="#carousel-1" role="button" data-slide="next"><i
				class="glyphicon glyphicon-chevron-right"></i><span class="sr-only">Next</span></a>
		</div>
		<ol class="carousel-indicators">
			<li data-target="#carousel-1" data-slide-to="0"></li>
			<li data-target="#carousel-1" data-slide-to="1"></li>
			<li data-target="#carousel-1" data-slide-to="2" class="active"></li>
		</ol>
	</div>
	<div class="article-list">
		<div class="container">
			<div class="intro">
				<h2 class="text-center">Latest Articles</h2>
				<p class="text-center">Nunc luctus in metus eget fringilla.
					Aliquam sed justo ligula. Vestibulum nibh erat, pellentesque ut
					laoreet vitae.</p>
			</div>
			<div class="row articles">
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/resources/img/main/desk.jpg"></a>
					<h3 class="name">Article Title</h3>
					<p class="description">Aenean tortor est, vulputate quis leo
						in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida.
						Aliquam varius finibus est, interdum justo suscipit id.</p>
					<a href="#" class="action"><i
						class="glyphicon glyphicon-circle-arrow-right"></i></a>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/resources/img/main/building.jpg"></a>
					<h3 class="name">Article Title</h3>
					<p class="description">Aenean tortor est, vulputate quis leo
						in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida.
						Aliquam varius finibus est, interdum justo suscipit id.</p>
					<a href="#" class="action"><i
						class="glyphicon glyphicon-circle-arrow-right"></i></a>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<a href="#"><img class="img-responsive"
						src="${pageContext.request.contextPath}/resources/img/main/loft.jpg"></a>
					<h3 class="name">Article Title</h3>
					<p class="description">Aenean tortor est, vulputate quis leo
						in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida.
						Aliquam varius finibus est, interdum justo suscipit id.</p>
					<a href="#" class="action"><i
						class="glyphicon glyphicon-circle-arrow-right"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="projects-horizontal">
		<div class="container">
			<div class="intro">
				<h2 class="text-center">Projects</h2>
				<p class="text-center">Nunc luctus in metus eget fringilla.
					Aliquam sed justo ligula. Vestibulum nibh erat, pellentesque ut
					laoreet vitae.</p>
			</div>
			<div class="row projects">
				<div class="col-sm-6 item">
					<div class="row">
						<div class="col-md-5">
							<a href="#"><img class="img-responsive"
								src="${pageContext.request.contextPath}/resources/img/main/desk.jpg"></a>
						</div>
						<div class="col-md-7">
							<h3 class="name">Project Name</h3>
							<p class="description">Aenean tortor est, vulputate quis leo
								in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
								gravida.</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 item">
					<div class="row">
						<div class="col-md-5">
							<a href="#"><img class="img-responsive"
								src="${pageContext.request.contextPath}/resources/img/main/building.jpg"></a>
						</div>
						<div class="col-md-7">
							<h3 class="name">Project Name</h3>
							<p class="description">Aenean tortor est, vulputate quis leo
								in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
								gravida.</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 item">
					<div class="row">
						<div class="col-md-5">
							<a href="#"><img class="img-responsive"
								src="${pageContext.request.contextPath}/resources/img/main/loft.jpg"></a>
						</div>
						<div class="col-md-7">
							<h3 class="name">Project Name</h3>
							<p class="description">Aenean tortor est, vulputate quis leo
								in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
								gravida.</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 item">
					<div class="row">
						<div class="col-md-5">
							<a href="#"><img class="img-responsive"
								src="${pageContext.request.contextPath}/resources/img/main/minibus.jpeg"></a>
						</div>
						<div class="col-md-7">
							<h3 class="name">Project Name</h3>
							<p class="description">Aenean tortor est, vulputate quis leo
								in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
								gravida.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="team-boxed">
		<div class="container">
			<div class="intro">
				<h2 class="text-center">Team</h2>
				<p class="text-center">Nunc luctus in metus eget fringilla.
					Aliquam sed justo ligula. Vestibulum nibh erat, pellentesque ut
					laoreet vitae.</p>
			</div>
			<div class="row people">
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/resources/img/main/1.jpg">
						<h3 class="name">Ben Johnson</h3>
						<p class="title">Musician</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/resources/img/main/2.jpg">
						<h3 class="name">Emily Clark</h3>
						<p class="title">Artist</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 item">
					<div class="box">
						<img class="img-circle"
							src="${pageContext.request.contextPath}/resources/img/main/3.jpg">
						<h3 class="name">Carl Kent</h3>
						<p class="title">Stylist</p>
						<p class="description">Aenean tortor est, vulputate quis leo
							in, vehicula rhoncus lacus. Praesent aliquam in tellus eu
							gravida. Aliquam varius finibus est, et interdum justo suscipit
							id. Etiam dictum feugiat tellus, a semper massa.</p>
						<div class="social">
							<a href="#"><i class="fa fa-facebook-official"></i></a><a
								href="#"><i class="fa fa-twitter"></i></a><a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section></section>
	<div class="team-grid"></div>


</body>
</html>