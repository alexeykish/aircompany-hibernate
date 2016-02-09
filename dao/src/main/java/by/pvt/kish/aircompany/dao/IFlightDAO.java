package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

/**
 * This interface represents a contract for a IDAO for the plane model.
 *
 * @author Kish Alexey
 */
public interface IFlightDAO {

    /**
     * Returns a list of five last flights of the concrete plane from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete plane
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getPlaneLastFiveFlights(Long id) throws DaoException;

    /**
     * Returns a list of five last flights of the concrete employee from the DB
     *
     * @param id - The ID of the employee
     * @return - the list of last five flight of the concrete employee
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getEmployeeLastFiveFlights(Long id) throws DaoException;

}
