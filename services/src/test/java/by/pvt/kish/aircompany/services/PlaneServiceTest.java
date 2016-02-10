package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

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
        testPlane.setRange(200);
        Map<Position, Integer> testTeam = new TreeMap<>();
        testTeam.put(Position.PILOT, 1);
        testTeam.put(Position.NAVIGATOR, 1);
        testTeam.put(Position.RADIOOPERATOR, 1);
        testTeam.put(Position.STEWARDESS, 1);
        testPlane.setPlaneCrew(testTeam);
        id = planeService.add(testPlane);
    }

    @Test
    public void testAdd() throws Exception {
        Plane addedPlane = planeService.getById(id);
        assertEquals("Add method failed: wrong model", addedPlane.getModel(), testPlane.getModel());
        assertEquals("Add method failed: wrong capacity", addedPlane.getCapacity(), testPlane.getCapacity());
        assertEquals("Add method failed: wrong range", addedPlane.getRange(), testPlane.getRange());
        assertEquals("Add method failed: wrong team", addedPlane.getPlaneCrew(), testPlane.getPlaneCrew());
    }

    @Test
    public void testUpdate() throws Exception {
        Plane prepareToUpdatePlane = new Plane();
        prepareToUpdatePlane.setPid(id);
        prepareToUpdatePlane.setModel("updatedModel");
        prepareToUpdatePlane.setCapacity(300);
        prepareToUpdatePlane.setRange(400);
        Map<Position, Integer> updatedTeam = new TreeMap<>();
        updatedTeam.put(Position.PILOT, 2);
        updatedTeam.put(Position.NAVIGATOR, 2);
        updatedTeam.put(Position.RADIOOPERATOR, 2);
        updatedTeam.put(Position.STEWARDESS, 2);
        prepareToUpdatePlane.setPlaneCrew(updatedTeam);
        planeService.update(prepareToUpdatePlane);
        Plane updatedPlane = planeService.getById(id);
        assertEquals("Update method failed: wrong pid", prepareToUpdatePlane.getPid(), updatedPlane.getPid());
        assertEquals("Update method failed: wrong model", prepareToUpdatePlane.getModel(), updatedPlane.getModel());
        assertEquals("Update method failed: wrong capacity", prepareToUpdatePlane.getCapacity(), updatedPlane.getCapacity());
        assertEquals("Update method failed: wrong range", prepareToUpdatePlane.getRange(), updatedPlane.getRange());
        assertEquals("Update method failed: wrong team", prepareToUpdatePlane.getPlaneCrew(), updatedPlane.getPlaneCrew());
    }

    @Test
    public void testGetAll() throws Exception {
        int beforeAddNumber = planeService.getAll().size();
        Long getAllId = planeService.add(testPlane);
        int afterAddNumber = planeService.getAll().size();
        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber-1);
        planeService.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        planeService.delete(id);
        assertNull("Delete method: failed", planeService.getById(id));
    }

    @After
    public void tearDown() throws Exception {
        planeService.delete(id);

    }
}