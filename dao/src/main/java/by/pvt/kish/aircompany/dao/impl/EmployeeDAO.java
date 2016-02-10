/**
 * 
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.IEmployeeDAO;
import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.utils.DaoUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.utils.DaoUtils.closeResultSet;

/**
 * This class represents a concrete implementation of the IDAO interface for employee model.
 *
 * @author  Kish Alexey
 */
public class EmployeeDAO extends BaseDAO<Employee> implements IEmployeeDAO{

	private static Logger logger = Logger.getLogger(EmployeeDAO.class);

	private static final String HQL_UPDATE_EMPLOYEE_STATUS = "update FROM Employee E set E.status=:status where E.eid=:id";
	private static final String SQL_GET_ALL_AVAILABLE_EMPLOYEES = "SELECT * FROM employees as e " +
																	"WHERE e.eid NOT IN (" +
																	"SELECT t.t_eid FROM teams as t " +
																	"JOIN flights as f on f.fid=t.t_fid " +
																	"WHERE  f.date = ?)) " +
																	"AND (e.`status` <> 'BLOCKED')";
	private static final String SQL_GET_EMPLOYEE_AVAILABILITY = "SELECT * FROM teams " +
																	"JOIN flights on flights.fid=teams.t_fid " +
																	"WHERE  flights.date = ? " +
																	"HAVING t_eid = ?";

	private static final String UPDATE_EMPLOYEE_STATUS_FAIL = "Updating employee status failed";
	private static final String GET_ALL_AVAILABLE_EMPLOYEE_FAIL = "Getting all available employee failed";
	private static final String GET_USER_AVAILABILITY_FAIL = "Getting user availability failed";

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
	public List<Employee> getAllAvailable(Date date) throws DaoException {
		List<Employee> employees = new ArrayList<>();
		try {
			Session session = util.getSession();
			Query query = session.createSQLQuery(SQL_GET_ALL_AVAILABLE_EMPLOYEES);
			query.setParameter(1, date);
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
	public void setStatus(Long id, EmployeeStatus status) throws DaoException {
		try {
			Session session = util.getSession();
			Query query = session.createQuery(HQL_UPDATE_EMPLOYEE_STATUS);
			query.setParameter("status",status);
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
	public boolean checkEmployeeAvailability(Long id, Date flightDate) throws DaoException {
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE_AVAILABILITY);
			preparedStatement.setDate(1, flightDate);
			preparedStatement.setLong(2, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException(GET_USER_AVAILABILITY_FAIL, e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return false;
	}

}
