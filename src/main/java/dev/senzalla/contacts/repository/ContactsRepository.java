package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    Optional<Contacts> findByNameContactIgnoreCaseAndNicknameContactIgnoreCaseAndDateBirthContact(String nameContact, String nicknameContact, LocalDate dateBirthContact);
}