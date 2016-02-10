package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.UserDAO;
import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.IUserService;
import by.pvt.kish.aircompany.validators.UserValidator;

import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IUserService interface for user model.
 *
 * @author Kish Alexey
 */
public class UserService extends BaseService<User> implements IUserService {

    private static UserService instance;
    private UserDAO userDAO = UserDAO.getInstance();
    private UserValidator userValidator = new UserValidator();

    /**
     * Returns an synchronized instance of a UserService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a UserService
     */
    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public Long add(User user) throws ServiceException, ServiceLoginException, ServiceValidateException {
        try {
            String validateResult = userValidator.validate(user);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            if (!userDAO.checkLogin(user.getLogin())) {
                throw new ServiceLoginException(Message.ERROR_REG_USER_EXISTS);
            } else {
                return userDAO.add(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void update(User user) throws ServiceException, ServiceValidateException {
        updateEntity(userDAO, user, userValidator);
    }

    @Override
    public List<User> getAll() throws ServiceException {
        return getAllEntities(userDAO);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        deleteEntity(userDAO, id);
    }

    @Override
    public User getById(Long id) throws ServiceException {
        return getByIdEntity(userDAO, id);
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        try {
            return userDAO.checkLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(String login, String password) throws ServiceException, ServiceLoginException {
        User user;
        try {
            user = userDAO.getUser(login, password);
            if (user == null) {
                throw new ServiceLoginException(Message.ERROR_REG_LOGIN);
            } else if (user.getStatus().equals(UserStatus.ONLINE)) {
                throw new ServiceLoginException(Message.ERROR_REG_USER_EXISTS);
            } else {
                userDAO.setStatus(user.getUid(), UserStatus.ONLINE);
                user.setStatus(UserStatus.ONLINE);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void setStatus(Long uid, UserStatus status) throws ServiceException, ServiceValidateException {
        try {
            if (uid <= 0) {
                throw new ServiceValidateException(Message.ERROR_USER_STATUS);
            }
            userDAO.setStatus(uid, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
