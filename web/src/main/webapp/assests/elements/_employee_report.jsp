<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="margin:5px;">
    <h2>Employee report: #${requestScope.employee.eid}</h2>
    <table class="input-label">
        <tr>
            <td>Firstname:</td>
            <td>${requestScope.employee.firstName}</td>
        </tr>
        <tr>
            <td>Lastname:</td>
            <td>${requestScope.employee.lastName}</td>
        </tr>
        <tr>
            <td>Position:</td>
            <td>${requestScope.employee.position}</td>
        </tr>
    </table>
    <div>
        <form action="controller" method="post">
            <h4>Employee status is: ${requestScope.employee.status}</h4>
            <select class="inputForm" name="status" title="Status">
                <c:forEach items="${requestScope.statuses}" var="status">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="command" value="set_employee_status_command"/>
            <input type="hidden" name="eid" value="${requestScope.employee.eid}"/>
            <input class="button" type="submit" name="submit" value="set status"/>
        </form>
    </div>
    <div>
        <c:choose>
            <c:when test="${requestScope.permissionChangeDeleteStatus =='false'}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="delete_employee_command"/>
                    <input type="hidden" name="eid" value="${requestScope.employee.eid}"/>
                    <input class="button" type="submit" name="submit" value="delete"/>
                </form>
                <form action="updateemployee" method="post">
                    <input type="hidden" name="eid" value="${requestScope.employee.eid}"/>
                    <input class="button" type="submit" name="submit" value="edit"/>
                </form>
            </c:when>
            <c:otherwise>
                <h5>It is possible to change or delete employee details if there are no flights associated with this employee</h5>
                <h4>Last five employee flights</h4>
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
                            <td>${flight.departure.name}</td>
                            <td>${flight.arrival.name}</td>
                            <td>${flight.status}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
