package by.pvt.kish.aircompany.exceptions;

/**
 * @author Kish Alexey
 */
public class RequestHandlerException extends Exception {
    /**
     * Constructs a RequestHandlerException with the given detail message.
     * @param message The detail message of the RequestHandlerException.
     */
    public RequestHandlerException(String message) {
        super(message);
    }

    /**
     * Constructs a RequestHandlerException with the given root cause.
     * @param cause The root cause of the RequestHandlerException.
     */
    public RequestHandlerException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a RequestHandlerException with the given detail message and root cause.
     * @param message The detail message of the RequestHandlerException.
     * @param cause The root cause of the RequestHandlerException.
     */
    public RequestHandlerException (String message, Throwable cause) {
        super(message, cause);
    }
}
