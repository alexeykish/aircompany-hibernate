package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.exceptions.RequestHandlerException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.services.impl.UserService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kish Alexey
 */
public class LoginUserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = LoginUserCommand.class.getSimpleName();
		HttpSession session = request.getSession();
		try {
			String login = RequestHandler.getString(request, "login");
			String password = RequestHandler.getString(request, "password");
			User user = UserService.getInstance().getUser(login, password);
			switch (user.getUserType()) {
				case ADMINISTRATOR:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 2); // TODO access level
					break;
				case DISPATCHER:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 1); // TODO access level
					break;
				default:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 0); // TODO access level
					break;
			}
			session.setAttribute(Attribute.USER_ATTRIBUTE, user);
			Cookie c = new Cookie("uid", String.valueOf(user.getUid()));
			int sessionAge = 60 * 60;
			c.setMaxAge(sessionAge);
			response.addCookie(c);
			return Page.MAIN;
		} catch (ServiceException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		} catch (RequestHandlerException e) {
			return ErrorHandler.returnLoginErrorPage(request, e.getMessage(), className);
		} catch (ServiceLoginException e) {
			return ErrorHandler.returnLoginErrorPage(request, e.getMessage(), className);
		}
	}
}
