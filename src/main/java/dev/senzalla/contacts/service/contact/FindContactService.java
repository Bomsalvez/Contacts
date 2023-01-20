package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.mapper.ContactsMapper;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindContactService {
    private final ContactsRepository contactsRepository;

    public ContactsDto findContact(Long pkContact) {
        Contacts contacts = contactsRepository.findByPkContact(pkContact).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
        return ContactsMapper.toContactsDto(contacts);
    }
}
