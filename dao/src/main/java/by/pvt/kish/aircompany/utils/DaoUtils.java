package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for DAO classes. This class contains commonly used DAO logic which is been refactored in
 * single static methods.
 *
 * @author Kish Alexey
 */
public class DaoUtils {

    private static Logger logger = Logger.getLogger(DaoUtils.class.getName());

    /**
     * Delete the given pojos by ID from the DB
     *
     * @param connection        - DB connection
     * @param preparedStatement - processed statement
     * @param id                - The ID of the pojos to be deleted
     * @param sqlQuery          - The SQL request to the DB to delete the pojos
     * @param failMessage       - The message to throw it in exception when SQLException happens
     * @throws DaoException If something fails at DB level
     */
    public static void deleteEntity(Connection connection, PreparedStatement preparedStatement, Long id, String sqlQuery, String failMessage) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(failMessage, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Set the given pojos status by ID from the DB
     *
     * @param connection        - DB connection
     * @param preparedStatement - processed statement
     * @param id                - The ID of the pojos to be setted
     * @param status            - The status of the pojos to be setted
     * @param sqlQuery          - The SQL request to the DB to delete the pojos
     * @param failMessage       - The message to throw it in exception when SQLException happens
     * @throws DaoException If something fails at DB level
     */
    public static void setEntityStatus(Connection connection, PreparedStatement preparedStatement, Long id, String status, String sqlQuery, String failMessage) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(failMessage, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Close ResultSet object as soon as you finish working with ResultSet to prevent memory leaks
     *
     * @param resultSet - The resultSet to be closed
     * @throws DaoException If ResultSet is already null
     */
    public static void closeResultSet(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.debug("resultSet is null" + e);
                throw new DaoException("resultSet is null" + e);
            }
        }
    }

    /**
     * Close PreparedStatement object as soon as you finish working with PreparedStatement to prevent memory leaks
     *
     * @param statement - The preparedStatement to be closed
     * @throws DaoException If preparedStatement is already null
     */
    public static void closePreparedStatement(PreparedStatement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.debug("preparedStatement is null" + e);
                throw new DaoException("preparedStatement is null" + e);
            }
        }
    }

    /**
     * Close Connection object as soon as you finish working with Connection to prevent memory leaks
     *
     * @param connection - The connection to be closed
     * @throws DaoException If connection is already null
     */
    public static void closeConnection(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.debug("connection is null" + e);
                throw new DaoException("connection is null" + e);
            }
        }
    }
}
