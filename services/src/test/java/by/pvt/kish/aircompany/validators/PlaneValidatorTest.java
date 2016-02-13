package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.pojos.PlaneCrew;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class PlaneValidatorTest {

    private Plane validPlane;
    private int numberOfPositions;
    private PlaneCrew testTeam;
    private PlaneValidator planeValidator = new PlaneValidator();

    @Before
    public void setUp() throws Exception {
        validPlane = new Plane();
        validPlane.setModel("testModel");
        validPlane.setCapacity(99999999);
        validPlane.setFlightRange(99999999);
        testTeam = new PlaneCrew();
        numberOfPositions = 100;
        testTeam.setNumberOfPilots(numberOfPositions);
        testTeam.setNumberOfNavigators(numberOfPositions);
        testTeam.setNumberOfRadiooperators(numberOfPositions);
        testTeam.setNumberOfStewardesses(numberOfPositions);
        validPlane.setPlaneCrew(testTeam);

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
        validPlane.setFlightRange(0);
        assertEquals("Validate method failed: range is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        validPlane.setFlightRange(99999999);
        testTeam.setNumberOfPilots(-1);
        validPlane.setPlaneCrew(testTeam);
        assertEquals("Validate method failed: number of pilots is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.setNumberOfPilots(numberOfPositions);
        testTeam.setNumberOfNavigators(-1);
        validPlane.setPlaneCrew(testTeam);
        assertEquals("Validate method failed: number of navigators is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.setNumberOfNavigators(numberOfPositions);
        testTeam.setNumberOfRadiooperators(-1);
        validPlane.setPlaneCrew(testTeam);
        assertEquals("Validate method failed: number of radiooperators is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        testTeam.setNumberOfRadiooperators(numberOfPositions);
        testTeam.setNumberOfStewardesses(-1);
        validPlane.setPlaneCrew(testTeam);
        assertEquals("Validate method failed: number of stewardess is null", planeValidator.validate(validPlane), Message.ERROR_EMPTY);
        assertEquals("Validate method failed: plane is null", planeValidator.validate(null), Message.ERROR_EMPTY);
    }
}