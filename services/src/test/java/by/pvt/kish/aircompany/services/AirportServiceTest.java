package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.Airport;
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
        testAirport.setName("testCity");
        id = airportService.add(testAirport);
    }

    @Test
    public void testAdd() throws Exception {
        Airport addedAirport = airportService.getById(id);
        assertEquals("Add method failed: wrong city", addedAirport.getName(), testAirport.getName());
        airportService.delete(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Airport prepareToUpdateAirport = new Airport();
        prepareToUpdateAirport.setAid(id);
        prepareToUpdateAirport.setName("updatedCity");
        airportService.update(prepareToUpdateAirport);
        Airport updatedAirport = airportService.getById(id);
        assertEquals("Update method failed: wrong aid", prepareToUpdateAirport.getAid(), updatedAirport.getAid());
        assertEquals("Update method failed: wrong city", prepareToUpdateAirport.getName(), updatedAirport.getName());
        airportService.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        airportService.delete(id);
        assertNull("Delete method: failed", airportService.getById(id));
    }

    @After
    public void tearDown() throws Exception {
    }
}