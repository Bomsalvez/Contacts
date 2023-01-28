package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.module.ContactList;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
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
    private final DeleteContactService deleteContactService;

    public ContactsCreated addContact(ContactsDto contactsDto) {
        return saveContactService.addContact(contactsDto);
    }

    public ContactsCreated findContactDto(Long pkContact) {
        return findContactService.findContactDto(pkContact);
    }

    public Page<ContactList> findMultipleContact(Pageable pageable, String nameContact, String token) {
        return findMultipleContactService.findMultipleContact(pageable, nameContact, token);
    }

    public ContactsCreated editContact(ContactsDto contactsDto, Long pkContact) {
        return saveContactService.editContact(contactsDto, pkContact);
    }

    public void deleteContact(Long pkContact) {
        deleteContactService.delete(pkContact);
    }
}
