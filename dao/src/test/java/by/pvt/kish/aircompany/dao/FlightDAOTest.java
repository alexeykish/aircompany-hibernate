package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.pojos.*;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class FlightDAOTest {

    private Flight testFlight1;
    private Flight testFlight2;
    Address testAddress1;
    Address testAddress2;
    private Long id1;
    private Long id2;
    private FlightDAO flightDAO = FlightDAO.getInstance();
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction = null;

    @Before
    public void setUp() throws Exception {
        testAddress1 = new Address("testCountry1", "testCity1");
        testAddress2 = new Address("testCountry2", "testCity2");
        Airport departedAirport = new Airport("departedAirport", testAddress1);
        Airport arrivalAirport = new Airport("arrivalAirport", testAddress2);
        PlaneCrew planeCrew1 = new PlaneCrew(1, 1, 1, 1);
        PlaneCrew planeCrew2 = new PlaneCrew(1, 0, 0, 1);
        Plane plane1 = new Plane("testModel", 100, 100);
        Plane plane2 = new Plane("testModel", 100, 100);

        plane1.setPlaneCrew(planeCrew1);
        plane2.setPlaneCrew(planeCrew2);
        planeCrew1.setPlane(plane1);
        planeCrew2.setPlane(plane2);

        transaction = util.getSession().beginTransaction();

        testFlight1 = new Flight();
        testFlight1.setDate(new Date());
        testFlight1.setDeparture(departedAirport);
        testFlight1.setArrival(arrivalAirport);
        testFlight1.setPlane(plane1);

        testFlight2 = new Flight();
        testFlight2.setDate(new Date());
        testFlight2.setDeparture(arrivalAirport);
        testFlight2.setArrival(departedAirport);
        testFlight2.setPlane(plane2);

        Employee employeePilot = new Employee("Name", "Name", Position.PILOT);
        Employee employeeNavigator = new Employee("Name", "Name", Position.NAVIGATOR);
        Employee employeeRadiooperator = new Employee("Name", "Name", Position.RADIOOPERATOR);
        Employee employeeStewardess = new Employee("Name", "Name", Position.STEWARDESS);

        employeePilot.getFlights().add(testFlight1);
        employeeNavigator.getFlights().add(testFlight1);
        employeeRadiooperator.getFlights().add(testFlight1);
        employeeStewardess.getFlights().add(testFlight1);

        employeePilot.getFlights().add(testFlight2);
        employeeStewardess.getFlights().add(testFlight2);

        Set<Employee> testCrew1 = new HashSet<>();
        testCrew1.add(employeePilot);
        testCrew1.add(employeeNavigator);
        testCrew1.add(employeeRadiooperator);
        testCrew1.add(employeeStewardess);

        Set<Employee> testCrew2 = new HashSet<>();
        testCrew2.add(employeePilot);
        testCrew2.add(employeeStewardess);

        testFlight1.setCrew(testCrew1);
        testFlight2.setCrew(testCrew2);

        id1 = flightDAO.add(testFlight1);
        id2 = flightDAO.add(testFlight2);
    }

    @Test
    public void testAdd() throws Exception {
        Flight addedFlight1 = flightDAO.getById(id1);
        Flight addedFlight2 = flightDAO.getById(id2);
        assertEquals("Add method failed: wrong firstname", addedFlight1.getCrew().size(), testFlight1.getCrew().size());
        assertEquals("Add method failed: wrong firstname", addedFlight2.getCrew().size(), testFlight2.getCrew().size());
        flightDAO.delete(id1);
        flightDAO.delete(id2);
    }

    @Test
    public void testUpdate() throws Exception {
        Flight prepareToUpdateFlight = flightDAO.getById(id1);
        Airport changedDepartureAirport = new Airport("changedDepartureAirport", testAddress2);
        Airport changedArrivalAirport = new Airport("changedArrivalAirport", testAddress1);
        prepareToUpdateFlight.setDate(new Date());
        prepareToUpdateFlight.setDeparture(changedDepartureAirport);
        prepareToUpdateFlight.setArrival(changedArrivalAirport);
        flightDAO.update(prepareToUpdateFlight);

        Flight updatedFlight = flightDAO.getById(id1);
        assertEquals("Update method failed: wrong eid", updatedFlight, prepareToUpdateFlight);
        flightDAO.delete(id1);
        flightDAO.delete(id2);
    }

    @Test
    public void testGetAll() throws Exception {
        int count = flightDAO.getAll().size();
        int countFact = flightDAO.getCount();
        assertEquals("Get all method failed", count, countFact);
        flightDAO.delete(id1);
        flightDAO.delete(id2);
    }

    @Test
    public void testDelete() throws Exception {
        flightDAO.delete(id1);
        flightDAO.delete(id2);
        assertNull("Delete employee: failed", flightDAO.getById(id1));
        assertNull("Delete employee: failed", flightDAO.getById(id2));
    }

    @Test
    public void testSetStatus() throws Exception {
        Flight prepareToUpdateStatusFlight = flightDAO.getById(id1);
        flightDAO.setFlightStatus(id1, FlightStatus.CANCELED);
        Flight updatedStatusFlight = flightDAO.getById(id1);
        assertEquals("Update method failed: wrong status", updatedStatusFlight.getStatus(), prepareToUpdateStatusFlight.getStatus());
        flightDAO.delete(id1);
        flightDAO.delete(id2);
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }
}