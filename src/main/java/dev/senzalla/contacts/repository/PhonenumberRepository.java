package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhonenumberRepository extends JpaRepository<Phonenumber, Long> {

    @Transactional
    @Modifying
    @Query("delete from Phonenumber p  where p.contacts.pkContact = :pkContact and p.pkPhoneNumber = :pkPhoneNumber")
    void deletePhoneToContact(@NonNull Long pkContact, @NonNull Long pkPhoneNumber);
}