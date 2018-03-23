<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<title>ToDo list</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/includes/header.jsp" />

		<div class="container-fluid main_content">
			<div class="row">
				<div class="col">
					<p class="welcome_text">
						Hello, this is your personal task scheduler. If
						you are a new user, please register, or log in with your username.
					</p>
				</div>
			</div>
		</div>
		
		<jsp:include page="/includes/footer.jsp" />
	</div>
	<script src="js/jquery-3.3.1.min.js" />
	<script src="js/bootstrap.min.js" />
</body>
</html>