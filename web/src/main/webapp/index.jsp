<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="assests/css/style.css"/>
</head>
<body>
<div>
    <div style="text-align: center;">
        <div style="color: red; font-size: 16px;">Aircompany</div>
    </div>
    <div style="text-align: center; font-size: 14px;">
        <div>
            Система Авиакомпания.
        </div>
        <div>
            Авиакомпания имеет список рейсов.
        </div>
        <div>
            Диспетчер формирует летную Бригаду (пилоты, штурман, радист, стюардессы)на Рейс.
        </div>
        <div>
            Администратор управляет списком рейсов.
        </div>
    </div>
    <div style="text-align: center;">
        <%@include file="/assests/elements/_auth.jsp" %>
    </div>
    <div>
        <p style="text-align: center; font-size: 12px; color: red;">${requestScope.login_message}
        </p>
    </div>
</div>
</body>
</html>
