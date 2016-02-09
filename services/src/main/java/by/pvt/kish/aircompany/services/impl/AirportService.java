package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.AirportValidator;

import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for airport model.
 *
 * @author Kish Alexey
 */
public class AirportService extends BaseService<Airport> {

    private static AirportService instance;
    private AirportDAO airportDAO = AirportDAO.getInstance();
    private AirportValidator airportValidator = new AirportValidator();

    /**
     * Returns an synchronized instance of a AirportService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a AirportService
     */
    public synchronized static AirportService getInstance() {
        if (instance == null) {
            instance = new AirportService();
        }
        return instance;
    }

    @Override
    public Long add(Airport airport) throws ServiceException, ServiceValidateException {
        return addEntity(airportDAO, airport, airportValidator);
    }

    @Override
    public void update(Airport airport) throws ServiceException, ServiceValidateException {
        updateEntity(airportDAO, airport, airportValidator);
    }

    @Override
    public List<Airport> getAll() throws ServiceException {
        return getAllEntities(airportDAO);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        deleteEntity(airportDAO, id);
    }

    @Override
    public Airport getById(Long id) throws ServiceException {
        return getByIdEntity(airportDAO, id);
    }
}
