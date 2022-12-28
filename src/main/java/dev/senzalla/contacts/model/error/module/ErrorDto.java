package dev.senzalla.contacts.model.error.module;

import lombok.Getter;

@Getter
public class ErrorDto {
    private final String error;
    private String property;

    public ErrorDto(String error) {
        this.error = error;
    }

    public ErrorDto(String error, String property) {
        this.error = error;
        this.property = property;
    }
}
