package dev.senzalla.contacts.model.contact.module;

import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * A DTO for the {@link dev.senzalla.contacts.model.contact.entity.Contacts} entity
 */
@Getter
@Setter
public class ContactsSummarize implements ContactList {
    private Long pkContact;
    private String nameContact;
    private String nicknameContact;
    private Set<PhonenumberDto> phonenumbers;
    private Set<MailDto> mails;
}