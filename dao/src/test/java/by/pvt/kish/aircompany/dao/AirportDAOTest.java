package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.AirportDAO;
import by.pvt.kish.aircompany.pojos.Address;
import by.pvt.kish.aircompany.pojos.Airport;
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
public class AirportDAOTest {

    private AirportDAO airportDAO = AirportDAO.getInstance();
    private Long id;
    private Airport testAirport;
    private Address testAddress;
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        testAddress = new Address("testCountry", "testCity");
        testAirport = new Airport();
        testAirport.setName("testName");
        testAirport.setAddress(testAddress);
        transaction = util.getSession().beginTransaction();
        id = airportDAO.add(testAirport);
    }

    @Test
    public void testAdd() throws Exception {
        Airport addedAirport = airportDAO.getById(id);
        assertEquals("Add method failed", addedAirport, testAirport);
        airportDAO.delete(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Airport prepareToUpdateAirport = new Airport();
        prepareToUpdateAirport.setAid(id);
        prepareToUpdateAirport.setName("updatedCity");
        airportDAO.update(prepareToUpdateAirport);
        Airport updatedAirport = airportDAO.getById(id);
        assertEquals("Update method failed", prepareToUpdateAirport, updatedAirport);
        airportDAO.delete(id);
    }

    @Test
    public void testGetAll() throws Exception {
        int countAirports = airportDAO.getAll().size();
        int countLines = airportDAO.getCount();
        assertEquals("Get all method failed", countLines, countAirports);
        airportDAO.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        airportDAO.delete(id);
        assertNull("Delete method: failed", airportDAO.getById(id));
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }
}