<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div style="margin:5px;">
	<h2>Add Employee</h2>
	<form name="addemployee" action="controller" method="post" autocomplete="off">
		<input type="hidden" name="command" value="add_employee_command" />
		<table>
			<tr>
				<td class="input-label">Firstname:</td>
				<td><input class="inputForm" type="text" name="firstName" title="Firstname"/></td>
			</tr>
			<tr>
				<td class="input-label">Lastname:</td>
				<td><input class="inputForm" type="text" name="lastName" title="Lastname"/></td>
			</tr>
			<tr>
				<td class="input-label">Position:</td>
				<td><select class="inputForm" name="position" title="Position">
					<c:forEach items="${requestScope.positions}" var="position">
						<option value="${position}">${position}</option>
					</c:forEach>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit" value="add employee" />
	</form>
</div>
