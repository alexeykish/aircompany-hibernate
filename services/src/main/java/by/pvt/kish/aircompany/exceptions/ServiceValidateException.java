package by.pvt.kish.aircompany.exceptions;

/**
 * This class represents a generic ServiceLoginException. It should wrap any exception of the underlying
 * code associated with validation.
 *
 * @author Kish Alexey
 */
public class ServiceValidateException extends Exception {
    /**
     * Constructs a ServiceValidateException with the given detail message.
     *
     * @param message The detail message of the ServiceException.
     */
    public ServiceValidateException(String message) {
        super(message);
    }

    /**
     * Constructs a ServiceValidateException with the given root cause.
     *
     * @param cause The root cause of the ServiceValidateException.
     */
    public ServiceValidateException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a ServiceValidateException with the given detail message and root cause.
     *
     * @param message The detail message of the ServiceValidateException.
     * @param cause   The root cause of the ServiceValidateException.
     */
    public ServiceValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
