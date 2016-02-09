/**
 *
 */
package by.pvt.kish.aircompany.pool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class implements the connection pool
 * The data for the connection to the DB are taken from the file db.properties
 * The number of connections defaults to 8
 *
 * @author Kish Alexey
 */
public class ConnectionPool {

    static Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool instance;
    private static BasicDataSource dataSource = new BasicDataSource();
    private static final ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    private ConnectionPool() throws IOException, SQLException, PropertyVetoException {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        dataSource.setDriverClassName(bundle.getString("SQL_DB_DRIVER_CLASS"));
        dataSource.setUsername(bundle.getString("SQL_DB_USERNAME"));
        dataSource.setPassword(bundle.getString("SQL_DB_PASSWORD"));
        dataSource.setUrl(bundle.getString("SQL_DB_URL"));
    }

    /**
     * Returns an instance of a connection pool, if the instance does not exist yet - create a new
     *
     * @return - connection pool
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    public synchronized static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
        if (instance == null) {
            instance = new ConnectionPool();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Returns a copy of the connection as a variable of the thread, if the thread was not the connection is to be set to thread variable
     *
     * @return - DB connection
     * @throws SQLException If there is a failure DB connection
     */
    public Connection getConnection() throws SQLException {

        if (threadConnection.get() == null) {
            Connection connection = dataSource.getConnection();
            threadConnection.set(connection);
            return threadConnection.get();
        } else
            return threadConnection.get();
    }

}


