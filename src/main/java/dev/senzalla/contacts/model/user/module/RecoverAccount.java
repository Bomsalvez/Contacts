package dev.senzalla.contacts.model.user.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecoverAccount(@NotBlank String password, @Email @NotBlank String mail) {
}
