<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>

<div style="margin: 5px;">
    <h2>Update Employee</h2>
    <form name="updateemployee" action="controller" method="post">
        <input type="hidden" name="command" value="update_employee_command"/>
        <input type="hidden" name="eid" value="${requestScope.employee.eid}"/>
        <table>
            <tr class="input-label">
                <td>ID:</td>
                <td>${requestScope.employee.eid}</td>
            </tr>
            <tr>
                <td class="input-label">Firstname:</td>
                <td><input class="inputForm" type="text" name="firstName" value="${requestScope.employee.firstName}" title="Firstname"/></td>
            </tr>
            <tr>
                <td class="input-label">Lastname:</td>
                <td><input class="inputForm" type="text" name="lastName" value="${requestScope.employee.lastName}" title="Lastname"/></td>
            </tr>
            <tr>
                <td class="input-label">Position:</td>
                <td><select class="inputForm" name="position" title="Position">
                    <option value="${requestScope.employee.position}">${requestScope.employee.position}</option>
                    <c:forEach items="${requestScope.positions}" var="position">
                        <c:if test="${requestScope.employee.position != position}">
                            <option value="${position}">${position}</option>
                        </c:if>
                    </c:forEach>
                </select></td>
            </tr>
        </table>
        <input class="button" type="submit" name="submit" value="update employee"/>
    </form>
</div>
