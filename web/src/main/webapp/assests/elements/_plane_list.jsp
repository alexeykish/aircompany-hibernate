<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<div>
    <table class="list">
        <tr>
            <th>ID</th>
            <th>MODEL</th>
            <th>PASSENGER CAPACITY</th>
            <th>FLIGHT RANGE, KM</th>
        </tr>
        <c:forEach items="${requestScope.planes}" var="plane">
            <tr>
                <td>${plane.pid}</td>
                <td>${plane.model}</td>
                <td>${plane.capacity}</td>
                <td>${plane.range}</td>
                <td>${plane.status}</td>
                <td style="width: 70px;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="plane_report_command"/>
                        <input type="hidden" name="pid" value="${plane.pid}"/>
                        <input class="table-button" type="submit" name="submit" value="details"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="addplane" method="post">
        <input class="button" type="submit" name="submit" value="add plane"/>
    </form>
</div>
