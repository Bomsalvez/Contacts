package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByMailUser(String mail);

    @Query("select u from User u left join fetch u.permissions where u.pkUser = :pkUser")
    Optional<User> findById(Long pkUser);

    @Query("select u from User u " +
            "where (:nameUser is null or lower(u.nameUser) like lower(concat('%',:nameUser,'%')))" +
            "and (:mailUser is null or lower(u.mailUser) like lower(concat('%',:mailUser,'%')))")
    Page<User> findByNameUserAndMailUser(Pageable pageable, @Param("nameUser") String nameUser, @Param("mailUser") String mailUser);
}