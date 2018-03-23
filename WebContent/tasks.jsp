<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP"%>
<%@ page import="by.gsu.epamlab.model.enums.Section"%>
<%@ page import="by.gsu.epamlab.model.enums.Operation" %>
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
				<div class="col-3 justify-content-center">
					<form action="<c:url value='/tasks' />" method="GET" class="section_form">
						<ul>
							<li><input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.TODAY.getName() %>"></li>
							<li><input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.TOMORROW.getName() %>"></li>
							<li><input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.SOMEDAY.getName() %>"></li>
							<li><input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.FIXED.getName() %>"></li>
							<li><input type="submit" name="<%= ConstantsJSP.KEY_SECTION %>" value="<%= Section.RECYCLE_BIN.getName() %>"></li>
						</ul>
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
				</div>

				<div class="col-9 justify-content-center">
					<form action="<c:url value='/operations' />" method="POST" class="operation_form">
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

						<c:choose>
							<c:when test="${not empty tasks}">
								<table class="tasks_table">
									<tr>
										<th></th>
										<th>Content</th>
										<c:if test="${section ne 'TODAY' && section ne 'TOMORROW'}">
											<th><i class="fa fa-calendar" aria-hidden="true"></i> Date</th>
										</c:if>
										<th>File</th>
									</tr>
									<c:forEach var="task" items="${tasks}">
										<tr>
											<td style="padding-right: 1em;">
												<input type="checkbox" name="<%= ConstantsJSP.KEY_TASK_ID %>" value="${task.id}">
											</td>
											<td style="text-align: left; width: 70%;">${task.content}</td>
											<c:if test="${section ne 'TODAY' && section ne 'TOMORROW'}">
												<td>${task.date}</td>
											</c:if>
											<c:choose>
												<c:when test="${not empty task.trueFileName}">
													<td>
														<div class="file_operations">
															<a href="javascript:downloadFileForm('${task.id}')">
																<i class="fa fa-download" aria-hidden="true"></i> ${task.trueFileName}
															</a><br>
															<a href="javascript:deleteFileForm('${task.id}')">
																<i class="fa fa-trash-o" aria-hidden="true"></i> Delete
															</a>
														</div>
													</td>
												</c:when>
												<c:otherwise>
													<td>
														<input type="file" name="filename" id="fileId${task.id}" class="inputfile" form="uploadFileId" onchange="uploadFileForm('${task.id}')"/>
														<label for="fileId${task.id}" class="inputfile_label">
															<i class="fa fa-file-o" aria-hidden="true"></i> Choose a file 
														</label>
													</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</table>
							</c:when>
							<c:otherwise>
								<p>No Tasks</p>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/fileOperationHandler.js"/></script>
		<jsp:include page="/includes/footer.jsp" />
	</div>
	<script src="js/jquery-3.3.1.min.js" />
	<script src="js/bootstrap.min.js" />
</body>
</html>