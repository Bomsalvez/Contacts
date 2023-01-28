package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.recoveraccount.entity.RecoverAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoverAccountRepository extends JpaRepository<RecoverAccount, Long> {

    Optional<RecoverAccount> findByHashSecurityAndMailUser(String hash,String mailUser);
}