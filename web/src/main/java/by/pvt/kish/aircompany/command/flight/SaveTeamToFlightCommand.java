/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.exceptions.RequestHandlerException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SaveTeamToFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = UpdateFlightCommand.class.getSimpleName();
		try {
			Long id = RequestHandler.getId(request, "fid");
			int num = RequestHandler.getInt(request, "num");
			List<Long> team = RequestHandler.getTeam(request, num);
			FlightService.getInstance().addTeam(id, team);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_TEAM_CHANGE);
		} catch (ServiceException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		} catch (RequestHandlerException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		}
		return Page.MAIN;
	}

}
