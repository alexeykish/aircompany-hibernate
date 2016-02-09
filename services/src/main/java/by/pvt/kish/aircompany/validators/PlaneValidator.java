package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;

/**
 * Describes the utility class to test the Plane object before adding or changing it in the DB
 *
 * @author Kish Alexey
 */
public class PlaneValidator implements IValidator<Plane> {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     *
     * @param plane - Plane object being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public String validate(Plane plane) {
        if (checkEmpty(plane)) {
            return Message.ERROR_EMPTY;
        }
        return null;
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param plane - Plane object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEmpty(Plane plane) {
        return (plane == null) ||
                (plane.getModel() == null) ||
                (plane.getModel().equals("")) ||
                (plane.getCapacity() < 1) ||
                (plane.getRange() < 1) ||
                (plane.getTeam() == null) ||
                (plane.getTeam().get(Position.PILOT) <= 0) ||
                (plane.getTeam().get(Position.NAVIGATOR) <= 0) ||
                (plane.getTeam().get(Position.RADIOOPERATOR) <= 0) ||
                (plane.getTeam().get(Position.STEWARDESS) <= 0);
    }
}
