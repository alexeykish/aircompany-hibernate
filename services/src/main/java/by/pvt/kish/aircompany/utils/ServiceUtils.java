package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.IDAO;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.validators.IValidator;

import java.util.List;

/**
 * @author Kish Alexey
 */
public class ServiceUtils {

    public static <T> Long addEntity(IDAO<T> dao, T t, IValidator<T> validator) throws ServiceValidateException, ServiceException {
        try {
            String validateResult = validator.validate(t);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            return dao.add(t);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static <T> void updateEntity(IDAO<T> dao, T t, IValidator<T> validator) throws ServiceValidateException, ServiceException {
        try {
            String validateResult = validator.validate(t);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            dao.update(t);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static <T> void deleteEntity(IDAO<T> dao, Long id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static <T> List<T> getAllEntities(IDAO<T> dao) throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static <T> T getByIdEntity(IDAO<T> dao, Long id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
