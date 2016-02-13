package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testAdd() throws Exception {
        Employee addedEmployee = employeeService.getById(id);
        addedEmployee.setEid(id);
        assertEquals("Add method failed: wrong firstname", addedEmployee, testEmployee);
        employeeService.delete(id);
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
        assertEquals("Update method failed: wrong eid", updatedEmployee, prepareToUpdateEmployee);
        employeeService.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        employeeService.delete(id);
        assertNull("Delete employee: failed",employeeService.getById(id));
    }
}