package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class PlaneValidatorTest {

    private Plane validPlane;
    private int numberOfPositions;
    private Map<Position, Integer> testTeam;
    private PlaneValidator planeValidator = new PlaneValidator();

    @Before
    public void setUp() throws Exception {
        validPlane = new Plane();
        validPlane.setModel("testModel");
        validPlane.setCapacity(99999999);
        validPlane.setRange(99999999);
        testTeam = new TreeMap<>();
        numberOfPositions = 100;
        testTeam.put(Position.PILOT, numberOfPositions);
        testTeam.put(Position.NAVIGATOR, numberOfPositions);
        testTeam.put(Position.RADIOOPERATOR, numberOfPositions);
        testTeam.put(Position.STEWARDESS, numberOfPositions);
        validPlane.setTeam(testTeam);

    }

    @Test
    public void testValidate() throws Exception {
        assertNull("Validate method failed", planeValidator.validate(validPlane));
        validPlane.setModel(null);
        assertEquals("Validate method failed: model is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        validPlane.setModel("testModel");
        validPlane.setCapacity(0);
        assertEquals("Validate method failed: capacity is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        validPlane.setCapacity(99999999);
        validPlane.setRange(0);
        assertEquals("Validate method failed: range is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        validPlane.setRange(99999999);
        testTeam.put(Position.PILOT, -1);
        validPlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of pilots is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.put(Position.PILOT, numberOfPositions);
        testTeam.put(Position.NAVIGATOR, -1);
        validPlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of navigators is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.put(Position.NAVIGATOR, numberOfPositions);
        testTeam.put(Position.RADIOOPERATOR, -1);
        validPlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of radiooperators is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.put(Position.RADIOOPERATOR, numberOfPositions);
        testTeam.put(Position.STEWARDESS, -1);
        validPlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of stewardess is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        assertEquals("Validate method failed: plane is null", planeValidator.validate(null), Message.ERROR_EMPTY);
    }
}