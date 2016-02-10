/**
 *
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.ITeamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * This class represents a concrete implementation of the {@link ITeamDAO} interface.
 *
 * @author Kish Alexey
 */
public class TeamDAO implements ITeamDAO {

    private static final String SQL_ADD_TEAM = "INSERT INTO  teams (`t_eid`,`t_fid`) VALUES (?,?)";
    private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE t_fid = ?";
    private static final String SQL_GET_TEAM_BY_ID = "SELECT * FROM teams WHERE t_fid = ?";


    private static final String ADD_TEAM_COMMIT_FAIL = "Creating team failed (commit failed)";
    private static final String ADD_TEAM_ROLLBACK_FAIL = "Creating team failed (rollback failed)";
    private static final String DELETE_TEAM_FAIL = "Deleting team failed";
    private static final String GET_TEAM_BY_ID_FAIL = "Getting team by ID failed";



    private static TeamDAO instance;

    protected static Connection connection;
    protected PreparedStatement preparedStatement;

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

//    /**
//     * Create a given flight team in the database for a particular flight from the DB matching the given ID
//     *
//     * @param id   - The ID of the flight
//     * @param team - The flight team to be created
//     * @throws DaoException If something fails at DB level
//     */
//    @Override
//    public void add(Long id, List<Long> team) throws DaoException {
//        try {
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(SQL_ADD_TEAM);
//            for (Long i : team) {
//                preparedStatement.setLong(1, i);
//                preparedStatement.setLong(2, id);
//                preparedStatement.executeUpdate();
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//                throw new DaoException(ADD_TEAM_COMMIT_FAIL, e);
//            } catch (SQLException e2) {
//                throw new DaoException(ADD_TEAM_ROLLBACK_FAIL, e2);
//            }
//        } finally {
//            closePreparedStatement(preparedStatement);
//        }
//    }

//    /**
//     * Delete the given team from the database
//     *
//     * @param id - ID of the team to be deleted from the DB
//     * @throws DaoException If something fails at DB level
//     */
//    //@Override
//    public void delete(Long id) throws DaoException {
//        deleteEntity(connection, preparedStatement, id, SQL_DELETE_TEAM, DELETE_TEAM_FAIL);
//    }

//    /**
//     * Returns a list of employees of the flight team for particular flight from the DB matching the given ID
//     *
//     * @param id - The ID of the flight
//     * @return a list of the employees, that is a flight team
//     * @throws DaoException If something fails at DB level
//     */
//   //@Override
//    public Set<Employee> getById(Long id) throws DaoException {
//        ResultSet resultSet = null;
//        Set<Employee> team = new HashSet<>();
//        try {
//            preparedStatement = connection.prepareStatement(SQL_GET_TEAM_BY_ID);
//            preparedStatement.setLong(1, id);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                team.add(EmployeeDAO.getInstance().getById(resultSet.getLong(Column.TEAMS_EID)));
//            }
//        } catch (SQLException e) {
//            throw new DaoException(GET_TEAM_BY_ID_FAIL, e);
//        } finally {
//            closeResultSet(resultSet);
//            closePreparedStatement(preparedStatement);
//        }
//        return team;
//    }


}
