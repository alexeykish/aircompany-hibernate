<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div style="margin: 5px;">
    <c:set var="totalRecords" value="0"/>
    <h2>Add flight team to flight</h2>
    <form name="setteam" action="controller" method="post">
        <input type="hidden" name="command" value="save_team_to_flight_command"/>
        <table>
            <tr>
                <td class="input-label">Flight id:</td>
                <td><input class="inputForm" type="text" name="fid" value="${requestScope.flight.fid}" readonly
                           title="Flight id"/></td>
            </tr>
            <tr>
                <td class="input-label">Flight team:</td>
            </tr>
            <c:forEach items="${requestScope.positions}" var="position" varStatus="loop">
                <tr>
                    <td class="input-label">${position}</td>
                    <td>
                        <select class="inputForm" name="${loop.index}" title="Flight team">
                            <c:forEach items="${requestScope.employees}" var="employee">
                                <c:if test="${(employee.position == position)}">
                                    <option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <c:if test="${loop.last}">
                    <c:set var="totalRecords" value="${loop.count}"/>
                    <input type="hidden" name="num" value= ${totalRecords}>
                </c:if>
                <tr>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
        <input class="button" type="submit" name="submit" value="save flight team to flight"/>
    </form>
</div>
