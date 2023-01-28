package dev.senzalla.contacts.service.contacts;

import dev.senzalla.contacts.model.contacts.module.ContactList;
import dev.senzalla.contacts.model.contacts.module.ContactsCreated;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactService {
    private final SaveContactService saveContactService;
    private final FindContactService findContactService;
    private final FindMultipleContactService findMultipleContactService;

    public ContactsCreated addContact(ContactsCreating contactsCreating) {
        return saveContactService.addContact(contactsCreating);
    }

    public ContactsCreated findContact(Long pkContact) {
        return findContactService.findContact(pkContact);
    }

    public Page<ContactList> findMultipleContact(Pageable pageable, String nameContact, String token) {
        return findMultipleContactService.findMultipleContact(pageable, nameContact, token);
    }
}
