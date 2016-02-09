<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="margin:5px;">
    <h2>Add Flight</h2>
    <form name="addflights" action="controller" method="post">
        <input type="hidden" name="command" value="add_flight_command"/>
        <c:set var="now" value="<%=new java.util.Date()%>"/>
        <table>
            <tr>
                <td class="input-label">Date:</td>
                <td><input class="inputForm" required type="text" name="date" value="<fmt:formatDate pattern="yyyy-MM-dd"
            value="${now}"/>" title="Date"/></td>
            </tr>
            <tr>
                <td class="input-label">Departure place:</td>
                <td><select class="inputForm" name="from">
                    <c:forEach items="${requestScope.airports}" var="airport">
                        <option value="${airport.aid}">${airport.city}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td class="input-label">Arrival place:</td>
                <td><select class="inputForm" name="to">
                    <c:forEach items="${requestScope.airports}" var="airport">
                        <option value="${airport.aid}">${airport.city}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td class="input-label">Plane:</td>
                <td><select class="inputForm" name="pid">
                    <c:forEach items="${requestScope.planes}" var="plane">
                        <option value="${plane.pid}">${plane.model}</option>
                    </c:forEach>
                </select></td>
            </tr>
        </table>
        <input class="button" type="submit" name="submit" value="add flight"/>
    </form>
</div>
