package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Kish Alexey
 */
public class FlightServiceTest {

    Flight testFlight;
    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    FlightService flightService = FlightService.getInstance();
    FlightDAO flightDAO = FlightDAO.getInstance();
    Long id;
    Long eid1;
    Long eid2;
    Long eid3;
    Long eid4;
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction = null;

    @Before
    public void setUp() throws Exception {

        testFlight = new Flight();

        employee1 = new Employee("Name1", "Name1", Position.PILOT);
        employee2 = new Employee("Name2", "Name2", Position.NAVIGATOR);
        employee3 = new Employee("Name3", "Name3", Position.RADIOOPERATOR);
        employee4 = new Employee("Name4", "Name4", Position.STEWARDESS);

        transaction = util.getSession().beginTransaction();
        eid1 = employeeDAO.add(employee1);
        eid2 = employeeDAO.add(employee2);
        eid3= employeeDAO.add(employee3);
        eid4 = employeeDAO.add(employee4);

        id = flightDAO.add(testFlight);
        transaction.commit();

    }

    @Test
    public void testAddTeam() throws Exception {
        List<Long> idList = new ArrayList<>();
        idList.add(eid1);
        idList.add(eid2);
        idList.add(eid3);
        idList.add(eid4);
        flightService.addTeam(id, idList);
        assertNotNull("Crew add failed", flightService.getById(id).getCrew());

        flightDAO.delete(id);
    }

    @After
    public void tearDown() throws Exception {

    }
}