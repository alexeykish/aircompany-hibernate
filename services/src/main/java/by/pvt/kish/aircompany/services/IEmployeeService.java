package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;

import java.util.Date;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface IEmployeeService {

    /**
     * Set employees status to the DB
     *
     * @param id     - The ID of the employee
     * @param status - The status to be changed
     * @throws ServiceException If something fails at DAO level
     */
    void setStatus(Long id, EmployeeStatus status) throws ServiceException;

    /**
     * Returns a list of all available employees at this date from the DB
     *
     * @param date - The date of the flight
     * @return - a list of all available employees at this date from the DB
     * @throws ServiceException If something fails at DAO level
     */
    List<Employee> getAllAvailable(Date date) throws ServiceException;

    /**
     * Check if the employee is in another flight teams at that date
     *
     * @param id - The ID of the employee
     * @param flightDate - The flight date
     * @return - false if employee isn't in another flights at that date, true - if employee is busy at that date
     * @throws DaoException If something fails at DB level
     */
    boolean checkEmployeeAvailability(Long id, Date flightDate) throws ServiceException;

    /**
     * Returns a list of five last flights of the concrete employee from the DB
     *
     * @param id - The ID of the employee
     * @return - the list of last five flight of the concrete employee
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getEmployeeLastFiveFlights(Long id) throws ServiceException;

    /**
     * Returns a list of employees as flight crew of the concrete flight from the DB
     *
     * @param id - The ID of the flight
     * @return - the list of the employees as flight crew
     * @throws DaoException If something fails at DB level
     */
    List<Employee> getFlightCrewByFlightId(Long id) throws ServiceException;
}
