package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.mapper.ContactsMapper;
import dev.senzalla.contacts.model.contact.module.ContactList;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindMultipleContactService {
    private final ContactsRepository contactsRepository;
    private final TokenService tokenService;

    public Page<ContactList> findMultipleContact(Pageable pageable, String nameContact, String token) {
        Page<Contacts> contacts = contactsRepository.findByNameContact(pageable, nameContact);
        boolean isTokenPresent = tokenService.checkValidToken(token);
        if (isTokenPresent) {
            return contacts.map(ContactsMapper::toContactsSummarize);
        } else {
            return contacts.map(ContactsMapper::toContactsMinimal);
        }
    }
}
