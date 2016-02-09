<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<table class="list">
		<tr>
			<th>ID</th>
			<th>FIRSTNAME</th>
			<th>LASTNAME</th>
			<th>LOGIN</th>
			<th>EMAIL</th>
			<th>USER TYPE</th>
			<th>USER STATUS</th>
		</tr>
		<c:forEach items="${requestScope.users}" var="user">
			<tr>
				<td>${user.uid}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.login}</td>
				<td>${user.email}</td>
				<td>${user.userType}</td>
				<td>${user.status}</td>
			</tr>
		</c:forEach>
	</table>
</div>
