package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.pojos.PlaneCrew;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kish Alexey
 */
public class TeamCreatorTest {

    private Plane testPlane;
    private int numberOfPositions;

    @Before
    public void setUp() throws Exception {
        testPlane = new Plane();
        testPlane.setModel("testModel");
        testPlane.setCapacity(99999999);
        testPlane.setFlightRange(99999999);
        PlaneCrew testTeam = new PlaneCrew();
        numberOfPositions = 100;
        testTeam.setNumberOfPilots(numberOfPositions);
        testTeam.setNumberOfNavigators(numberOfPositions);
        testTeam.setNumberOfRadiooperators(numberOfPositions);
        testTeam.setNumberOfStewardesses(numberOfPositions);
        testPlane.setPlaneCrew(testTeam);
    }

    @Test
    public void testGetPlanePositions() throws Exception {
        List<String> list = TeamCreator.getPlanePositions(testPlane);
        assertEquals("Get plane positions failed: List size wrong", list.size(), numberOfPositions*4);
    }
}