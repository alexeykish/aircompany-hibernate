<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="margin:5px;">
    <h2>Plane report: #${requestScope.plane.pid}</h2>
    <table class="input-label">
        <tr>
            <td>Model:</td>
            <td>${requestScope.plane.model}</td>
        </tr>
        <tr>
            <td>Passenger capacity:</td>
            <td>${requestScope.plane.capacity}</td>
        </tr>
        <tr>
            <td>Flight range:</td>
            <td>${requestScope.plane.range}</td>
        </tr>
        <tr>
            <td>Flight team:</td>
        </tr>
        <tr>
            <td>Number of pilots:</td>
            <td>${requestScope.team['PILOT']}</td>
        </tr>
        <tr>
            <td>Number of navigators:</td>
            <td>${requestScope.team['NAVIGATOR']}</td>
        </tr>
        <tr>
            <td>Number of radiooperators:</td>
            <td>${requestScope.team['RADIOOPERATOR']}</td>
        </tr>
        <tr>
            <td>Number of stewardess:</td>
            <td>${requestScope.team['STEWARDESS']}</td>
        </tr>
    </table>
    <div>
        <form action="controller" method="post">
            <h4>Plane status is: ${requestScope.plane.status}</h4>
            <select class="inputForm" name="status" title="Status">
                <c:forEach items="${requestScope.statuses}" var="status">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="command" value="set_plane_status_command"/>
            <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
            <input class="button" type="submit" name="submit" value="set status"/>
        </form>
    </div>
    <div>
        <c:choose>
            <c:when test="${requestScope.permissionChangeDeleteStatus =='false'}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="delete_plane_command"/>
                    <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
                    <input class="button" type="submit" name="submit" value="delete"/>
                </form>
                <form action="updateplane" method="post">
                    <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
                    <input class="button" type="submit" name="submit" value="edit"/>
                </form>
            </c:when>
            <c:otherwise>
                <h5>It is possible to change or delete plane details if there are no flights associated with this plane</h5>
                <h4>Last five plane flights</h4>
                <table class="list">
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${requestScope.flights}" var="flight">
                        <tr>
                            <td>${flight.fid}</td>
                            <td>${flight.date}</td>
                            <td>${flight.from.city}</td>
                            <td>${flight.to.city}</td>
                            <td>${flight.status}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
