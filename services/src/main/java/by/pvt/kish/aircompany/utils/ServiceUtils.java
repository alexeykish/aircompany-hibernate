package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.IDAO;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.validators.IValidator;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Utility class that represents CRUD operations at the Service layer
 *
 * @author Kish Alexey
 */
public class ServiceUtils {

    private static Logger logger = Logger.getLogger(ServiceUtils.class);
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction = null;
    private static final String SUCCESSFUL_TRANSACTION = "Successful transaction";
    private static final String TRANSACTION_FAILED = "Transaction failed";

    public <T> Long addEntity(IDAO<T> dao, T t, IValidator<T> validator) throws ServiceValidateException, ServiceException {
        Long id;
        try {
            String validateResult = validator.validate(t);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            transaction = util.getSession().beginTransaction();
            id = dao.add(t);
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
        return id;
    }

    public <T> void updateEntity(IDAO<T> dao, T t, IValidator<T> validator) throws ServiceValidateException, ServiceException {
        try {
            String validateResult = validator.validate(t);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            transaction = util.getSession().beginTransaction();
            dao.update(t);
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
    }

    public <T> void deleteEntity(IDAO<T> dao, Long id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            transaction = util.getSession().beginTransaction();
            dao.delete(id);
//            util.getSession().getTransaction().commit();
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
    }

    public <T> List<T> getAllEntities(IDAO<T> dao) throws ServiceException {
        List<T> results;
        try {
            transaction = util.getSession().beginTransaction();
            results = dao.getAll();
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
        return results;
    }

    public <T> T getByIdEntity(IDAO<T> dao, Long id) throws ServiceException {
        T t;
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            transaction = util.getSession().beginTransaction();
            t = dao.getById(id);
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
        return t;
    }
}
