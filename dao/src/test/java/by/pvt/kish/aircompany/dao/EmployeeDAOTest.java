package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class EmployeeDAOTest {

    private Employee testEmployee;
    private Long id;
    private EmployeeDAO employeeDao;
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction = null;

    @Before
    public void setUp() throws Exception {
        employeeDao = EmployeeDAO.getInstance();
        testEmployee = new Employee();
        testEmployee.setFirstName("FirstName");
        testEmployee.setLastName("LastName");
        testEmployee.setPosition(Position.PILOT);
        transaction = util.getSession().beginTransaction();
        id = employeeDao.add(testEmployee);
    }

    @Test
    public void testAdd() throws Exception {
        Employee addedEmployee = employeeDao.getById(id);
        assertEquals("Add method failed: wrong firstname", addedEmployee, testEmployee);
        employeeDao.delete(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee prepareToUpdateEmployee = employeeDao.getById(id);
        prepareToUpdateEmployee.setFirstName("updatedFirstname");
        prepareToUpdateEmployee.setLastName("updatedLastname");
        prepareToUpdateEmployee.setPosition(Position.NAVIGATOR);
        employeeDao.update(prepareToUpdateEmployee);

        Employee updatedEmployee = employeeDao.getById(id);
        assertEquals("Update method failed: wrong eid", updatedEmployee, prepareToUpdateEmployee);
        employeeDao.delete(id);
    }

    @Test
    public void testGetAll() throws Exception {
        Long count = (long) employeeDao.getAll().size();
        Long countFact = employeeDao.getCount();
        assertEquals("Get all method failed", count, countFact);
        employeeDao.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        employeeDao.delete(id);
        assertNull("Delete employee: failed", employeeDao.getById(id));
    }

    @Test
    public void testSetStatus() throws Exception {
        Employee prepareToUpdateEmployee = new Employee();
        prepareToUpdateEmployee.setEid(id);
        employeeDao.setStatus(id, EmployeeStatus.BLOCKED);
        Employee updatedEmployee = employeeDao.getById(id);
        assertEquals("Update method failed: wrong status", updatedEmployee.getStatus(), prepareToUpdateEmployee.getStatus());
        employeeDao.delete(id);
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }
}