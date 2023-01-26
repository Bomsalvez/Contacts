package dev.senzalla.contacts.service.mail;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.model.mail.mapper.MailMapper;
import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.repository.MailRepository;
import dev.senzalla.contacts.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SaveMailService {
    private final MailRepository mailRepository;

    public void addMailToContact(Set<MailDto> mailsDto, Contacts contacts) {
        if (mailsDto != null) {
            mailsDto.stream().map(MailMapper::toMail).forEach(mail -> addMail(mail, contacts));
        }
    }

    private void addMail(Mail mail, Contacts contacts) {
        try {
            mail.setContacts(contacts);
            mailRepository.save(mail);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }
}
