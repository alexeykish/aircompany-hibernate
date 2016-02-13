<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin: 5px;">
	<h2>Update Flight</h2>
	<form name="updateflights" action="controller" method="post">
		<input type="hidden" name="command" value="update_flight_command" />
		<input type="hidden" name="fid" value="${requestScope.flight.fid}" />
		<table>
			<tr>
				<td class="input-label">ID:</td>
				<td><input class="inputForm" type="text" name="fid"
					value="${requestScope.flight.fid}" readonly  title="fid"/></td>
			</tr>
			<tr>
				<td class="input-label">Date:</td>
				<td><input class="inputForm" type="text" name="date"
					value="${requestScope.flight.date}"  title="date"/></td>
			</tr>
			<tr>
				<td class="input-label">From:</td>
					<td><select class="inputForm" name="from" title="from">
						<option value="${requestScope.flight.departure.aid}">${requestScope.flight.departure.name}</option>
						<c:forEach items="${requestScope.airports}" var="airport">
							<c:if test="${requestScope.flight.departure.aid != airport.aid}">
								<option value="${airport.aid}">${airport.name}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">To:</td>
				<td><select class="inputForm" name="to" title="to">
						<option value="${requestScope.flight.arrival.aid}">${requestScope.flight.arrival.name}</option>
						<c:forEach items="${requestScope.airports}" var="airport">
							<c:if test="${requestScope.flight.arrival.aid != airport.aid}">
								<option value="${airport.aid}">${airport.name}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Plane:</td>
				<td><select class="inputForm" name="pid" title="pid">
					<option value="${requestScope.flight.plane.pid}">${requestScope.flight.plane.model}</option>
					<c:forEach items="${requestScope.planes}" var="plane">
						<c:if test="${requestScope.flight.plane.pid != plane.pid}">
							<option value="${plane.pid}">${plane.model}</option>
						</c:if>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Status:</td>
				<td><select class="inputForm" name="status" title="status">
					<option value="${requestScope.flight.status}">${requestScope.flight.status}</option>
					<c:forEach items="${requestScope.statuses}" var="status">
						<c:if test="${requestScope.flight.status != status}">
							<option value="${status}">${status}</option>
						</c:if>
					</c:forEach>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit"
			value="update flight" />
	</form>
</div>
