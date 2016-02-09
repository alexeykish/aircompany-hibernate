package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
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
public class EmployeeReportCommand implements by.pvt.kish.aircompany.command.ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = EmployeeReportCommand.class.getName();
        try {
            Long id = RequestHandler.getId(request, "eid");
            Employee employee = EmployeeService.getInstance().getById(id);
            List<Flight> flights = FlightService.getInstance().getEmployeeLastFiveFlights(employee.getEid());
            boolean permissionChangeDeleteStatus = flights.size() != 0;
            List<EmployeeStatus> employeeStatuses = Arrays.asList(EmployeeStatus.values());

            request.setAttribute(Attribute.EMPLOYEE_ATTRIBUTE, employee);
            request.setAttribute(Attribute.FLIGHTS_ATTRIBUTE, flights);
            request.setAttribute(Attribute.STATUSES_ATTRIBUTE, employeeStatuses);
            request.setAttribute(Attribute.PERMISSION_CHANGE_DELETE_STATUS_ATTRIBUTE, permissionChangeDeleteStatus);

            return Page.EMPLOYEE_REPORT;
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
    }
}
