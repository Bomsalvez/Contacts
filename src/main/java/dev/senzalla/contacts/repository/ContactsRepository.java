package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    Optional<Contacts> findByNameContactAndDateBirthContactOrNicknameContact(String nameContact, LocalDate dateBirthContact, String nicknameContact);

    @Query("select c from Contacts c where (:nameContact is null or lower(c.nameContact) like lower(concat('%',:nameContact,'%')))")
    Page<Contacts> findByNameContact(Pageable pageable, @Param("nameContact") String nameContact);
}