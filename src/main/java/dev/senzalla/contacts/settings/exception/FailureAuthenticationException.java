package dev.senzalla.contacts.settings.exception;

public class FailureAuthenticationException extends RuntimeException {
    private static final String message = "Usuario não permitido ou nao encontrado!";

    public FailureAuthenticationException() {
        super(message);
    }
}
