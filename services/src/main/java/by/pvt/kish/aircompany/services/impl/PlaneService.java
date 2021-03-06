package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.PlaneDAO;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.IPlaneService;
import by.pvt.kish.aircompany.validators.PlaneValidator;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * This class represents a concrete implementation of the IService interface for plane model.
 *
 * @author Kish Alexey
 */
public class PlaneService extends BaseService<Plane> implements IPlaneService{

    private static Logger logger = Logger.getLogger(PlaneService.class);
    private static PlaneService instance;
    private PlaneDAO planeDAO = PlaneDAO.getInstance();
    private PlaneValidator planeValidator = new PlaneValidator();

    /**
     * Returns an synchronized instance of a PlaneService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a PlaneService
     */
    public synchronized static PlaneService getInstance() {
        if (instance == null) {
            instance = new PlaneService();
        }
        return instance;
    }

    /**
     * Create the plane Entity
     *
     * @param plane - The plane object to be created
     * @return - The ID of the plane object, generated by DB
     * @throws ServiceException         - if something fails at Service layer
     * @throws ServiceValidateException - if something fails at Service validation
     */
    @Override
    public Long add(Plane plane) throws ServiceException, ServiceValidateException {
        return serviceUtils.addEntity(planeDAO, plane, planeValidator);
    }

    /**
     * Update the plane Entity
     *
     * @param plane - The plane object to be updated
     * @throws ServiceException         - if something fails at Service layer
     * @throws ServiceValidateException - if something fails at Service validation
     */
    @Override
    public void update(Plane plane) throws ServiceException, ServiceValidateException {
        serviceUtils.updateEntity(planeDAO, plane, planeValidator);
    }

    /**
     * Returns a list of all planes
     *
     * @return a list of all planes
     * @throws ServiceException - if something fails at Service layer
     */
    @Override
    public List<Plane> getAll() throws ServiceException {
        return serviceUtils.getAllEntities(planeDAO);
    }

    /**
     * Delete the plane Entity
     *
     * @param id - The ID of the plane entity to be deleted
     * @throws ServiceException - if something fails at Service layer
     */
    @Override
    public void delete(Long id) throws ServiceException {
        serviceUtils.deleteEntity(planeDAO, id);
    }

    /**
     * Returns the plane entity matching the given ID
     *
     * @param id - The ID of the plane entity to be returned
     * @return - the plane entity matching the given ID
     * @throws ServiceException - if something fails at Service layer
     */
    @Override
    public Plane getById(Long id) throws ServiceException {
        return serviceUtils.getByIdEntity(planeDAO, id);
    }

    /**
     * Set plane status to the DB
     *
     * @param id     - The ID of the plane
     * @param status - The status to be changed
     * @throws ServiceException If something fails at DAO level
     */
    @Override
    public void setStatus(Long id, PlaneStatus status) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            transaction = util.getSession().beginTransaction();
            planeDAO.setPlaneStatus(id, status);
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Returns a list of five last flights of the concrete plane from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete plane
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Flight> getPlaneLastFiveFlights(Long id) throws ServiceException {
        List<Flight> results;
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            transaction = util.getSession().beginTransaction();
            results =  planeDAO.getPlaneLastFiveFlights(id);
            transaction.commit();
            logger.info(SUCCESSFUL_TRANSACTION);
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
        return results;
    }

    /**
     * Returns a list of all available planes at this date from the DB
     * @param date - The date of the flight
     * @return - a list of all available employees at this date from the DB
     * @throws ServiceException If something fails at DAO level
     */
    @Override
    public List<Plane> getAllAvailable(Date date) throws ServiceException {
        List<Plane> results;
        try {
            transaction = util.getSession().beginTransaction();
            results =  planeDAO.getAllAvailablePlanes(date);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.info(TRANSACTION_FAILED);
            throw new ServiceException(e.getMessage());
        }
        return results;
    }
}
