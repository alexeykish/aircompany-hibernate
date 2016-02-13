package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;

import java.util.Date;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface IEmployeeDAO {

    /**
     * Returns a list of all available employees at this date from the DB
     * @param date - The date of the flight
     * @return - a list of all available employees at this date from the DB
     * @throws DaoException If something fails at DB level
     */
    List<Employee> getAllAvailableEmployee(Date date) throws DaoException;

    /**
     * Set employees status to the DB
     * @param id - The ID of the employee
     * @param status - The status to be changed
     * @throws DaoException If something fails at DB level
     */
    void setEmployeeStatus(Long id, EmployeeStatus status) throws DaoException;

    /**
     * Check if the employee is in another flight teams at that date
     *
     * @param id - The ID of the employee
     * @param flightDate - The flight date
     * @return - false if employee isn't in another flights at that date, true - if employee is busy at that date
     * @throws DaoException If something fails at DB level
     */
    boolean checkEmployeeAvailability(Long id, Date flightDate) throws DaoException;


    /**
     * Returns a list of five last flights of the concrete employee from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete employee
     * @throws DaoException If something fails at DB level
     */
    List<Flight> getEmployeeLastFiveFlights(Long id) throws DaoException;

    /**
     * Returns a list of employees as flight crew of the concrete flight from the DB
     *
     * @param id - The ID of the flight
     * @return - the list of the employees as flight crew
     * @throws DaoException If something fails at DB level
     */
    List<Employee> getFlightCrewByFlightId(Long id) throws DaoException;

    /**
     * Returns a list of employees prepared for pagination from the DB
     *
     * @param pageSize - The number of employees at the page
     * @param pageNumber - The number of the showed page
     * @return - the list of the employees
     * @throws DaoException If something fails at DB level
     */
    List<Employee> getAllToPage(int pageSize, int pageNumber) throws DaoException;
}
