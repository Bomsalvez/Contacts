package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonenumberRepository extends JpaRepository<Phonenumber, Long> {
}