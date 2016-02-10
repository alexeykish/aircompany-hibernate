/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.User;

import static by.pvt.kish.aircompany.validators.FormDataValidator.*;

/**
 * Describes the utility class to test the User object before adding or changing it in the DB
 *
 * @author Kish Alexey
 */
public class UserValidator implements IValidator<User> {

    /**
     * Verifies that the user entered data pattern
     * Check the validity of:
     * <li>the presence of empty fields</li>
     *
     * @param user - User object being checked
     * @return - null, if everything checks out correctly; if the data is incorrect - the corresponding line indicating the error
     */
    public String validate(User user) {

        if (checkEmpty(user)) {
            return Message.ERROR_EMPTY;
        }
        if (!namePattern.matcher(user.getFirstName()).matches()) {
            return Message.MALFORMED_FIRSTNAME;
        }
        if (!namePattern.matcher(user.getLastName()).matches()) {
            return Message.MALFORMED_LASTNAME;
        }
        if (!loginPattern.matcher(user.getLogin()).matches()) {
            return Message.MALFORMED_LOGIN;
        }
        if (!passwordPattern.matcher(user.getPassword()).matches()) {
            return Message.MALFORMED_PASSWORD;
        }
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            return Message.MALFORMED_EMAIL;
        }
        return null;
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param user - User object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEmpty(User user) {
        return (user == null) ||
                (user.getFirstName() == null) || (user.getFirstName().equals("")) ||
                (user.getLastName() == null) || (user.getLastName().equals("")) ||
                (user.getLogin() == null) || (user.getLogin().equals("")) ||
                (user.getPassword() == null) || (user.getPassword().equals("")) ||
                (user.getEmail() == null) || (user.getEmail().equals("")) ||
                (user.getUserType() == null) ||
                (user.getStatus() == null);
    }
}
