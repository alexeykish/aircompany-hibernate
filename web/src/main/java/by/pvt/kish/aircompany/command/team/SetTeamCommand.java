/**
 *
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.services.impl.TeamService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.utils.TeamCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SetTeamCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = SetTeamCommand.class.getSimpleName();
        try {
            Long id = RequestHandler.getId(request, "fid");
            List<Employee> team = TeamService.getInstance().getById(id);
            Flight flight = FlightService.getInstance().getById(id);
            List<Employee> employees = EmployeeService.getInstance().getAllAvailable(flight.getDate());
            List<String> positions = TeamCreator.getPlanePositions(PlaneService.getInstance().getById(flight.getPlane().getPid()));
            request.setAttribute(Attribute.FLIGHT_ATTRIBUTE, flight);
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
            if (team.size() != 0) {
                request.setAttribute(Attribute.TEAM_ATTRIBUTE, team);
                return Page.CHANGE_TEAM;
            }
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
        return Page.SET_TEAM;
    }
}
