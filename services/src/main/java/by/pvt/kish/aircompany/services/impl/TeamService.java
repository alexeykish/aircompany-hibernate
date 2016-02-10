package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.dao.impl.TeamDAO;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.ITeamService;
import by.pvt.kish.aircompany.utils.ServiceUtils;
import by.pvt.kish.aircompany.validators.TeamValidator;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * This class represents a concrete implementation of the ITeamService interface for flight team.
 *
 * @author Kish Alexey
 */
public class TeamService extends BaseService implements ITeamService {

    private static Logger logger = Logger.getLogger(TeamService.class.getName());

    private static TeamService instance;
    private TeamDAO teamDAO = TeamDAO.getInstance();

    private TeamService() {
        super();
    }

    /**
     * Returns an synchronized instance of a TeamService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a TeamService
     */
    public synchronized static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    /**
     * Create a given flight team for a particular flight matching the given ID
     *
     * @param id   - The ID of the flight
     * @param team - The flight team to be created
     * @throws ServiceException         - if something fails at Service layer
     * @throws ServiceValidateException - if something fails at Service validation
     */
    @Override
    public void add(Long id, List<Long> team) throws ServiceException, ServiceValidateException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            String validateResult = TeamValidator.validate(id, team);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            connection.setAutoCommit(false);
            teamDAO.delete(id);
            teamDAO.add(id, team);
            FlightDAO.getInstance().setStatus(id, FlightStatus.READY.toString());
            connection.commit();
        } catch (SQLException e) {
            try {
                logger.debug("Add team: commit failed");
                connection.rollback();
                throw new ServiceException(e);
            } catch (SQLException e2) {
                logger.debug("Add team: rollback failed");
                throw new ServiceException(e);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long add(Object o) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Object o) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getAll() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) throws ServiceException {
        ServiceUtils.deleteEntity(teamDAO, id);
    }

    /**
     * Returns a list of employees of the flight team for particular flight matching the given ID
     *
     * @param id - The ID of the flight
     * @return a list of the employees, that is a flight team
     * @throws ServiceException - if something fails at Service layer
     */
    @Override
    public List<Employee> getById(Long id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            return teamDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean checkEmployeeAvailability(Long id, Date flightDate) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(Message.ERROR_ID_MISSING);
        }
        try {
            return TeamDAO.getInstance().checkEmployeeAvailability(id, flightDate);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
