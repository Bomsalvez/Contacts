package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.module.ContactList;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
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

    public ContactsDto addContact(ContactsDto contactsDto) {
        return saveContactService.addContact(contactsDto);
    }

    public ContactsDto findContact(Long pkContact) {
        return findContactService.findContact(pkContact);
    }

    public Page<ContactList> findMultipleContact(Pageable pageable, String nameContact, String token) {
        return findMultipleContactService.findMultipleContact(pageable, nameContact, token);
    }
}
