package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.dao.impl.PlaneDAO;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.pojos.PlaneCrew;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class PlaneDAOTest {

    private PlaneDAO planeDAO = PlaneDAO.getInstance();;
    private Long id;
    private Plane testPlane;
    private PlaneCrew testCrew;
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        testPlane = new Plane("testModel", 100, 200);
        testCrew = new PlaneCrew(1, 1, 1, 1);

        testPlane.setPlaneCrew(testCrew);
        testCrew.setPlane(testPlane);

        transaction = util.getSession().beginTransaction();
        id = planeDAO.add(testPlane);
    }

    @Test
    public void testAdd() throws Exception {
        Plane addedPlane = planeDAO.getById(id);
        assertEquals("Add method failed: wrong plane", addedPlane, testPlane);
        assertEquals("Add method failed: wrong planeCrew", addedPlane.getPlaneCrew(), testCrew);
        planeDAO.delete(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Plane prepareToUpdatePlane = planeDAO.getById(id);
        prepareToUpdatePlane.setModel("updatedModel");
        prepareToUpdatePlane.setCapacity(300);
        prepareToUpdatePlane.setFlightRange(400);

        PlaneCrew updatedCrew = new PlaneCrew(2, 2, 2, 2);
        updatedCrew.setPid(id);
        prepareToUpdatePlane.setPlaneCrew(updatedCrew);
        planeDAO.update(prepareToUpdatePlane);

        Plane updatedPlane = planeDAO.getById(id);
        assertEquals("Update method failed: wrong pid", prepareToUpdatePlane, updatedPlane);
        assertEquals("Update method failed: wrong model", prepareToUpdatePlane.getPlaneCrew(), updatedPlane.getPlaneCrew());
        planeDAO.delete(id);
    }

    @Test
    public void testGetAll() throws Exception {
        int countPlanes = planeDAO.getAll().size();
        int countLines = planeDAO.getCount();
        assertEquals("Get all method failed", countLines, countPlanes);
        planeDAO.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        planeDAO.delete(id);
        assertNull("Delete method: failed", planeDAO.getById(id));
    }

    @Test

    public void testSetStatus() throws Exception {
        Plane prepareToUpdateStatusPlane = planeDAO.getById(id);
        planeDAO.setPlaneStatus(id, PlaneStatus.BLOCKED);
        Plane updatedStatusPlane = planeDAO.getById(id);
        assertEquals("Update method failed: wrong status", updatedStatusPlane.getStatus(), prepareToUpdateStatusPlane.getStatus());
        planeDAO.delete(id);
    }

    @Test
    public void testGetAllAvailable() throws  Exception {
        Plane plane1 = new Plane("model1", 100, 100);
        plane1.setStatus(PlaneStatus.AVAILABLE);
        Plane plane2 = new Plane("model2", 100, 100);
        plane2.setStatus(PlaneStatus.BLOCKED);
        Long pid1 = planeDAO.add(plane1);
        Long pid2 = planeDAO.add(plane2);
        Flight flight = new Flight();
        Date testDate1 = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
        flight.setDate(testDate1);
        flight.setPlane(testPlane);
        Long fid = FlightDAO.getInstance().add(flight);
        List<Plane> planes = planeDAO.getAllAvailablePlanes(testDate1);
        for (Plane p: planes) {
            System.out.println(p);
        }
        System.out.println("List size = " + planes.size());
        planeDAO.delete(pid1);
        planeDAO.delete(pid2);
        FlightDAO.getInstance().delete(fid);
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }
}