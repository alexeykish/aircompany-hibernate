package by.pvt.kish.aircompany.constants;

/**
 * @author Kish Alexey
 */
public class Message {
    public static final String SUCCESS_ADD_FLIGHT = "The flight was successfully added";
    public static final String SUCCESS_DELETE_FLIGHT = "The flight was successfully deleted";
    public static final String SUCCESS_UPDATE_FLIGHT = "The flight was successfully updated";

    public static final String SUCCESS_TEAM_CHANGE = "The flight team was successfully changed";
    public static final String SUCCESS_DELETE_TEAM = "The flight team was successfully deleted";

    public static final String SUCCESS_ADD_EMPLOYEE = "The employee was successfully added";
    public static final String SUCCESS_DELETE_EMPLOYEE = "The employee was successfully deleted";
    public static final String SUCCESS_UPDATE_EMPLOYEE = "The employee was successfully updated";
    public static final String SUCCESS_SET_STATUS_EMPLOYEE = "The employee status was successfully changed";

    public static final String SUCCESS_ADD_PLANE = "The plane was successfully added";
    public static final String SUCCESS_DELETE_PLANE = "The plane was successfully deleted";
    public static final String SUCCESS_SET_STATUS_PLANE = "The plane status was successfully changed";

    public static final String SUCCESS_REG = "You have successfully registered";

    public static final String MALFORMED_FIRSTNAME = "Incorrectly filled 'Firstname' field";
    public static final String MALFORMED_LASTNAME = "Incorrectly filled 'Lastname' field";
    public static final String MALFORMED_LOGIN = "Incorrectly filled 'Login' field";
    public static final String MALFORMED_PASSWORD = "Password should contain six or more symbols";
    public static final String MALFORMED_EMAIL = "Incorrectly filled 'e-mail' field";

    public static final String ERROR_REG_LOGIN = "Wrong login name or password";
    public static final String ERROR_REG_USER_EXISTS = "User with this login already authorized";
    public static final String ERROR_REG_EMPTY= "Empty data at login or password";
    public static final String ERROR_SQL = "Something wrong with the database, please check connection";
    public static final String ERROR_EMPTY = "Empty data at request";
    public static final String ERROR_ID_MISSING = "Missing id parameter";
    public static final String ERROR_REG_DATA = "Incorrect data at user registration";
    public static final String ERROR_FLIGHT_VALID = "Action canceled! Place of departure and place of arrival should be different";
    public static final String ERROR_TEAM_VALID = "Action canceled! Duplication in the composition of the team members";
    public static final String ERROR_MESSAGE = "Opps! Something gone wrong... Check log file.";
    public static final String ERROR_SQL_DAO = "SQL exception at IDAO";
    public static final String ERROR_IAE = "IllegalArgument Exception: DATE field";
    public static final String USER_LOGOUT = "(Session invalidation) Logout of user";
    public static final String ERROR_TEAM_POSITIONS_VALID = "Action canceled! Members of the team must be at correct positions";
    public static final String ERR_ACCESS = "Access denied";
    public static final String ERROR_REG_LOGOUT = "User logout failed";
    public static final String ERROR_USER_STATUS = "Set user status failed";
    public static final String ERROR_FLIGHT_DATE = "Adding or updating of the flight prohibited, when departure date is earlier than the current";
    public static final String ERROR_STATUS = "Empty status in request";
    public static final String ERROR_TEAM_MEMBER_VALID = "Employee is already busy in the other team at this day";
}

