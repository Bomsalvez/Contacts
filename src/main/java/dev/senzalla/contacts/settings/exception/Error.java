package dev.senzalla.contacts.settings.exception;

import lombok.Getter;

@Getter
public class Error {
    private final String error;
    private String property;

    public Error(String error) {
        this.error = error;
    }

    public Error(String error, String property) {
        this.error = error;
        this.property = property;
    }
}
