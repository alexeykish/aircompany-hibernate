/**
 * 
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.IEmployeeDAO;
import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a concrete implementation of the IDAO interface for employee model.
 *
 * @author  Kish Alexey
 */
public class EmployeeDAO extends BaseDAO<Employee> implements IEmployeeDAO{

	private static Logger logger = Logger.getLogger(EmployeeDAO.class);

	private static final String HQL_UPDATE_EMPLOYEE_STATUS = "update FROM Employee E set E.status=:employeestatus where E.eid=:id";
	private static final String HQL_GET_ALL_AVAILABLE_EMPLOYEES = "select E from Employee E where E not in (select E from Employee E join E.flights f where f.date=:date) and E.status=:employeestatus";
	private static final String HQL_GET_EMPLOYEE_AVAILABILITY = "select E from Employee E join E.flights f where f.date=:date and E.eid=:id";
	private static final String HQL_GET_EMPLOYEES_LAST_FIVE_FLIGHTS = "select E.flights from Employee E where E.eid =:eid";
	private static final String HQL_GET_EMPLOYEES_BY_FLIGHTID = "select E from Employee E join E.flights F where F.fid =:id";

	private static final String UPDATE_EMPLOYEE_STATUS_FAIL = "Updating employee status failed";
	private static final String GET_ALL_AVAILABLE_EMPLOYEE_FAIL = "Getting all available employee failed";
	private static final String GET_USER_AVAILABILITY_FAIL = "Getting user availability failed";
	private static final String GET_EMPLOYEES_FLIGHTS_FAIL = "Getting employees flights failed";
	private static final String GET_EMPLOYEE_BY_FLIGHTID_FAIL = "Getting employees by flight id failed";

	private static EmployeeDAO instance;
	private HibernateUtil util = HibernateUtil.getUtil();

	private EmployeeDAO() {
		super(Employee.class);
	}

	/**
	 * Returns an synchronized instance of a EmployeeDAO, if the instance does not exist yet - create a new
	 * @return - a instance of a EmployeeDAO
	 */
	public synchronized static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

	/**
	 * Returns a list of all available employees at this date from the DB
	 * @param date - The date of the flight
	 * @return - a list of all available employees at this date from the DB
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public List<Employee> getAllAvailableEmployee(Date date) throws DaoException {
		List<Employee> employees = new ArrayList<>();
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_GET_ALL_AVAILABLE_EMPLOYEES);
			query.setParameter("date", date);
			query.setParameter("employeestatus", EmployeeStatus.AVAILABLE);
			employees = query.list();
		} catch (HibernateException e) {
			throw new DaoException(GET_ALL_AVAILABLE_EMPLOYEE_FAIL);
		}
		return employees;
	}

	/**
	 * Set employees status to the DB
	 * @param id - The ID of the employee
	 * @param status - The status to be changed
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public void setEmployeeStatus(Long id, EmployeeStatus status) throws DaoException {
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_UPDATE_EMPLOYEE_STATUS);
			query.setParameter("employeestatus",status);
			query.setParameter("id",id);
			query.executeUpdate();
		} catch (HibernateException e) {
			throw new DaoException(UPDATE_EMPLOYEE_STATUS_FAIL);
		}
	}

	/**
	 * Check if the employee is in another flight teams at that date
	 *
	 * @param id - The ID of the employee
	 * @param flightDate - The flight date
	 * @return - false if employee isn't in another flights at that date, true - if employee is busy at that date
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public boolean checkEmployeeAvailability(Long id, Date flightDate) throws DaoException {
		List results;
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_GET_EMPLOYEE_AVAILABILITY);
			query.setParameter("date", flightDate);
			query.setParameter("id", id);
			results = query.list();
			if (!results.isEmpty()) {
				return false;
			}
		} catch (HibernateException e) {
			throw new DaoException(GET_USER_AVAILABILITY_FAIL, e);
		}
		return true;
	}

	/**
	 * Returns a list of five last flights of the concrete employee from the DB
	 *
	 * @param id - The ID of the plane
	 * @return - the list of last five flight of the concrete employee
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public List<Flight> getEmployeeLastFiveFlights(Long id) throws DaoException {
		List<Flight> flights = new ArrayList<>();
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_GET_EMPLOYEES_LAST_FIVE_FLIGHTS);
			query.setParameter("eid",id);
			query.setMaxResults(5);
			flights = query.list();
		} catch (HibernateException e) {
			throw new DaoException(GET_EMPLOYEES_FLIGHTS_FAIL, e);
		}
		return flights;
	}

	/**
	 * Returns a list of employees as flight crew of the concrete flight from the DB
	 *
	 * @param id - The ID of the flight
	 * @return - the list of the employees as flight crew
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public List<Employee> getFlightCrewByFlightId(Long id) throws DaoException {
		List<Employee> employees = new ArrayList<>();
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_GET_EMPLOYEES_BY_FLIGHTID);
			query.setParameter("id", id);
			employees = query.list();
		} catch (HibernateException e) {
			throw new DaoException(GET_EMPLOYEE_BY_FLIGHTID_FAIL);
		}
		return employees;
	}

	/**
	 * Returns a list of employees prepared for pagination from the DB
	 *
	 * @param pageSize - The number of employees at the page
	 * @param pageNumber - The number of the showed page
	 * @return - the list of the employees
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public List<Employee> getAllToPage(int pageSize, int pageNumber) throws DaoException {
		List<Employee> results = new ArrayList<>();
		try {
			Session session = util.getSession();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			results = criteria.list();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
		return results;
	}
}
