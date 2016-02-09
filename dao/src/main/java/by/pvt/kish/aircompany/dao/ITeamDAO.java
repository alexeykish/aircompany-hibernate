package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

/**
 * This interface represents a contract for a IDAO for the flight team.
 *
 * @author Kish Alexey
 */
public interface ITeamDAO {

    /**
     * Create a given flight team in the database for a particular flight from the DB matching the given ID
     *
     * @param id   - The ID of the flight
     * @param team - The flight team to be created
     * @throws DaoException If something fails at DB level
     */
    void add(Long id, List<Long> team) throws DaoException;

    /**
     * Returns a list of employees of the flight team for particular flight from the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @return a list of the employees, that is a flight team
     * @throws DaoException If something fails at DB level
     */
    List<Employee> getById(Long id) throws DaoException;
}
