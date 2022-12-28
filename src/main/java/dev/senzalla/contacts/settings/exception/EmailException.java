package dev.senzalla.contacts.settings.exception;

public class EmailException extends RuntimeException {
    private static final String message = "Falha ao conectar com o gmail";

    public EmailException() {
        super(message);
    }
}
