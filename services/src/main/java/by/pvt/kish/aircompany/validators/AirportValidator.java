package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.Airport;

/**
 * Describes the utility class to test the Airport object before adding or changing it in the DB
 *
 * @author Kish Alexey
 */
public class AirportValidator implements IValidator<Airport> {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     *
     * @param airport - Airport object being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public String validate(Airport airport) {
        if (checkEmpty(airport)) {
            return Message.ERROR_EMPTY;
        }
        return null;
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param airport - Airport object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEmpty(Airport airport) {
        return (airport == null) ||
                (airport.getName() == null);
    }
}
