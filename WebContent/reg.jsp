<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/loginReg.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration page</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="/includes/header.jsp" />
		<div class="content">
		<c:if test="${not empty warningMessage}">
			<c:out value="${warningMessage}"></c:out>
		</c:if>
			<form action='<c:url value="/registration" />' method="POST" class="userForm">
				<p class="form_header">Registration</p>
				<p>Login</p>
				<input type="text" name="<%=ConstantsJSP.KEY_LOGIN %>">
				<p>Password</p>
				<input type="text" name="<%=ConstantsJSP.KEY_PASSWORD %>"><br>
				<input type="submit" value="Register">
			</form>
		</div>
		<jsp:include page="/includes/footer.jsp" />
	</div>
</body>
</html>