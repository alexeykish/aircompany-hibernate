/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;

/**
 * Describes the utility class to test the Employee object before adding or changing it in the DB
 *
 * @author Kish Alexey
 */
public class EmployeeValidator implements IValidator<Employee> {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     *
     * @param employee - Employee object being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public String validate(Employee employee) {
        if (checkEmpty(employee)) {
            return Message.ERROR_EMPTY;
        }
        return null;
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param employee - Employee object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEmpty(Employee employee) {
        return (employee == null) ||
                (employee.getFirstName() == null) || (employee.getFirstName().equals("")) ||
                (employee.getLastName() == null) || (employee.getLastName().equals("")) ||
                (employee.getPosition() == null);
    }
}
