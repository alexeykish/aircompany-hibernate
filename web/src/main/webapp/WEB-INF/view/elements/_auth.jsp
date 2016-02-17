<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--<script type="text/javascript" src="js/validation.js"></script>-->

<div class="container">
	<p>Authorization</p>
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<form action="controller" method="post" autocomplete="off">
				<input type="hidden" name="command" value="login_user_command" />
				<div>
					<input class="inputForm" id="loginAuthForm" name="login"
						type="text" title="loginAuthForm"/>
				</div>
				<div>
					<input class="inputForm" id="passAuthForm" name="password"
						type="password"  title="passAuthForm"/>
				</div>
				<div>
					<input class="button" type="submit" value="login" />
				</div>
				<div>
					<a href="registration"
						style="font-size: 10px; color: #666666; margin: 5px;">registration</a>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="user-container" style="text-align: left;">
				<p style="font-size: 12px">Current user:
					${sessionScope.user.login}</p>
				<p style="font-size: 12px">User type:
					${sessionScope.user.userType}</p>
				<form action="controller" method="post" autocomplete="off">
					<input type="hidden" name="command" value="logout_user_command" /> <input
						class="button" type="submit" value="logout" />
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</div>