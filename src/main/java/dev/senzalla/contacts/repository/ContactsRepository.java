package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.contact.entity.Contacts;
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
    Optional<Contacts> findByNameContactIgnoreCaseAndNicknameContactIgnoreCaseAndDateBirthContact(String nameContact, String nicknameContact, LocalDate dateBirthContact);

    @Query("select c from Contacts c left join c.phoneNumbers p left join c.mails m left join c.addresses a where c.pkContact = :pkContact")
    Optional<Contacts> findByPkContact(Long pkContact);

    @Query("select c from Contacts c " +
            "where (:nameContact is null or lower(c.nameContact) like lower(concat('%',:nameContact,'%')))" +
            "and c.phoneNumbers is not empty")
    Page<Contacts> findByNameContact(Pageable pageable, @Param("nameContact") String nameContact);
}