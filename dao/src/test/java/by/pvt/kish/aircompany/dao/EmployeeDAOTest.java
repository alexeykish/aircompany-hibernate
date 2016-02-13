package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class EmployeeDAOTest {

    private Employee testEmployee;
    private Long id;
    private EmployeeDAO employeeDao = EmployeeDAO.getInstance();
    private FlightDAO flightDao = FlightDAO.getInstance();
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction = null;

    @Before
    public void setUp() throws Exception {
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
        int count =  employeeDao.getAll().size();
        int countFact = employeeDao.getCount();
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
        employeeDao.setEmployeeStatus(id, EmployeeStatus.BLOCKED);
        Employee updatedEmployee = employeeDao.getById(id);
        assertEquals("Update method failed: wrong status", updatedEmployee.getStatus(), prepareToUpdateEmployee.getStatus());
        employeeDao.delete(id);
    }

    @Test
    public  void testEmployeeAvailability() throws Exception {
        Flight testFlight = new Flight();
        Date testDate1 = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
        testFlight.setDate(testDate1);
        Set<Employee> testCrew = new HashSet<>();
        testCrew.add(testEmployee);
        testFlight.setCrew(testCrew);
        Long fid = flightDao.add(testFlight);
        assertFalse("Expected false, but failed" , employeeDao.checkEmployeeAvailability(id, testDate1));
        Date testDate2 = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 2);
        assertTrue("Expected false, but failed" , employeeDao.checkEmployeeAvailability(id, testDate2));
        employeeDao.delete(id);
        flightDao.delete(fid);
    }

    @Test
    @Ignore
    public void testGetAllAvailableEmployee() throws Exception {
        Employee testEmployee1 = new Employee("Name+", "Name+", Position.PILOT);
        Employee testEmployee3 = new Employee("Name+", "Name+", Position.PILOT);
        Employee testEmployee2 = new Employee("Name", "Name", Position.PILOT);
        testEmployee2.setStatus(EmployeeStatus.BLOCKED);
        Long eid1 = employeeDao.add(testEmployee1);
        Long eid2 = employeeDao.add(testEmployee2);
        Long eid3 = employeeDao.add(testEmployee3);
        Flight testFlight = new Flight();
        Date testDate1 = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
        testFlight.setDate(testDate1);
        Set<Employee> testCrew = new HashSet<>();
        testCrew.add(testEmployee);
        testFlight.setCrew(testCrew);
        Long fid = flightDao.add(testFlight);
        List<Employee> list = employeeDao.getAllAvailableEmployee(testDate1);
        for (Employee e: list) {
            System.out.println(e);
        }
        System.out.println("List size = " + list.size());
        //assertEquals("Get all available employees failed", 1, employeeDao.getAllAvailablePlanes(testDate1).size());
        employeeDao.delete(id);
        employeeDao.delete(eid1);
        employeeDao.delete(eid2);
        employeeDao.delete(eid3);
        flightDao.delete(fid);
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }
}