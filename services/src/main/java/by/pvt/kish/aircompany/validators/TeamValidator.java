/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.services.impl.TeamService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describes the utility class to test the flight team before adding or changing it in the DB
 *
 * @author Kish Alexey, 2015
 */
public class TeamValidator {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     * <li>one and the same employee should not be in command at several positions</li>
     * <li>each employee must be located at a position corresponding to its position</li>
     * <li>each employee must be free from another flights at flights date</li>
     *
     * @param id   - The flight id
     * @param team - The flight team being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public static String validate(Long id, List<Long> team) throws ServiceException {
        if (checkEmpty(team)) {
            return Message.ERROR_EMPTY;
        }
        if (checkEntry(team)) {
            return Message.ERROR_TEAM_VALID;
        }
        if (checkPositions(id, team)) {
            return Message.ERROR_TEAM_POSITIONS_VALID;
        }
        if (checkEmployee(id, team)) {
            return Message.ERROR_TEAM_MEMBER_VALID;
        }
        return null;
    }


    /**
     * The method checks the position of employees to their positions on the team
     *
     * @param team - The flight team being checked
     * @return false if it corresponds to the position correctly, true if found at least one to inadequate
     */
    private static boolean checkPositions(Long fid, List<Long> team) throws ServiceException {
        List<Employee> list = new ArrayList<>();
        for (Long i : team) {
            list.add(EmployeeService.getInstance().getById(i));
        }
        Plane plane = FlightService.getInstance().getById(fid).getPlane();
        int num_pilots = 0;
        int num_navigators = 0;
        int num_radiooperators = 0;
        int num_stewardesses = 0;
        for (Employee e : list) {
            if (e.getPosition().equals(Position.PILOT)) {
                num_pilots++;
            }
            if (e.getPosition().equals(Position.NAVIGATOR)) {
                num_navigators++;
            }
            if (e.getPosition().equals(Position.RADIOOPERATOR)) {
                num_radiooperators++;
            }
            if (e.getPosition().equals(Position.STEWARDESS)) {
                num_stewardesses++;
            }
        }
        return num_pilots != plane.getPlaneCrew().get(Position.PILOT) ||
                num_navigators != plane.getPlaneCrew().get(Position.NAVIGATOR) ||
                num_radiooperators != plane.getPlaneCrew().get(Position.RADIOOPERATOR) ||
                num_stewardesses != plane.getPlaneCrew().get(Position.STEWARDESS);
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param team - The flight team being checked
     * @return - false if all positions are filled, true if one of the items is null
     */
    private static boolean checkEmpty(List<Long> team) {
        if (team == null) {
            return true;
        }
        for (Long i : team) {
            if (i == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method checks the uniqueness of each employee in the team, the employee should take no more than one position
     *
     * @param team - The flight team being checked
     * @return - false all items are unique, true if a match is found
     */
    private static boolean checkEntry(List<Long> team) {
        Set<Long> set = new HashSet<>(team);
        return set.size() != team.size();
    }

    public static boolean checkEmployee(Long id, List<Long> team) throws ServiceException {
        Date flightDate = FlightService.getInstance().getById(id).getDate();
        for (Long eid : team) {
            if (TeamService.getInstance().checkEmployeeAvailability(eid, flightDate)) {
                return true;
            }
        }
        return false;
    }
}
