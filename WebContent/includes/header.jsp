<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<header>
	<p class="user_name">
		User:
		<c:choose>
			<c:when test="${not empty user.name}">
				<c:out value="${user.name}"></c:out>
			</c:when>
			<c:otherwise>
				<c:out value="<%= ConstantsJSP.DEFAULT_USER %>"></c:out>
			</c:otherwise>
		</c:choose>
	</p>
	<div class="authorization">
		<c:choose>
			<c:when test="${not empty user.name}">
				<a href="logout">Logout</a>
			</c:when>
			<c:otherwise>
				<a href="login.jsp">Login</a>
				<a href="reg.jsp">Registrate</a>
			</c:otherwise>
		</c:choose>
	</div>
</header>