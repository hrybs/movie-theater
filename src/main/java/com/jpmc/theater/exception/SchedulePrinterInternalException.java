package com.jpmc.theater.exception;

/**
 * Schedule printer Internal Exception
 */
public class SchedulePrinterInternalException extends RuntimeException {

    /**
     * Create instance
     * @param message error message
     * @param cause cause
     */
    public SchedulePrinterInternalException(String message, Throwable cause) {
        super(message, cause);
    }

}
