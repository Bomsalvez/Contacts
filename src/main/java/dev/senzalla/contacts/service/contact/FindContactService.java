package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.mapper.ContactsMapper;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FindContactService {
    private final ContactsRepository contactsRepository;

    public ContactsCreated findContactDto(Long pkContact) {
        Contacts contacts = findContact(pkContact);
        return ContactsMapper.toContactsCreated(contacts);
    }

    public Contacts findContact(Long pkContact) {
        return contactsRepository.findByPkContact(pkContact).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
    }
}
