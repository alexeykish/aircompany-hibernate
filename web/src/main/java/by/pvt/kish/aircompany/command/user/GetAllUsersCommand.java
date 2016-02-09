/**
 * 
 */
package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.UserService;
import by.pvt.kish.aircompany.utils.ErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllUsersCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = LoginUserCommand.class.getSimpleName();
		try {
			List<User> users = UserService.getInstance().getAll();
			request.setAttribute(Attribute.USERS_ATTRIBUTE, users);
		} catch (ServiceException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		}
		return Page.USERS;
	}
}
