package by.pvt.kish.aircompany.command.plane;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kish Alexey
 */
public class PlaneReportCommand implements by.pvt.kish.aircompany.command.ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = PlaneReportCommand.class.getName();
        try {
            Long id = RequestHandler.getId(request, "pid");
            Plane plane = PlaneService.getInstance().getById(id);
            Map<String, Integer> team = new HashMap<>();
            team.put(Position.PILOT.toString(), plane.getPlaneCrew().get(Position.PILOT));
            team.put(Position.NAVIGATOR.toString(), plane.getPlaneCrew().get(Position.NAVIGATOR));
            team.put(Position.RADIOOPERATOR.toString(), plane.getPlaneCrew().get(Position.RADIOOPERATOR));
            team.put(Position.STEWARDESS.toString(), plane.getPlaneCrew().get(Position.STEWARDESS));
            List<Flight> flights = FlightService.getInstance().getPlaneLastFiveFlights(plane.getPid());
            boolean permissionChangeDeleteStatus = flights.size() != 0;
            List<PlaneStatus> planeStatuses = Arrays.asList(PlaneStatus.values());
            request.setAttribute(Attribute.PLANE_ATTRIBUTE, plane);
            request.setAttribute(Attribute.TEAM_ATTRIBUTE, team);
            request.setAttribute(Attribute.FLIGHTS_ATTRIBUTE, flights);
            request.setAttribute(Attribute.STATUSES_ATTRIBUTE, planeStatuses);
            request.setAttribute(Attribute.PERMISSION_CHANGE_DELETE_STATUS_ATTRIBUTE, permissionChangeDeleteStatus);
            return Page.PLANE_REPORT;
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
    }
}
