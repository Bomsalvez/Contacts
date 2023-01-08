package dev.senzalla.contacts.model.recoveraccount.entity;

import dev.senzalla.contacts.model.mail.TemplateMail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "recover_account", schema = "db_contacts")
public class RecoverAccount implements TemplateMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkRecoverAccount", nullable = false)
    private Long pkRecoverAccount;
    @Basic
    @Column(name = "nameUser", nullable = false, length = 255)
    private String nameUser;
    @Basic
    @Column(name = "mailUser", nullable = false, length = 255)
    private String mailUser;
    @Basic
    @Column(name = "hashSecurity", nullable = false, length = 32)
    private String hashSecurity;
    @Basic
    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;
}
