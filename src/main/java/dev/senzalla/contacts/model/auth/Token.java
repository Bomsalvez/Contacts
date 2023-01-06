package dev.senzalla.contacts.model.auth;

public record Token(String HEADER, String token) {
    public Token(String token) {
        this("Bearer", token);
    }
}
