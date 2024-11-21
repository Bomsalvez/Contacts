package dev.senzalla.contacts.model.contact.module;

import dev.senzalla.contacts.model.phonenumber.module.PhonenumberCreated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * A DTO for the {@link dev.senzalla.contacts.model.contact.entity.Contacts} entity
 */
@Getter
@Setter
public class ContactsMinimal implements ContactList {
    private Long pkContact;
    @NotBlank
    private String nameContact;
    private Set<PhonenumberCreated> phoneNumbers;
}