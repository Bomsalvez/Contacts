package dev.senzalla.contacts.service.contacts;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.mapper.ContactMapper;
import dev.senzalla.contacts.model.contacts.module.ContactsCreated;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindContactService {
    private final ContactsRepository contactsRepository;

    public ContactsCreated findContact(Long pkContact) {
        Contacts contacts = contactsRepository.findById(pkContact).orElseThrow(() -> new NotFoundException("Contato nao encontrado"));
        return ContactMapper.toContactsCreated(contacts);
    }
}
