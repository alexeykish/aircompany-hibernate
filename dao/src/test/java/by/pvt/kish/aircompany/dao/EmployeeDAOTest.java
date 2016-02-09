package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
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

    @Before
    public void setUp() throws Exception {
        employeeDao = EmployeeDAO.getInstance();
        testEmployee = new Employee();
        testEmployee.setFirstName("FirstName");
        testEmployee.setLastName("LastName");
        testEmployee.setPosition(Position.PILOT);
        id = employeeDao.add(testEmployee);
    }

    @Test
    public void testAdd() throws Exception {
        Employee addedEmployee = employeeDao.getById(id);
        assertEquals("Add method failed: wrong firstname", addedEmployee.getFirstName(), testEmployee.getFirstName());
        assertEquals("Add method failed: wrong lastname", addedEmployee.getLastName(), testEmployee.getLastName());
        assertEquals("Add method failed: wrong position", addedEmployee.getPosition(), testEmployee.getPosition());
    }

    @Test
    public void testUpdate() throws Exception {
        Employee prepareToUpdateEmployee = new Employee();
        prepareToUpdateEmployee.setEid(id);
        prepareToUpdateEmployee.setFirstName("updatedFirstname");
        prepareToUpdateEmployee.setLastName("updatedLastname");
        prepareToUpdateEmployee.setPosition(Position.NAVIGATOR);
        employeeDao.update(prepareToUpdateEmployee);
        Employee updatedEmployee = employeeDao.getById(id);
        assertEquals("Update method failed: wrong eid", updatedEmployee.getEid(), prepareToUpdateEmployee.getEid());
        assertEquals("Update method failed: wrong firstname", updatedEmployee.getFirstName(), prepareToUpdateEmployee.getFirstName());
        assertEquals("Update method failed: wrong lastname", updatedEmployee.getLastName(), prepareToUpdateEmployee.getLastName());
        assertEquals("Update method failed: wrong position", updatedEmployee.getPosition(), prepareToUpdateEmployee.getPosition());
    }

    @Test
    public void testGetAll() throws Exception {
        int beforeAddNumber = employeeDao.getAll().size();
        Long getAllId = employeeDao.add(testEmployee);
        int afterAddNumber = employeeDao.getAll().size();
        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber - 1);
        employeeDao.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        employeeDao.delete(id);
        assertNull("Delete employee: failed", employeeDao.getById(id));
    }

    @After
    public void tearDown() throws Exception {
        employeeDao.delete(id);
    }
}