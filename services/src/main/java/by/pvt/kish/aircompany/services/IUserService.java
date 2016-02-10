package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;

/**
 * This interface represents a contract for a IUserService for the {@link User} model.
 *
 * @author Kish Alexey
 */
public interface IUserService {

    /**
     * Checks unique login of the user
     *
     * @param login - checked login
     * @return false is login exists, true is don`t
     * @throws ServiceException - if something fails at Service layer
     */
    boolean checkLogin(String login) throws ServiceException;

    /**
     * Returns the user matching the given login and password
     *
     * @param login    - login of the user to be returned
     * @param password - password of the user to be returned
     * @return The user matching the given login and password
     * @throws ServiceException      - if something fails at Service layer
     * @throws ServiceLoginException - if something fails at Service user authorisation
     */
    User getUser(String login, String password) throws ServiceException, ServiceLoginException;

    /**
     * Set user status to DB
     *
     * @param id     - The ID of the user to be set
     * @param status - The status to be set
     * @throws ServiceException         - if something fails at Service layer
     * @throws ServiceValidateException - if something fails at Service validation
     */
    void setStatus(Long id, UserStatus status) throws ServiceException, ServiceValidateException;
}
