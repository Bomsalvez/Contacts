package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.module.ContactsDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactService {
    private final SaveContactService saveContactService;
    private final FindContactService findContactService;

    public ContactsDto addContact(ContactsDto contactsDto) {
        return saveContactService.addContact(contactsDto);
    }

    public ContactsDto findContact(Long pkContact) {
        return findContactService.findContact(pkContact);
    }
}
