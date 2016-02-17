<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="<c:url value="/assests/js/validation.js"/>"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/assests/css/style.css"/>"/>
</head>
<body>

<div class="container">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="register_user_command"/>
        <h2 style="text-align: center;">Registration</h2>
        <table class="add_user_table">
            <tr>
                <td class="input-label">Firstname:</td>
                <td><input class="inputForm" type="text" name="first_name" id="first_nameForm"
                           onKeyUp="check('first_name')" maxlength="15"/> <br/>
                    <p class="reg-hint" id="first_name">Latin or cyrillic letters, 2-15 symbols</p></td>
            </tr>
            <tr>
                <td class="input-label">Lastname:</td>
                <td><input class="inputForm" type="text" name="last_name" id="last_nameForm"
                           onKeyUp="check('last_name')" maxlength="15"/> <br/>
                    <p class="reg-hint" id="last_name">Latin or cyrillic letters, 2-15 symbols</p></td>
            </tr>
            <tr>
                <td class="input-label">Login:</td>
                <td><input class="inputForm" type="text" name="login" id="loginForm"
                           onKeyUp="check('login')" maxlength="10"/>
                    <p class="reg-hint" id="login">Latin letters or digits, 3-10 symbols</p></td>
            </tr>
            <tr>
                <td class="input-label">Password:</td>
                <td><input class="inputForm" type="password" name="password" id="passwordForm"
                           onKeyUp="check('password')" maxlength="15"/> <br/>
                    <p class="reg-hint" id="password">Latin letters, digits, *, ! or ^, 6-15 symbols</p></td>
            </tr>
            <tr>
                <td class="input-label">E-mail:</td>
                <td><input class="inputForm" type="text" name="email" id="emailForm" onKeyUp="check('email')"/> <br/>
                    <p class="reg-hint" id="email">Email is not valid.</p></td>
            </tr>
            <tr>
                <td class="input-label">User type:</td>
                <td>
                    <select class="inputForm" name="user_type">
                        <c:forEach items="${requestScope.types}" var="type">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input class="button" type="submit" name="submit" value="Register"/>
        </form>
    <form action="showindex" method="get">
        <input class="button" type="submit" name="submit" value="Back"/>
    </form>
</div>
</body>
</html>
