package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    long deleteByContacts(Contacts contacts);
}