<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/addTask.css">
<link rel="stylesheet" href="css/main.css">
<title>Add task</title>
</head>
<body>
	<jsp:include page="/includes/header.jsp" />
	<c:if test="${not empty warningMessage}">
		<c:out value="${warningMessage}"></c:out>
	</c:if>
	<form action="<c:url value='/addTask' />" method="POST" class="addTaskForm">
		<p>Add your new task</p>
		<input type="text" name="<%=ConstantsJSP.KEY_TASK_CONTENT%>" placeholder="What what you like to do?" class="addTaskContent"><br>
		<c:if test="${section eq 'SOMEDAY'}">
			<input type="text" name="<%=ConstantsJSP.KEY_TASK_DATE%>" placeholder="When? (YYYY-mm-dd)" class="addTaskDate">
			<br>
		</c:if>
		<input type="submit" value="Add task!">
	</form>
	<jsp:include page="/includes/footer.jsp" />
</body>
</html>