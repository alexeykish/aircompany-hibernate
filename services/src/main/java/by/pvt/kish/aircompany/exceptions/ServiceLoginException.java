package by.pvt.kish.aircompany.exceptions;

/**
 * This class represents a generic ServiceLoginException. It should wrap any exception of the underlying
 * code associated with authorization.
 *
 * @author Kish Alexey
 */
public class ServiceLoginException extends Exception {
    /**
     * Constructs a ServiceLoginException with the given detail message.
     *
     * @param message The detail message of the ServiceLoginException.
     */
    public ServiceLoginException(String message) {
        super(message);
    }

    /**
     * Constructs a ServiceLoginException with the given root cause.
     *
     * @param cause The root cause of the ServiceLoginException.
     */
    public ServiceLoginException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a ServiceLoginException with the given detail message and root cause.
     *
     * @param message The detail message of the ServiceLoginException.
     * @param cause   The root cause of the ServiceLoginException.
     */
    public ServiceLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
