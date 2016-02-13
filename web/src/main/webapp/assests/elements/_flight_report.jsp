<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="margin:5px;">
    <h2>Flight report: #${requestScope.flight.fid}</h2>
    <table class="input-label">
        <tr>
            <td>Date:</td>
            <td>${requestScope.flight.date}</td>
        </tr>
        <tr>
            <td>Departure from:</td>
            <td>${requestScope.flight.departure.name}</td>
        </tr>
        <tr>
            <td>Arrival to:</td>
            <td>${requestScope.flight.arrival.name}</td>
        </tr>
        <tr>
            <td>Plane:</td>
            <td>${requestScope.flight.plane.model}</td>
        </tr>
    </table>
    <div>
        <h4>Flight status is: ${requestScope.flight.status}</h4>
    </div>
    <div>
        <c:if test="${(requestScope.flight.status == 'CREATED')}">
            <c:if test="${(sessionScope.userType == '2')}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="delete_flight_command"/>
                    <input type="hidden" name="fid" value="${requestScope.flight.fid}"/>
                    <input class="table-button" type="submit"
                           name="submit"
                           value="delete"/>
                </form>

                <form action="updateflight" method="post">
                    <input type="hidden" name="fid" value="${requestScope.flight.fid}"/>
                    <input class="table-button" type="submit" name="submit" value="edit"/>
                </form>
            </c:if>
            <c:if test="${(sessionScope.userType == '1')}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="set_team_command"/>
                    <input type="hidden" name="fid" value="${requestScope.flight.fid}"/>
                    <c:if test="${(requestScope.flight.crew.size() == 0)}">
                        <input class="table-button" style="width: 90px;  color:red;" type="submit" name="submit"
                               value="set flight team"/>
                    </c:if>
                    <c:if test="${(requestScope.flight.crew.size() != 0)}">
                        <input class="table-button" style="width: 90px;" type="submit" name="submit"
                               value="change flight team"/>
                    </c:if>
                </form>
            </c:if>
        </c:if>
        <div>
            <h4>Flight team:</h4>
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Position</th>
                </tr>
                <c:forEach items="${requestScope.flight.crew}" var="employee">
                    <tr>
                        <td>${employee.eid}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.position}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
