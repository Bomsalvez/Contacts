package dev.senzalla.contacts.model.phonenumber.entity;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Phonenumber {
    @Id
    @Column(name = "pkPhoneNumber", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkPhoneNumber;

    @NotBlank
    @Column(name = "phoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "tagPhone")
    private TagPhone tagPhone;

    @ManyToOne
    @JoinColumn(name = "fkContact", referencedColumnName = "pkContact", nullable = false)
    private Contacts contacts;
}
