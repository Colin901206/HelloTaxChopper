package ca.taxchopper.taxserver.common;

/**
 * Common business exception
 */
public class GlobaException extends RuntimeException {
    public GlobaException(String errorMessage) {
        super(errorMessage);
    }
}
