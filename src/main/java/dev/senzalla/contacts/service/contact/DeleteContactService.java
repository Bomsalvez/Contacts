package dev.senzalla.contacts.service.contact;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.service.mail.MailService;
import dev.senzalla.contacts.service.phonenumber.PhonenumberService;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeleteContactService {
    private final ContactsRepository contactsRepository;
    private final MailService mailService;
    private final PhonenumberService phonenumberService;

    public void delete(Long pkContact) {
        Contacts contacts = contactsRepository.findByPkContact(pkContact).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
        mailService.deleteMail(contacts.getMails());
        phonenumberService.deletePhonenumber(contacts.getPhoneNumbers());
        contactsRepository.delete(contacts);
    }
}
