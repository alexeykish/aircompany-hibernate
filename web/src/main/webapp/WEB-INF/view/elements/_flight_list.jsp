<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <c:if test="${(sessionScope.userType == '2')}">
        <form action="addflight" method="post">
            <input class="button" type="submit" name="submit" value="add flight"/>
        </form>
    </c:if>
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
                <td>${flight.departure.name}</td>
                <td>${flight.arrival.name}</td>
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
                        <c:if test="${(flight.crew.size() == 0)}">
                            <input class="table-button" style="width: 90px;  color:red;" type="submit" name="submit"
                                   value="details"/>
                        </c:if>
                        <c:if test="${(flight.crew.size() != 0)}">
                            <input class="table-button" style="width: 90px;" type="submit" name="submit"
                                   value="details"/>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
    <div>
        <table class="pagination">
            <tr>
                <c:if test="${requestScope.currentPage != 1}">
                    <td><a href="controller?command=get_all_flights_command&page=${requestScope.currentPage - 1}">Previous</a>
                    </td>
                </c:if>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="controller?command=get_all_flights_command&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td>
                        <a href="controller?command=get_all_flights_command&page=${requestScope.currentPage + 1}">Next</a>
                    </td>
                </c:if>
            </tr>
        </table>
    </div>


</div>
