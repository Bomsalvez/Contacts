package dev.senzalla.contacts.service.mail;

import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeleteMail {
    private final MailRepository mailRepository;

    public void delete(Set<Mail> mails) {
        mailRepository.deleteAll(mails);
    }

    public void delete(Long pkContact, Long pkMail) {
        mailRepository.deleteMailToContact(pkContact, pkMail);
    }
}
