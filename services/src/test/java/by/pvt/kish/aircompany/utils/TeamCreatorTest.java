package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

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
        testPlane.setRange(99999999);
        Map<Position, Integer> testTeam = new TreeMap<>();
        numberOfPositions = 100;
        testTeam.put(Position.PILOT, numberOfPositions);
        testTeam.put(Position.NAVIGATOR, numberOfPositions);
        testTeam.put(Position.RADIOOPERATOR, numberOfPositions);
        testTeam.put(Position.STEWARDESS, numberOfPositions);
        testPlane.setPlaneCrew(testTeam);
    }

    @Test
    public void testGetPlanePositions() throws Exception {
        List<String> list = TeamCreator.getPlanePositions(testPlane);
        assertEquals("Get plane positions failed: List size wrong", list.size(), numberOfPositions*4);
    }
}