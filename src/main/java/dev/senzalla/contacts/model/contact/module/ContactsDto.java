package dev.senzalla.contacts.model.contact.module;

import dev.senzalla.contacts.model.address.module.AddressDto;
import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ContactsDto {

    @NotBlank
    private String nameContact;

    @Past
    private LocalDate dateBirthContact;

    @Size(max = 30)
    private String nicknameContact;

    @Valid
    private Set<PhonenumberDto> phonenumbers;

    @Valid
    private Set<MailDto> mails;

    @Valid
    private Set<AddressDto> addresses;
}