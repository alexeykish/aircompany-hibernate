package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

/**
 * This interface represents a contract for a IDAO for the plane model.
 *
 * @author Kish Alexey
 */
public interface IPlaneDAO {

    /**
     * Update particular plane status int the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @throws DaoException If something fails at DB level
     */
    void setStatus(Long id, PlaneStatus status) throws DaoException;
}
