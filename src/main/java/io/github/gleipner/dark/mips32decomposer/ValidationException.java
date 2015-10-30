package io.github.gleipner.dark.mips32decomposer;

/**
 * This exception should be thrown whenever an object of some type attempts
 * to validate itself and some discrepancy which may not be amended is detected.
 */
public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}