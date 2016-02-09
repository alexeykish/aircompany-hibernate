<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div>
    <table>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="get_all_flights_command"/>
                    <input class="button"
                           type="submit"
                           name="submit"
                           value="Flights"/>
                </form>
            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="get_all_employees_command"/>
                    <input class="button"
                           type="submit"
                           name="submit"
                           value="Employees"/>
                </form>
            </td>
        </tr>
    </table>
</div>
