package dev.senzalla.contacts.model.contacts.entity;

import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkContact", nullable = false)
    private Long pkContact;
    @NotBlank
    @Column(name = "nameContact", nullable = false)
    private String nameContact;
    @Past
    @Column(name = "dateBirthContact")
    private LocalDate dateBirthContact;
    @Basic
    @Column(name = "nicknameContact", nullable = true, length = 30)
    private String nicknameContact;
    @OneToMany(mappedBy = "contacts", fetch = FetchType.EAGER)
    private Set<PhoneNumber> phonenumbers;
//    @OneToMany(mappedBy = "contactsByFkContact")
//    private Collection<AddressContact> addressContacts;
//    @OneToMany(mappedBy = "contactsByFkContact")
//    private Collection<Mail> mailByPkContact;

}
