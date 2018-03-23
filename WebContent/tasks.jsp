<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP"%>
<%@ page import="by.gsu.epamlab.model.enums.Section"%>
<%@ page import="by.gsu.epamlab.model.enums.Operation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/tasks.css">
<title>tasks</title>
</head>
<body>
	<jsp:include page="/includes/header.jsp" />
	<div class="content">
		<form action="<c:url value='/tasks' />" method="GET" class="sectionForm">
			<input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.TODAY.getName() %>"> 
			<input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.TOMORROW.getName() %>"> 
			<input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.SOMEDAY.getName() %>"> 
			<input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.FIXED.getName() %>"> 
			<input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.RECYCLE_BIN.getName() %>">
		</form>
		
		<form action="<c:url value='/uploadFile' />" method="POST" id="uploadFileId" name="uploadFile" enctype="multipart/form-data">
			<input type="hidden" name="<%= ConstantsJSP.KEY_HIDDEN_TASK_ID%>">
		</form>
		<form action="<c:url value='/downloadFile' />" method="POST" id="downloadFileId" name="downloadFile">
			<input type="hidden" name="<%= ConstantsJSP.KEY_HIDDEN_TASK_ID%>">
		</form>
		<form action="<c:url value='/deleteFile' />" method="POST" id="deleteFileId" name="deleteFile">
			<input type="hidden" name="<%= ConstantsJSP.KEY_HIDDEN_TASK_ID%>">
		</form>
		
		<form action="<c:url value='/operations' />" method="POST" class="sectionForm">
			<c:choose>
				<c:when test="${not empty tasks}">
					<table class="tasksTable">
						<tr>
							<th></th>
							<th>Content</th>
							<c:if test="${section ne 'TODAY' && section ne 'TOMORROW'}">
								<th>Date</th>
							</c:if>
							<th>file</th>
						</tr>
						<c:forEach var="task" items="${tasks}">
							<tr>
								<th><input type="checkbox" name="<%= ConstantsJSP.KEY_TASK_ID %>" value="${task.id}"></th>
								<th>${task.content}</th>
								<c:if test="${section ne 'TODAY' && section ne 'TOMORROW'}">
									<th>${task.date}</th>
								</c:if>
								<c:choose>
									<c:when test="${not empty task.trueFileName}">
										<th>
											<a href="javascript:downloadFileForm('${task.id}')"> ${task.trueFileName} </a>
											<a href="javascript:deleteFileForm('${task.id}')">Delete</a>
										</th>
									</c:when>
									<c:otherwise>
										<th><input type="file" onchange="uploadFileForm('${task.id}')" form="uploadFileId" name="filename"></th>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h2>No tasks</h2>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${section eq 'RECYCLE_BIN'}">
					<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.RESTORE.getName() %>"> 
					<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.DELETE.getName() %>">  
					<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.DELETE_ALL.getName() %>"> 
				</c:when>
				<c:otherwise>
					<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.REMOVE.getName() %>">
				</c:otherwise>
			</c:choose>
			<c:if test="${section ne 'RECYCLE_BIN' && section ne 'FIXED'}">
				<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.ADD.getName() %>"> 
				<input type="submit" name="<%= ConstantsJSP.KEY_OPERATION %>" value="<%=Operation.FIX.getName() %>"> 
			</c:if>
		</form>
	</div>
	
	<script type="text/javascript" src="js/fileOperationHandler.js"/></script>
	<jsp:include page="/includes/footer.jsp" />

</body>
</html>