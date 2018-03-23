<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<div class="container-fluid header">
	<div class="row navigation">
		<div class="col-4 todo_title">
			To-do List
		</div>
		<div class="col-2 user_display">
			<i class="fa fa-user-o" aria-hidden="true"></i> 
			<c:choose>
				<c:when test="${not empty user.name}">
					<c:out value="${user.name}"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="<%= ConstantsJSP.DEFAULT_USER %>"></c:out>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="col-6 header_links">
			<ul>
				<c:choose>
					<c:when test="${not empty user.name}">
						<li><a href="logout" class="header_link">Logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="reg.jsp" class="registration_link">Registration</a></li>
						<li><a href="login.jsp" class="header_link">Authorization</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>