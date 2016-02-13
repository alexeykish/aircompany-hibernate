package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.pojos.PlaneCrew;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class PlaneServiceTest {

    private PlaneService planeService = PlaneService.getInstance();
    private Long id;
    private Plane testPlane;

    @Before
    public void setUp() throws Exception {
        testPlane = new Plane();
        testPlane.setModel("testModel");
        testPlane.setCapacity(100);
        testPlane.setFlightRange(200);
        PlaneCrew testTeam = new PlaneCrew();
        testTeam.setNumberOfPilots(1);
        testTeam.setNumberOfNavigators(1);
        testTeam.setNumberOfRadiooperators(1);
        testTeam.setNumberOfStewardesses(1);
        testPlane.setPlaneCrew(testTeam);
        testTeam.setPlane(testPlane);
        id = planeService.add(testPlane);
    }

    @Test
    public void testAdd() throws Exception {
        Plane addedPlane = planeService.getById(id);
        testPlane.setPid(id);
        assertEquals("Add method failed: wrong model", addedPlane, testPlane);
        planeService.delete(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Plane prepareToUpdatePlane = planeService.getById(id);
        prepareToUpdatePlane.setModel("updatedModel");
        prepareToUpdatePlane.setCapacity(300);
        prepareToUpdatePlane.setFlightRange(400);

        PlaneCrew updatedCrew = new PlaneCrew(2, 2, 2, 2);
        updatedCrew.setPid(id);
        prepareToUpdatePlane.setPlaneCrew(updatedCrew);
        planeService.update(prepareToUpdatePlane);

        Plane updatedPlane = planeService.getById(id);

        assertEquals("Update method failed: wrong pid", prepareToUpdatePlane, updatedPlane);
        planeService.delete(id);
    }


    @Test
    public void testDelete() throws Exception {
        planeService.delete(id);
        assertNull("Delete method: failed", planeService.getById(id));
    }

    @After
    public void tearDown() throws Exception {

    }
}