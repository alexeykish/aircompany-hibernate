package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.impl.UserService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kish Alexey
 */
public class LogoutUserCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(LogoutUserCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession currentSession = request.getSession();
		String className = LogoutUserCommand.class.getName();
		if (currentSession != null) {
			try {
				User user = (User)currentSession.getAttribute(Attribute.USER_ATTRIBUTE);
				UserService.getInstance().setStatus(user.getUid(), UserStatus.OFFLINE);
				currentSession.invalidate();
				logger.info(Message.USER_LOGOUT);
			} catch (ServiceException e) {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.ERROR_REG_LOGOUT);
				return ErrorHandler.returnErrorPage(e.getMessage(), className);
			} catch (ServiceValidateException e) {
				return ErrorHandler.returnLoginErrorPage(request, e.getMessage(), className);
			}
		}
		return Page.INDEX;
	}
}
