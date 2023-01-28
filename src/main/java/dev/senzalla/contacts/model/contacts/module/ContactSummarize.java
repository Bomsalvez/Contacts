package dev.senzalla.contacts.model.contacts.module;

import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ContactSummarize implements ContactList{
    private Long pkContact;
    private String nameContact;
    private String nicknameContact;
    private Set<PhoneNumberDto> phonenumbers;
}
