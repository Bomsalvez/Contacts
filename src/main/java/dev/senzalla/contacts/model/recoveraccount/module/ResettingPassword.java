package dev.senzalla.contacts.model.recoveraccount.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResettingPassword(@NotBlank String password, @Email @NotBlank String mail) {
}
