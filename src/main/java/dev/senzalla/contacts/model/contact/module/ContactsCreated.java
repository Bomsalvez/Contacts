package dev.senzalla.contacts.model.contact.module;

import dev.senzalla.contacts.model.address.module.AddressCreated;
import dev.senzalla.contacts.model.mail.module.MailCreated;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberCreated;
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
public class ContactsCreated {
    private Long pkContact;

    @NotBlank
    private String nameContact;

    @Past
    private LocalDate dateBirthContact;

    @Size(max = 30)
    private String nicknameContact;

    @Valid
    private Set<PhonenumberCreated> phonenumbers;

    @Valid
    private Set<MailCreated> mails;

    @Valid
    private Set<AddressCreated> addresses;
}