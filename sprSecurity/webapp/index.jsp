<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Hello ! Srv</title>

<link rel="stylesheet" type="text/css" href="resourses/css/MainCSS.css" />
<link rel="stylesheet" type="text/css"
	href="resourses/css/bootstrap/bootstrap.css" />

<script src="resourses/js/mainJS.js"></script>
</head>
<body>

	<div class="menu_bar_div">
		<a href="#" class="menu_link">Home</a> <a href="#" class="menu_link">View</a>
		<a href="#" class="menu_link">Contact</a>
	</div>


	<div class="menu_div">
		<br />

		<ul>
			<li><a href="#home">Home</a></li>
			<li><a href="#news">News</a></li>
			<li><a href="#contact">Contact</a></li>
			<li><a href="#about">About</a></li>
		</ul>
	</div>
	<div class="body_div ">
		<div class="container-fluid">
			<div class="col-md-3">

				<form class="form-horizontal">

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputEmail3">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">text</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputPassword3">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Sign in</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-5">
				<form class="form-horizontal" id="MainForm">

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Temp
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputEmail3"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">Temp
							Email</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputPassword3"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Temp
							REF</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputEmail3"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">Status</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputPassword3"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-8 col-sm-2">
							<button type="button" class="btn btn-default" onclick="loadElementWithValues(this)">Save</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>














	<!-- <form action="HelloServlet" method="get" style="padding: 100px;">
		name:<input type="text" name="name" /> <br /> email:<input
			type="text" name="email" /><br />
		<a value="submit" type="submit" style="padding-right: 10;">submit</a>
	</form>
 -->
</body>
</html>