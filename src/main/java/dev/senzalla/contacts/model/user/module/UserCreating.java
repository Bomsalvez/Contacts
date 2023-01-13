package dev.senzalla.contacts.model.user.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreating {
    @NotBlank
    private String nameUser;
    @Email
    @NotBlank
    private String mailUser;
    @NotBlank
    private String passwordUser;
}
