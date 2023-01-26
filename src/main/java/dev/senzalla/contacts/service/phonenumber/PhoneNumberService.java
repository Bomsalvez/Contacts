package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneNumberService {
    private final SavePhoneNumberService savePhoneNumberService;

    public void addPhoneNumberToContact(Contacts contacts, ContactsCreating contactsCreating) {
        savePhoneNumberService.addPhoneNumberToContact(contacts, contactsCreating);
    }
}
