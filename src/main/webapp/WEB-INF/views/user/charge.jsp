<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<link href="assets/fonts/font-awesome.css" rel="stylesheet"
	type="text/css">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/bootstrap-select.min.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/dropzone.css" type="text/css">
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<link rel="stylesheet" href="assets/css/user.style.css" type="text/css">
<!-- <script type="text/javascript">
  
  function selectBox() {
	
}
</script> -->
</head>
<body onunload=""
	class="page-subpage page-my-items navigation-off-canvas" id="page-top">

	<!-- Page Canvas-->
	<div id="page-canvas">

		<!--Off Canvas Navigation-->
		<nav class="off-canvas-navigation"> 
		   <header>�޴�</header>
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
					<li class="active">����Ʈ ���� �� ����</li>
				</ol>
				<!-- /.breadcrumb-->
			</div>
			<!-- /.container-->
		</div>
		<!-- /.breadcrumb-wrapper--> </section>
		<!--end Sub Header-->




		<!--Page Content-->
		<div id="page-content">
			<section class="container"> <header>
			<ul class="nav nav-pills">
				<li><a href="${pageContext.request.contextPath}/user/myPage"><h1
							class="page-title">��������</h1></a></li>
				<li><a
					href="${pageContext.request.contextPath}/errand/myRequest"><h1
							class="page-title">�ɺθ� ��û �� ���� ����</h1></a></li>
				<li><a
					href="${pageContext.request.contextPath}/user/safetyRegister"><h1
							class="page-title">���� �ɺθ��� ��û</h1></a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/user/charge"><h1
							class="page-title">����Ʈ ���� �� ����</h1></a></li>
			</ul>
			</header>
			<table class="table table-hover">
				<thead align="left">
					<tr>

						<td align="left">���� ���� :<label class="radio-inline"><input
								type="radio" name="optradio">������ �Ա�</label> <label
							class="radio-inline"><input type="radio" name="optradio">ī�����</label></td>
					</tr>
				</thead>
				<tbody align="left">
					<tr>
						<div class="form-group col-xs-4">
							<label for="sel1">����Ʈ �׼�</label> <select class="form-control"
								id="sel1" onChange="selectBox()">
								<option value="5000">5,000p(5,000��)</option>
								<option value="10000">10,000p(10,000��)</option>
								<option value="20000">20,000p(20,000��)</option>
								<option value="50000">50,000p(50,000��)</option>
								<option value="100000">100,000p(100,000��)</option>

							</select>
						</div>

					</tr>


				</tbody>

			</table>
			<div class="form-group" align="center">
				<button type="submit" class="btn btn-large btn-default" id="submit">����</button>


			</div>

			<h1>
				<i class="fa fa-check"></i>�ֱ� ���� ����
			</h1>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>������</th>
						<th>���� ����Ʈ</th>
						<th>��� ����Ʈ</th>
						<th>�ܿ� ����Ʈ</th>
						<th>ȯ�� ����Ʈ</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2017-06-19</td>
						<td>5,000</td>
						<td>0</td>
						<td>12,000</td>
						<td>-</td>

					</tr>

				</tbody>
			</table>


			<!-- ���������̼� -->

			<ul class="pager">
				<li><a href="#">Previous</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">Next</a></li>
			</ul>

			<!-- ���������̼� ���� --> <!-- /.form-group --> </section>


		</div>
		<!--/.col-md-6-->
	</div>

	<!-- </div>

	</div> -->
	</section>




	<script type="text/javascript" src="assets/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="assets/js/before.load.js"></script>
	<script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/smoothscroll.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="assets/js/dropzone.min.js"></script>
	<script type="text/javascript" src="assets/js/custom.js"></script>
	<script type="text/javascript" src="assets/js/maps.js"></script>


	<!--[if lte IE 9]>
<script type="text/javascript" src="assets/js/ie-scripts.js"></script>
<![endif]-->
</body>
</html>