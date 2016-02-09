package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class AirportDAOTest {

    private AirportDAO airportDAO;
    private Long id;
    private Airport testAirport;

    @Before
    public void setUp() throws Exception {
        airportDAO = AirportDAO.getInstance();
        testAirport = new Airport();
        testAirport.setCity("testCity");
        id = airportDAO.add(testAirport);
    }

    @Test
    public void testAdd() throws Exception {
        Airport addedAirport = airportDAO.getById(id);
        assertEquals("Add method failed: wrong city", addedAirport.getCity(), testAirport.getCity());
    }

    @Test
    public void testUpdate() throws Exception {
        Airport prepareToUpdateAirport = new Airport();
        prepareToUpdateAirport.setAid(id);
        prepareToUpdateAirport.setCity("updatedCity");
        airportDAO.update(prepareToUpdateAirport);
        Airport updatedAirport = airportDAO.getById(id);
        assertEquals("Update method failed: wrong aid", prepareToUpdateAirport.getAid(), updatedAirport.getAid());
        assertEquals("Update method failed: wrong city", prepareToUpdateAirport.getCity(), updatedAirport.getCity());
    }

    @Test
    public void testGetAll() throws Exception {
        int beforeAddNumber = airportDAO.getAll().size();
        Long getAllId = airportDAO.add(testAirport);
        int afterAddNumber = airportDAO.getAll().size();
        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber - 1);
        airportDAO.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        airportDAO.delete(id);
        assertNull("Delete method: failed", airportDAO.getById(id));
    }

    @After
    public void tearDown() throws Exception {
        airportDAO.delete(id);
    }
}