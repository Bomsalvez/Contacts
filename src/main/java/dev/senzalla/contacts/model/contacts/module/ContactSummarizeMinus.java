package dev.senzalla.contacts.model.contacts.module;

import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ContactSummarizeMinus implements ContactList {
    private Long pkContact;
    private String nameContact;
    private Set<PhoneNumberDto> phonenumbers;
}
