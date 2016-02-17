<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<div>
    <table class="list">
        <tr>
            <th>ID</th>
            <th>CITY</th>
        </tr>
        <c:forEach items="${requestScope.airports}" var="airport">
            <tr>
                <td>${airport.aid}</td>
                <td>${airport.name}</td>
                <td style="width: 70px;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="delete_airport_command"/>
                        <input type="hidden" name="aid" value="${airport.aid}"/>
                        <input class="table-button" type="submit" name="submit" value="delete" disabled/>
                    </form>
                </td>
                <td style="width: 70px;">
                    <form action="updateairport" method="post">
                        <input type="hidden" name="aid" value="${airport.aid}"/>
                        <input class="table-button" type="submit" name="submit" value="edit" disabled/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="addairport" method="post">
        <input class="button" type="submit" name="submit" value="add airport" disabled/>
    </form>
</div>
