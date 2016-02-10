package by.pvt.kish.aircompany.command.airport;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Airport;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.AirportService;
import by.pvt.kish.aircompany.utils.ErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllAirportsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = GetAllAirportsCommand.class.getName();
        try {
            List<Airport> airports = AirportService.getInstance().getAll();
            request.setAttribute(Attribute.AIRPORTS_ATTRIBUTE, airports);
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
        return Page.AIRPORTS;
    }
}
