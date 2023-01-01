package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByMailUser(String mail);

    @Query("select u from User u left join fetch u.permissions where u.pkUser = :pkUser")
    Optional<User> findById(Long pkUser);
}