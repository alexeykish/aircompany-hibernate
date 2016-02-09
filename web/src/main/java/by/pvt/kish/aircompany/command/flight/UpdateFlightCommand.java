package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public class UpdateFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = UpdateFlightCommand.class.getSimpleName();
		try {
			Flight flight = RequestHandler.getFlight(request);
			FlightService.getInstance().update(flight);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_FLIGHT);
		} catch (IllegalArgumentException e) {
			return ErrorHandler.returnErrorPage(Message.ERROR_IAE, className);
		} catch (ServiceException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		} catch (ServiceValidateException e) {
			return ErrorHandler.returnValidateErrorPage(request, e.getMessage(), AddFlightCommand.class.getName());
		}
		return Page.MAIN;
	}
}
