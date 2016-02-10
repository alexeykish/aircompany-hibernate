/**
 *
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * The abstract class represents a implementation of the IDAO interface
 * The constructor is obtained from the connection pool database connection
 *
 * @author Kish Alexey
 */
public abstract class BaseDAO<T> implements IDAO<T> {

    private static Logger logger = Logger.getLogger(BaseDAO.class);
    private Class className;

    protected HibernateUtil util = HibernateUtil.getUtil();

    protected static Connection connection;
    protected PreparedStatement preparedStatement;

    protected BaseDAO(Class className) {
        this.className = className;
    }

    public Long add(T t) throws DaoException {
        Long iid;
        try {
            Session session = util.getSession();
            session.saveOrUpdate(t);
            iid = (Long)session.getIdentifier(t);
            logger.info("Saved object: " + t);
        } catch (HibernateException e) {
            logger.error("Error in add DAO", e);
            throw new DaoException(e);
        }
        return iid;
    }

    public void update(T t) throws DaoException {
        try {
            Session session = util.getSession();
            session.merge(t);
            logger.info("Updated object: " + t);
        } catch (HibernateException e) {
            logger.error("Error in update DAO", e);
            throw new DaoException(e);
        }
    }

    public T getById(Long id) throws DaoException {
        T t = null;
        try {
            Session session = util.getSession();
            t = (T) session.get(className, id);
            logger.info("Get object: " + t);
        } catch (HibernateException e) {
            logger.error("Error in get by id DAO", e);
            throw new DaoException(e);
        }
        return t;
    }

    public List<T> getAll() throws DaoException {
        List<T> results;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(className);
            results = criteria.list();
        } catch (HibernateException e) {
            logger.error("Error in get all DAO", e);
            throw new DaoException(e);
        }
        return results;
    }

    public void delete(Long id) throws DaoException {
        try {
            Session session = util.getSession();
            T t = (T) session.get(className, id);
            if (t == null) {
                throw  new DaoException("Cant find object with that ID: " + id);
            }
            session.delete(t);
            logger.info("Deleted object: " + t);
        } catch (HibernateException e) {
            logger.error("Error in delete DAO", e);
            throw new DaoException(e);
        }
    }

    public Long getCount() throws DaoException {
        Long count;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(className);
            criteria.setProjection(Projections.rowCount());
            count = (Long) criteria.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error in get count DAO", e);
            throw new DaoException(e);
        }
        return count;
    }



}
