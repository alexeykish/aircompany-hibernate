package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Employee;

import java.sql.Date;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface IEmployeeDAO {

    List<Employee> getAllAvailable(Date date) throws DaoException;

    void setStatus(Long id, EmployeeStatus status) throws DaoException;

}
