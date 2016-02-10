package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class EmployeeServiceTest {

    private Employee testEmployee;
    private long id;
    private EmployeeService employeeService = EmployeeService.getInstance();

    @Before
    public void setUp() throws Exception {
        testEmployee = new Employee();
        testEmployee.setFirstName("FirstName");
        testEmployee.setLastName("LastName");
        testEmployee.setPosition(Position.PILOT);
        id = employeeService.add(testEmployee);
    }

    @After
    public void tearDown() throws Exception {
        employeeService.delete(id);
    }

    @Test
    public void testAdd() throws Exception {
        Employee addedEmployee = employeeService.getById(id);
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
        employeeService.update(prepareToUpdateEmployee);
        Employee updatedEmployee = employeeService.getById(id);
        assertEquals("Update method failed: wrong eid", updatedEmployee.getEid(), prepareToUpdateEmployee.getEid());
        assertEquals("Update method failed: wrong firstname", updatedEmployee.getFirstName(), prepareToUpdateEmployee.getFirstName());
        assertEquals("Update method failed: wrong lastname", updatedEmployee.getLastName(), prepareToUpdateEmployee.getLastName());
        assertEquals("Update method failed: wrong position", updatedEmployee.getPosition(), prepareToUpdateEmployee.getPosition());
    }

    @Test
    public void testGetAll() throws Exception {
        int beforeAddNumber = employeeService.getAll().size();
        Long getAllId = employeeService.add(testEmployee);
        int afterAddNumber = employeeService.getAll().size();
        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber-1);
        employeeService.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        employeeService.delete(id);
        assertNull("Delete employee: failed",employeeService.getById(id));
    }
}