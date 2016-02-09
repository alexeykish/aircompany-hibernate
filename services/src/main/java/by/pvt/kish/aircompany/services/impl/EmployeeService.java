package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.EmployeeValidator;

import java.sql.Date;
import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for employee model.
 *
 * @author Kish Alexey
 */
public class EmployeeService extends BaseService<Employee> {

    private static EmployeeService instance;
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    /**
     * Returns an synchronized instance of a EmployeeService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a EmployeeService
     */
    public synchronized static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    @Override
    public Long add(Employee employee) throws ServiceException, ServiceValidateException {
        return addEntity(employeeDAO, employee, employeeValidator);
    }

    @Override
    public void update(Employee employee) throws ServiceException, ServiceValidateException {
        updateEntity(employeeDAO, employee, employeeValidator);
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        return getAllEntities(employeeDAO);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        deleteEntity(employeeDAO, id);
    }

    @Override
    public Employee getById(Long id) throws ServiceException {
        return getByIdEntity(employeeDAO, id);
    }

    /**
     * Set employees status to the DB
     * @param id - The ID of the employee
     * @param status - The status to be changed
     * @throws ServiceException If something fails at DAO level
     */
    public void setStatus(Long id, String status) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            EmployeeDAO.getInstance().setStatus(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Returns a list of all available employees at this date from the DB
     * @param date - The date of the flight
     * @return - a list of all available employees at this date from the DB
     * @throws ServiceException If something fails at DAO level
     */
    public List<Employee> getAllAvailable(Date date) throws ServiceException {
        try {
            return EmployeeDAO.getInstance().getAllAvailable(date);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
