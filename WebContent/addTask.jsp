<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<title>Add Task</title>
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
				<div class="col-4">
					<form action="<c:url value='/addTask' />" method="POST" class="addTask_form">
						<h2>Add your new task</h2>
						<p>Task content</p>
						<textarea name="<%=ConstantsJSP.KEY_TASK_CONTENT%>" placeholder="What what you like to do?"></textarea>
						<c:if test="${section eq 'SOMEDAY'}">
							<p>Date</p>
							<input type="text" name="<%=ConstantsJSP.KEY_TASK_DATE%>" placeholder="When? (YYYY-mm-dd)"><br>	
						</c:if>
						<input type="submit" value="Add task!">
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