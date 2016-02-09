package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

/**
 * This interface represents a contract for a IDAO for the {@link User} model.
 * Note that all methods which returns the {@link User} from the DB, will not
 * fill the model with the password, due to security reasons.
 *
 * @author Kish Alexey
 */
public interface IUserDAO {

    /**
     * Checks unique login to the DB
     *
     * @param login - checked login
     * @return false is login exists, true is don`t
     * @throws DaoException If something fails at DB level
     */
    boolean checkLogin(String login) throws DaoException;

    /**
     * Returns the user from the DB matching the given login and password
     *
     * @param login    - login of the user to be returned
     * @param password - password of the user to be returned
     * @return The user from the DB matching the given login and password
     * @throws DaoException If something fails at DB level
     */
    User getUser(String login, String password) throws DaoException;

    /**
     * Checks user authorisation status
     *
     * @param id - The ID of the user to be checked
     * @return true is status is ONLINE, false if status is OFFLINE
     * @throws DaoException If something fails at DB level
     */
    boolean checkStatus(Long id) throws DaoException;

    /**
     * Set user status to DB
     *
     * @param id     - The ID of the user to be set
     * @param status - The status to be set
     * @throws DaoException If something fails at DB level
     */
    void setStatus(Long id, UserStatus status) throws DaoException;
}
