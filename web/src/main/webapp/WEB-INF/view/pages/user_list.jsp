<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/assests/css/style.css"/>"/>
</head>
<body>
<div id="header">
    <%@include file="../elements/_header.jsp" %>
</div>
<table style="border-collapse: collapse;">
    <tbody>
    <tr>
        <td id="left-content" class="left-content">
            <div>
                <%@include file="../elements/_adminmenu.jsp" %>
            </div>
        </td>
        <td id="right-content" class="right-content">
            <div>
                <%@include file="../elements/_user_list.jsp" %>
            </div>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>