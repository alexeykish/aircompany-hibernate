<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="user-container">
	<div style="font-size: 12px;">Current user:
		${sessionScope.user.login}</div>
	<div style="font-size: 12px;">User type:
		${sessionScope.user.userType}</div>
	<div style="margin:0 auto;">
		<form action="controller" method="post" autocomplete="off">
			<input type="hidden" name="command" value="logout_user_command" /> <input
				class="button" type="submit" value="logout" />
		</form>
	</div>
</div>