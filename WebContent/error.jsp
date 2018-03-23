<%@ taglib uri="/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/main.css">
<title>Eternal error</title>
</head>
<body>
	<jsp:include page="/includes/header.jsp" />
		<div class="content">
			<c:if test="${not empty errorMessage }">
				
				<p class="errorMessage"> 
				${errorMessage}.
				The application seems to have some problems with the server. Please try again later)</p>
			</c:if>
			<a href="index.jsp">To main page</a>
		</div>
	<jsp:include page="/includes/footer.jsp" />
</body>
</html>