<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<html>
<head>
<title>List Customers</title>
<link type = "text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
	
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		<!-- put new button : Add Customer -->
		<button class="add-button" 
			onclick="window.location.href='showFormForAdd'; return false;">Add Customer</button>
		<!-- add a search box -->
		<form:form action="search" method="GET">
			Search Customer: <input type="text" name="theSearchName" />
			<input type="submit" value="Search" class="add-button" />
		</form:form>
			<!-- add out html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				<!-- construct an "Delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				<tr>
					<td>${tempCustomer.firstName}</td>
					<td>${tempCustomer.lastName}</td>
					<td>${tempCustomer.email}</td>
					<!-- display the update link -->
					<td><a href="${updateLink}" >Update</a>
					|
					<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this'))) return false">Delete</a>
					</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>

</body>
</html>