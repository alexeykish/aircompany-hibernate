package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.pojos.Plane;

import java.util.Date;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface IPlaneService {

    /**
     * Set plane status to the DB
     *
     * @param id     - The ID of the plane
     * @param status - The status to be changed
     * @throws ServiceException If something fails at DAO level
     */
    void setStatus(Long id, PlaneStatus status) throws ServiceException;

    /**
     * Returns a list of five last flights of the concrete plane from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete plane
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getPlaneLastFiveFlights(Long id) throws ServiceException;

    /**
     * Returns a list of all available planes at this date from the DB
     * @param date - The date of the flight
     * @return - a list of all available employees at this date from the DB
     * @throws ServiceException If something fails at DAO level
     */
    List<Plane> getAllAvailable(Date date) throws ServiceException;
}
