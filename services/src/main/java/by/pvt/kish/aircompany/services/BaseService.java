/**
 *
 */
package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The abstract class represents a implementation of the IService interface
 * The constructor is obtained from the connection pool database connection
 *
 * @author Kish Alexey
 */
public abstract class BaseService<T> implements IService<T> {

    private static Logger logger = Logger.getLogger(BaseService.class.getName());

    protected static Connection connection;

    protected BaseService() {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (IOException e) {
            logger.error("IOException at Service");
        } catch (SQLException e) {
            logger.error("SQLException at Service");
        } catch (PropertyVetoException e) {
            logger.error("PropertyVetoException at Service");
        }
    }
}
