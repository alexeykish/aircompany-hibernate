package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Flight;

import java.util.List;

/**
 * This interface represents a contract for a IDAO for the plane model.
 *
 * @author Kish Alexey
 */
public interface IFlightDAO {

    /**
     * Update particular plane status int the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @throws DaoException If something fails at DB level
     */
    void setFlightStatus(Long id, FlightStatus status) throws DaoException;

    /**
     * Returns a list of flights ordered by date, prepared for pagination from the DB
     *
     * @param pageSize - The number of flights at the page
     * @param pageNumber - The number of the showed page
     * @return - the list of the flights, ordered by date
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getAllToPage(int pageSize, int pageNumber) throws DaoException;
}
