<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <table class="list">
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>From</th>
            <th>To</th>
            <%--<th>Team</th>--%>
            <th>Plane</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${requestScope.flights}" var="flight">
            <tr>
                <td>${flight.fid}</td>
                <td>${flight.date}</td>
                <td>${flight.from.city}</td>
                <td>${flight.to.city}</td>
                <%--<td>
                    <c:forEach items="${flight.team}" var="employee">
                        <div>#${employee.eid} ${employee.lastName} ${employee.position}</div>
                    </c:forEach>
                </td>--%>
                <td>${flight.plane.model}</td>
                <td>${flight.status}</td>
                <td style="width: 70px;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="flight_report_command"/>
                        <input type="hidden" name="fid" value="${flight.fid}"/>
                        <c:if test="${(flight.team.size() == 0)}">
                            <input class="table-button" style="width: 90px;  color:red;" type="submit" name="submit" value="details"/>
                        </c:if>
                        <c:if test="${(flight.team.size() != 0)}">
                            <input class="table-button" style="width: 90px;" type="submit" name="submit" value="details"/>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${(sessionScope.userType == '2')}">
        <form action="addflight" method="post">
            <input class="button" type="submit" name="submit" value="add flight"/>
        </form>
    </c:if>
</div>
