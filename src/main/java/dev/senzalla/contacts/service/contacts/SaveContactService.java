package dev.senzalla.contacts.service.contacts;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.mapper.ContactMapper;
import dev.senzalla.contacts.model.contacts.module.ContactsCreated;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.service.phonenumber.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SaveContactService {
    private final ContactsRepository contactsRepository;
    private final PhoneNumberService phoneNumberService;


    public ContactsCreated addContact(ContactsCreating contactsCreating) {
        Contacts contacts = ContactMapper.toContacts(contactsCreating);
        Optional<Contacts> optionalContacts = contactsRepository.findByNameContactAndDateBirthContactOrNicknameContact(contactsCreating.getNameContact(), contactsCreating.getDateBirthContact(), contactsCreating.getNicknameContact());
        if (optionalContacts.isPresent()) {
            contacts.setPkContact(optionalContacts.get().getPkContact());
        }
        contacts = contactsRepository.save(contacts);
        phoneNumberService.addPhoneNumberToContact(contacts, contactsCreating);
        return ContactMapper.toContactsCreated(contacts);
    }
}
