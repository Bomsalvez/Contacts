package dev.senzalla.contacts.settings.exception;

public class ExternalUser extends RuntimeException {
    private static final String message = "Usuario não permitido ou nao encontrado!";

    public ExternalUser() {
        super(message);
    }
}
