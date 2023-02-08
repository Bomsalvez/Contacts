package dev.senzalla.contacts.model.auth;

import jakarta.validation.constraints.NotBlank;

public record Login(@NotBlank String mail, @NotBlank String password) {
}
