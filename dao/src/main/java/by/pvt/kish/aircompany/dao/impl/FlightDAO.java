package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.IFlightDAO;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Flight;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * This class represents a concrete implementation of the IDAO interface for flight model.
 *
 * @author Kish Alexey
 */
public class FlightDAO extends BaseDAO<Flight> implements IFlightDAO{

    private static final String HQL_GET_PLANES_LAST_FIVE_FLIGHTS = "select F from Flight F where F.plane = ? ORDER BY f.date DESC";
    private static final String HQL_GET_EMPLOYEES_LAST_FIVE_FLIGHTS = "SELECT E.flights FROM Employee E where E.eid = ?";
    private static final String HQL_UPDATE_FLIGHT_STATUS = "UPDATE FROM Flight f SET f.status =:status WHERE f.fid =:id";

    private static final String GET_PLANE_FLIGHTS_FAIL = "Getting planes flights failed";
    private static final String GET_EMPLOYEES_FLIGHTS_FAIL = "Getting employees flights failed";
    private static final String UPDATE_FLIGHT_STATUS_FAIL = "Updating flight status failed";


    private static FlightDAO instance;
    private HibernateUtil util = HibernateUtil.getUtil();

    private FlightDAO() {
        super(Flight.class);
    }

    /**
     * Returns an synchronized instance of a FlightDAO, if the instance does not exist yet - create a new
     * @return - a instance of a FlightDAO
     */
    public synchronized static FlightDAO getInstance() {
        if (instance == null) {
            instance = new FlightDAO();
        }
        return instance;
    }

    /**
     * Returns a list of five last flights of the concrete plane from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete plane
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Flight> getPlaneLastFiveFlights(Long id) throws DaoException {
        return getLastFiveFlights(id, HQL_GET_PLANES_LAST_FIVE_FLIGHTS, GET_PLANE_FLIGHTS_FAIL);
    }

    /**
     * Returns a list of five last flights of the concrete employee from the DB
     *
     * @param id - The ID of the plane
     * @return - the list of last five flight of the concrete employee
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Flight> getEmployeeLastFiveFlights(Long id) throws DaoException {
        return getLastFiveFlights(id, HQL_GET_EMPLOYEES_LAST_FIVE_FLIGHTS, GET_EMPLOYEES_FLIGHTS_FAIL);
    }

    /**
     * Update particular plane status int the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @throws DaoException If something fails at DB level
     */
    public void setStatus(Long id, FlightStatus status) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery(HQL_UPDATE_FLIGHT_STATUS);
            query.setParameter("status",status);
            query.setParameter("id",id);
            query.executeUpdate();
        } catch (HibernateException e) {
            throw new DaoException(UPDATE_FLIGHT_STATUS_FAIL);
        }
    }

    public List<Flight> getLastFiveFlights(Long id, String hqlQuery, String failMessage) throws DaoException {
        List<Flight> flights;
        try {
            Session session = util.getSession();
            Query query = session.createQuery(hqlQuery);
            query.setParameter(0,id);
            query.setMaxResults(5);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(failMessage, e);
        }
        return flights;
    }


}
