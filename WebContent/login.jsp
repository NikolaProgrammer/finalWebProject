<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<title>Login</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/includes/header.jsp" />

		<div class="container-fluid main_content">
			<c:if test="${not empty warningMessage}">
				<div class="row justify-content-center">
					<div class="col-3 warning_content">
						<i class="fa fa-exclamation-circle" aria-hidden="true"></i> <c:out value="${warningMessage}"></c:out>
					</div>
				</div>
			</c:if>
			<div class="row justify-content-center">
				<div class="col-3">
					<form action="<c:url value='/login' />" method="POST" class="registration_form">
						<h2>Authorization</h2>
						<p>Login</p>
						<input type="text" name="<%=ConstantsJSP.KEY_LOGIN %>">
						<p>Password</p>
						<input type="password" name="<%=ConstantsJSP.KEY_PASSWORD %>">
						<input type="submit" value="Login">
					</form>
				</div>
			</div>
		</div>

		<jsp:include page="/includes/footer.jsp" />
	</div>
	<script src="js/jquery-3.3.1.min.js" />
	<script src="js/bootstrap.min.js" />
</body>
</html>