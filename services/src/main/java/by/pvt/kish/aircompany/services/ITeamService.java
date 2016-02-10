package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;

import java.util.List;

/**
 * This interface represents a contract for a ITeamService for the flight team.
 *
 * @author Kish Alexey
 */
public interface ITeamService {

    /**
     * Create a given flight team for a particular flight matching the given ID
     *
     * @param id   - The ID of the flight
     * @param team - The flight team to be created
     * @throws ServiceException         - if something fails at Service layer
     * @throws ServiceValidateException - if something fails at Service validation
     */
    void add(Long id, List<Long> team) throws ServiceException, ServiceValidateException;

    /**
     * Returns a list of employees of the flight team for particular flight matching the given ID
     *
     * @param id - The ID of the flight
     * @return a list of the employees, that is a flight team
     * @throws ServiceException - if something fails at Service layer
     */
    List<Employee> getById(Long id) throws ServiceException;
}
