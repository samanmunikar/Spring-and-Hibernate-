<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h2>Welcome to the Home Page</h2>
	<hr>
	<!-- Display user name and roles -->
	<p>User:<security:authentication property="principal.username" /></p>
	<br><br>
	<p>Roles:<security:authentication property="principal.authorities" /></p> 
	
	<hr>
	<security:authorize access="hasRole('MANAGER')">
	<!-- Add a link to point to /leaders ... this is for managers -->
	<p>
		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>(only for managers)
	</p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
	</form:form>
</body>
</html>