package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

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
    void setStatus(Long id, String status) throws DaoException;
}
