/**
 *
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.ITeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.utils.DaoUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.utils.DaoUtils.closeResultSet;
import static by.pvt.kish.aircompany.utils.DaoUtils.deleteEntity;

/**
 * This class represents a concrete implementation of the {@link ITeamDAO} interface.
 *
 * @author Kish Alexey
 */
public class TeamDAO extends BaseDAO implements ITeamDAO {

    private static final String SQL_ADD_TEAM = "INSERT INTO  teams (`t_eid`,`t_fid`) VALUES (?,?)";
    private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE t_fid = ?";
    private static final String SQL_GET_TEAM_BY_ID = "SELECT * FROM teams WHERE t_fid = ?";
    private static final String SQL_GET_EMPLOYEE_AVAILABILITY = "SELECT * FROM teams JOIN flights on flights.fid=teams.t_fid WHERE  flights.date = ? HAVING t_eid = ?";

    private static final String ADD_TEAM_COMMIT_FAIL = "Creating team failed (commit failed)";
    private static final String ADD_TEAM_ROLLBACK_FAIL = "Creating team failed (rollback failed)";
    private static final String DELETE_TEAM_FAIL = "Deleting team failed";
    private static final String GET_TEAM_BY_ID_FAIL = "Getting team by ID failed";
    private static final String GET_USER_AVAILABILITY_FAIL = "Getting user availability failed";


    private static TeamDAO instance;

    private TeamDAO() {
        super();
    }

    /**
     * Returns an synchronized instance of a TeamDAO, if the instance does not exist yet - create a new
     *
     * @return - a instance of a TeamDAO
     */
    public synchronized static TeamDAO getInstance() {
        if (instance == null) {
            instance = new TeamDAO();
        }
        return instance;
    }

    /**
     * Create a given flight team in the database for a particular flight from the DB matching the given ID
     *
     * @param id   - The ID of the flight
     * @param team - The flight team to be created
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void add(Long id, List<Long> team) throws DaoException {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_ADD_TEAM);
            for (Long i : team) {
                preparedStatement.setLong(1, i);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DaoException(ADD_TEAM_COMMIT_FAIL, e);
            } catch (SQLException e2) {
                throw new DaoException(ADD_TEAM_ROLLBACK_FAIL, e2);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Delete the given team from the database
     *
     * @param id - ID of the team to be deleted from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void delete(Long id) throws DaoException {
        deleteEntity(connection, preparedStatement, id, SQL_DELETE_TEAM, DELETE_TEAM_FAIL);
    }

    /**
     * Returns a list of employees of the flight team for particular flight from the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @return a list of the employees, that is a flight team
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Employee> getById(Long id) throws DaoException {
        ResultSet resultSet = null;
        List<Employee> team = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TEAM_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team.add(EmployeeDAO.getInstance().getById(resultSet.getLong(Column.TEAMS_EID)));
            }
        } catch (SQLException e) {
            throw new DaoException(GET_TEAM_BY_ID_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return team;
    }

    /**
     * Check if the employee is in another flight teams at that date
     *
     * @param id - The ID of hte employee
     * @param flightDate - The flight date
     * @return - false if employee isn't in another flights at that date, true - if employee is busy at that date
     * @throws DaoException If something fails at DB level
     */
    public boolean checkEmployeeAvailability(Long id, Date flightDate) throws DaoException {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE_AVAILABILITY);
            preparedStatement.setDate(1, flightDate);
            preparedStatement.setLong(2, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(GET_USER_AVAILABILITY_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return false;
    }

    @Override
    public Long add(Object o) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Object o) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getAll() throws DaoException {
        throw new UnsupportedOperationException();
    }


}
