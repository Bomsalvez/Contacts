package dev.senzalla.contacts.model.auth;

import jakarta.validation.constraints.NotNull;

public record Login(@NotNull String mail, @NotNull String password) {
}
