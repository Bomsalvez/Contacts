package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.mapper.ContactsMapper;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.service.mail.MailService;
import dev.senzalla.contacts.service.phonenumber.PhonenumberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SaveContactService {
    private final ContactsRepository contactsRepository;
    private final PhonenumberService phonenumberService;
    private final MailService mailService;

    public ContactsCreated addContact(ContactsDto contactsDto) {
        Contacts contacts = ContactsMapper.toContacts(contactsDto);
        checkContactExist(contacts);
        return saveContact(contacts, contactsDto);
    }

    public ContactsCreated editContact(ContactsDto contactsDto, Long pkContact) {
        Contacts contacts = ContactsMapper.toContacts(contactsDto);
        contacts.setPkContact(pkContact);
        return saveContact(contacts, contactsDto);
    }

    private void checkContactExist(Contacts contacts) {
        Optional<Contacts> oldContacts = contactsRepository.findByNameContactIgnoreCaseAndNicknameContactIgnoreCaseAndDateBirthContact(contacts.getNameContact(), contacts.getNicknameContact(), contacts.getDateBirthContact());
        oldContacts.ifPresent(value -> contacts.setPkContact(value.getPkContact()));
    }

    private ContactsCreated saveContact(Contacts contacts, ContactsDto contactsDto) {
        contactsRepository.save(contacts);
        phonenumberService.addPhonenumberToContact(contactsDto.getPhoneNumbers(), contacts);
        mailService.addMailToContact(contactsDto.getMails(), contacts);
        return ContactsMapper.toContactsCreated(contacts);
    }
}
