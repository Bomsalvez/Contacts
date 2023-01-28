package dev.senzalla.contacts.model.contacts.module;

import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ContactsCreating {
    @NotBlank
    private String nameContact;
    @Past
    private LocalDate dateBirthContact;
    private String nicknameContact;
    private Set<PhoneNumberDto> phonenumbers;
}
