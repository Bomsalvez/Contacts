package dev.senzalla.contacts.model.contact.entity;

import dev.senzalla.contacts.model.address.entity.Address;
import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Contacts {
    @Id
    @Column(name = "pkContact", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkContact;

    @NotBlank
    @Column(name = "nameContact", nullable = false)
    private String nameContact;

    @Past
    @Column(name = "dateBirthContact")
    private LocalDate dateBirthContact;

    @Size(max = 30)
    @Column(name = "nicknameContact", length = 30)
    private String nicknameContact;

    @OneToMany(mappedBy = "contacts", cascade = CascadeType.REFRESH)
    private Set<Phonenumber> phonenumbers;

    @OneToMany(mappedBy = "contacts", cascade = CascadeType.REFRESH)
    private Set<Mail> mails;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "address_contact",
            joinColumns = @JoinColumn(name = "fkContact"),
            inverseJoinColumns = @JoinColumn(name = "fkAddress"))
    private Set<Address> addresses;
}
