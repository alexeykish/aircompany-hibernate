package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.services.impl.AirportService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class AirportServiceTest {

    private AirportService airportService = AirportService.getInstance();
    private Long id;
    private Airport testAirport;

    @Before
    public void setUp() throws Exception {
        testAirport = new Airport();
        testAirport.setCity("testCity");
        id = airportService.add(testAirport);
    }

    @Test
    public void testAdd() throws Exception {
        Airport addedAirport = airportService.getById(id);
        assertEquals("Add method failed: wrong city", addedAirport.getCity(), testAirport.getCity());
    }

    @Test
    public void testUpdate() throws Exception {
        Airport prepareToUpdateAirport = new Airport();
        prepareToUpdateAirport.setAid(id);
        prepareToUpdateAirport.setCity("updatedCity");
        airportService.update(prepareToUpdateAirport);
        Airport updatedAirport = airportService.getById(id);
        assertEquals("Update method failed: wrong aid", prepareToUpdateAirport.getAid(), updatedAirport.getAid());
        assertEquals("Update method failed: wrong city", prepareToUpdateAirport.getCity(), updatedAirport.getCity());
    }

    @Test
    public void testGetAll() throws Exception {
        int beforeAddNumber = airportService.getAll().size();
        Long getAllId = airportService.add(testAirport);
        int afterAddNumber = airportService.getAll().size();
        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber-1);
        airportService.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        airportService.delete(id);
        assertNull("Delete method: failed", airportService.getById(id));
    }

    @After
    public void tearDown() throws Exception {
        airportService.delete(id);
    }
}