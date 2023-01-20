package dev.senzalla.contacts.model.contact.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContactsDto {
    private Long pkContact;

    @NotBlank
    private String nameContact;

    @Past
    private LocalDate dateBirthContact;

    @Size(max = 30)
    private String nicknameContact;
}