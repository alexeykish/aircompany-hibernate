<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div style="margin:5px;">
	<h2>Add Plane</h2>
	<form name="addairport" action="controller" method="post" autocomplete="off">
		<input type="hidden" name="command" value="add_airport_command" />
		<table>
			<tr>
				<td class="input-label">Name:</td>
				<td><input class="inputForm" type="text" name="name" title="Name"/></td>
			</tr>
			<tr>
				<td class="input-label">City:</td>
				<td><input class="inputForm" type="text" name="city" title="City"/></td>
			</tr>
			<tr>
				<td class="input-label">Country:</td>
				<td><input class="inputForm" type="text" name="country" title="Country"/></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit" value="add plane" />
	</form>
</div>
