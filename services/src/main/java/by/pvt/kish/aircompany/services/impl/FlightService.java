package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.FlightValidator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for flight model.
 *
 * @author Kish Alexey
 */
public class FlightService extends BaseService<Flight> {

    private static FlightService instance;
    private FlightDAO flightDAO = FlightDAO.getInstance();
    private FlightValidator flightValidator = new FlightValidator();
    private static Logger logger = Logger.getLogger(TeamService.class.getName());

    /**
     * Returns an synchronized instance of a FlightService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a FlightService
     */
    public synchronized static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    @Override
    public Long add(Flight flight) throws ServiceException, ServiceValidateException {
        return addEntity(flightDAO, flight, flightValidator);
    }

    @Override
    public void update(Flight flight) throws ServiceException, ServiceValidateException {
        try {
            connection.setAutoCommit(false);
            updateEntity(flightDAO, flight, flightValidator);
            connection.commit();
        } catch (SQLException e) {
            try {
                logger.debug("Update flight: commit failed");
                connection.rollback();
                throw new ServiceException(e);
            } catch (SQLException e2) {
                logger.debug("Update flight: rollback failed");
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<Flight> getAll() throws ServiceException {
        return getAllEntities(flightDAO);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        deleteEntity(flightDAO, id);
    }

    @Override
    public Flight getById(Long id) throws ServiceException {
        return getByIdEntity(flightDAO, id);
    }

    public List<Flight> getPlaneLastFiveFlights(Long id) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            return FlightDAO.getInstance().getPlaneLastFiveFlights(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Flight> getEmployeeLastFiveFlights(Long id) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            return FlightDAO.getInstance().getEmployeeLastFiveFlights(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void setStatus(Long id, String status) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            FlightDAO.getInstance().setStatus(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
