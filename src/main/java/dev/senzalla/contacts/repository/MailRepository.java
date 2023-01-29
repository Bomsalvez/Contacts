package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    @Transactional
    @Modifying
    @Query("delete from Mail m  where m.contacts.pkContact = :pkContact and m.pkMail = :pkMail")
    void deleteMailToContact(Long pkContact, Long pkMail);
}