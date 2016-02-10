package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class EmployeeValidatorTest {

    private Employee validEmployee;
    private Employee invalidEmployee;
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    @Before
    public void setUp() throws Exception {
        validEmployee = new Employee();
        validEmployee.setFirstName("testfirstname");
        validEmployee.setLastName("testlastname");
        validEmployee.setPosition(Position.PILOT);
    }

    @Test
    public void testValidate() throws Exception {
        assertNull("Validate method failed", employeeValidator.validate(validEmployee));
        assertEquals("Validate method failed: employee is null", employeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee = new Employee();
        invalidEmployee.setLastName("testlastname");
        invalidEmployee.setPosition(Position.PILOT);
        assertEquals("Validate method failed: firstName is null", employeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee.setFirstName("testfirstname");
        invalidEmployee.setLastName(null);
        assertEquals("Validate method failed", employeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee.setLastName("testlastname: lastName is null");
        invalidEmployee.setPosition(null);
        assertEquals("Validate method failed: position is null", employeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
    }
}