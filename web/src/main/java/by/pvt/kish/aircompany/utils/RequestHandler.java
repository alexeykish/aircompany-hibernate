package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.exceptions.RequestHandlerException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.AirportService;
import by.pvt.kish.aircompany.services.impl.PlaneService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kish Alexey
 */
public class RequestHandler {

    public static Flight getFlight(HttpServletRequest request) throws ServiceException {
        Flight flight = new Flight();
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String pid = request.getParameter("pid");
        if (checkNull(date) ||
                checkNull(from) ||
                checkNull(to) ||
                checkNull(pid)) {
            return null;
        }
        flight.setDate(Date.valueOf(date.trim()));
        flight.setTo(AirportService.getInstance().getById(Long.parseLong(to.trim())));
        flight.setFrom(AirportService.getInstance().getById(Long.parseLong(from.trim())));
        flight.setPlane(PlaneService.getInstance().getById(Long.parseLong(pid.trim())));
        Long id = RequestHandler.getId(request, "fid");
        if (id > 0) {
            flight.setFid(id);
            FlightStatus status = RequestHandler.getFlightStatus(request);
            flight.setStatus(status);
        }
        return flight;
    }

    private static boolean checkNull(String string) {
        return string == null || string.trim().equals("");
    }

    public static Long getId(HttpServletRequest request, String requestId) {
        String id = request.getParameter(requestId);
        if (id == null) {
            return (long) -1;
        }
        return Long.parseLong(id);
    }

    public static FlightStatus getFlightStatus(HttpServletRequest request) throws ServiceException {
        String status = request.getParameter("status");
        if (status == null) {
            throw new ServiceException(Message.ERROR_STATUS);
        }
        return FlightStatus.valueOf(status);
    }

    public static Employee getEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String position = request.getParameter("position");
        if (checkNull(firstName) || checkNull(lastName) || checkNull(position)) {
            return null;
        }
        employee.setFirstName(firstName.trim());
        employee.setLastName(lastName.trim());
        employee.setPosition(Position.valueOf(position.trim()));
        Long id = RequestHandler.getId(request, "eid");
        if (id > 0) {
            employee.setEid(id);
        }
        return employee;
    }

    public static Plane getPlane(HttpServletRequest request) {
        Plane plane = new Plane();
        String model = request.getParameter("model");
        String capacity = request.getParameter("capacity");
        String range = request.getParameter("range");
        String num_pilots = request.getParameter("num_pilots");
        String num_navigators = request.getParameter("num_navigators");
        String num_radiooperators = request.getParameter("num_radiooperators");
        String num_stewardess = request.getParameter("num_stewardess");
        if (checkNull(model) ||
                checkNull(capacity) ||
                checkNull(range) ||
                checkNull(num_pilots) ||
                checkNull(num_navigators) ||
                checkNull(num_radiooperators) ||
                checkNull(num_stewardess)) {
            return null;
        }
        plane.setModel(model.trim());
        plane.setCapacity(Integer.parseInt(capacity.trim()));
        plane.setRange(Integer.parseInt(range.trim()));
        Map<Position, Integer> team = new HashMap<>();
        team.put(Position.PILOT, Integer.parseInt(num_pilots));
        team.put(Position.NAVIGATOR, Integer.parseInt(num_navigators));
        team.put(Position.RADIOOPERATOR, Integer.parseInt(num_navigators));
        team.put(Position.STEWARDESS, Integer.parseInt(num_stewardess));
        plane.setPlaneCrew(team);
        Long id = RequestHandler.getId(request, "pid");
        if (id > 0) {
            plane.setPid(id);
        }
        return plane;
    }

    public static User getUser(HttpServletRequest request) {
        User user = new User();
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String userType = request.getParameter("user_type");
        if (checkNull(firstname) ||
                checkNull(lastname) ||
                checkNull(login) ||
                checkNull(password) ||
                checkNull(email) ||
                checkNull(userType)) {
            return null;
        }
        user.setFirstName(firstname.trim());
        user.setLastName(lastname.trim());
        user.setLogin(login.trim());
        user.setPassword(password.trim());
        user.setEmail(email.trim());
        user.setUserType(UserType.valueOf(userType.trim()));
        Long id = RequestHandler.getId(request, "uid");
        if (id > 0) {
            user.setUid(id);
            String status = request.getParameter("status");
            if (checkNull(status)) {
                return null;
            }
            user.setStatus(UserStatus.valueOf(status));
        }
        return user;
    }

    public static String getString(HttpServletRequest request, String parameter) throws RequestHandlerException {
        String result = request.getParameter(parameter);
        if (checkNull(result)) {
            throw new RequestHandlerException(parameter + " is empty");
        }
        return result;
    }

    public static int getInt(HttpServletRequest request, String parameter) throws RequestHandlerException {
        int result = Integer.parseInt(request.getParameter(parameter));
        if (result < 0) {
            throw new RequestHandlerException(parameter + " is less than zero");
        }
        return result;
    }

    public static List<Long> getTeam(HttpServletRequest request, int count) {
            List<Long> team = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                team.add(Long.parseLong(request.getParameter(String.valueOf(i))));
            }
            return team;
    }
}
