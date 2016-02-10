package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.pojos.Airport;

/**
 * This class represents a concrete implementation of the IDAO interface for airport model.
 *
 * @author Kish Alexey
 */
public class AirportDAO extends BaseDAO<Airport> {

    private static AirportDAO instance;

    private AirportDAO() {
        super(Airport.class);
    }

    /**
     * Returns an synchronized instance of a AirportDAO, if the instance does not exist yet - create a new
     *
     * @return - a instance of a AirportDAO
     */
    public synchronized static AirportDAO getInstance() {
        if (instance == null) {
            instance = new AirportDAO();
        }
        return instance;
    }
}
