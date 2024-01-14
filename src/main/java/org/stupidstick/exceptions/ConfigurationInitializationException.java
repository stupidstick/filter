package org.stupidstick.exceptions;

public class ConfigurationInitializationException extends RuntimeException {
    public ConfigurationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
