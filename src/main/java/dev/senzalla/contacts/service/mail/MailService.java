package dev.senzalla.contacts.service.mail;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.model.recoveraccount.entity.RecoverAccount;
import dev.senzalla.contacts.model.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailService {
    private final SendMailService sendMailService;
    private final SaveMailService saveMailService;
    private final DeleteMail deleteMail;

    public void sendMailToCreateAccount(User user) {
        sendMailService.sendMailToCreateAccount(user);
    }

    public void sendMailToRecoverAccount(RecoverAccount account) {
        sendMailService.sendMailToRecoverAccount(account);
    }

    public void addMailToContact(Set<MailDto> mails, Contacts contacts) {
        saveMailService.addMailToContact(mails, contacts);
    }

    public void deleteMail(Set<Mail> mails) {
        deleteMail.delete(mails);
    }

    public ContactsCreated addMailToContact(Long pkContact, Set<MailDto> mailDto) {
        return saveMailService.addMailToContact(pkContact, mailDto);
    }

    public void deleteMailToContact(Long pkContact, Long pkMail) {
        deleteMail.delete(pkContact, pkMail);
    }
}
