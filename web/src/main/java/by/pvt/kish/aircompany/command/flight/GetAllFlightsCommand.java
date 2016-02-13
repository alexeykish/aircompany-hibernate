/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.validators.FlightStatusValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllFlightsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = GetAllFlightsCommand.class.getSimpleName();
		int recordsPerPage = 5;
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		try {
			int noOfRecords = FlightService.getInstance().getCount();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			FlightStatusValidator.updateFlightsStatus();
			List<Flight> flights = FlightService.getInstance().getAllToPage(recordsPerPage, currentPage);
			request.setAttribute(Attribute.FLIGHTS_ATTRIBUTE, flights);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", currentPage);
		} catch (ServiceException e) {
			return ErrorHandler.returnErrorPage(e.getMessage(), className);
		}
		return Page.FLIGHTS;
	}

}
