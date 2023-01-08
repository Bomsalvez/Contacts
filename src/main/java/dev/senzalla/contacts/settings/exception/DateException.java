package dev.senzalla.contacts.settings.exception;

public class DateException extends RuntimeException {
    private static final String message = "Token Expirado";

    public DateException() {
        super(message);
    }
}
