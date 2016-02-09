package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightReportCommand implements by.pvt.kish.aircompany.command.ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = FlightReportCommand.class.getName();
        try {
            Long id = RequestHandler.getId(request, "fid");
            Flight flight = FlightService.getInstance().getById(id);
            List<FlightStatus> flightStatuses = Arrays.asList(FlightStatus.values());
            request.setAttribute(Attribute.FLIGHT_ATTRIBUTE, flight);
            request.setAttribute(Attribute.STATUSES_ATTRIBUTE, flightStatuses);
            return Page.FLIGHT_REPORT;
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
    }
}
