package dev.senzalla.contacts.model.mail.entity;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mail")
public class Mail {
    @Id
    @Column(name = "pkMail", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkMail;

    @NotBlank
    @Column(name = "mail", nullable = false)
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "tagMail")
    private TagMail tagMail;

    @ManyToOne
    @JoinColumn(name = "fkContact", referencedColumnName = "pkContact", nullable = false)
    private Contacts contacts;
}
