<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div style="margin:5px;">
	<h2>Add Plane</h2>
	<form name="addplane" action="controller" method="post" autocomplete="off">
		<input type="hidden" name="command" value="add_plane_command" />
		<table>
			<tr>
				<td class="input-label">Model:</td>
				<td><input class="inputForm" type="text" name="model" title="Model"/></td>
			</tr>
			<tr>
				<td class="input-label">Passenger capacity:</td>
				<td><input class="inputForm" type="text" name="capacity" title="Capacity"/></td>
			</tr>
			<tr>
				<td class="input-label">Flight range:</td>
				<td><input class="inputForm" type="text" name="range" title="Range"/></td>
			</tr>
			<tr>
				<td class="input-label">Number of pilots:</td>
				<td><input class="inputForm" type="text" name="num_pilots" title="Num_pilots"/></td>
			</tr>
			<tr>
				<td class="input-label">Number of navigators:</td>
				<td><input class="inputForm" type="text" name="num_navigators" title="Num_navigators"/></td>
			</tr>
			<tr>
				<td class="input-label">Number of radiooperators:</td>
				<td><input class="inputForm" type="text" name="num_radiooperators" title="Num_radiooperators"/></td>
			</tr>
			<tr>
				<td class="input-label">Number of stewardess:</td>
				<td><input class="inputForm" type="text" name="num_stewardess" title="Num_stewardess"/></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit" value="add plane"/>
	</form>
</div>
