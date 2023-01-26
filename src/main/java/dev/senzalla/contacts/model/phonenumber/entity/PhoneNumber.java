package dev.senzalla.contacts.model.phonenumber.entity;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhoneNumber {
    @Id
    @Column(name = "pkPhoneNumber", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkPhoneNumber;
    @NotBlank
    @Column(name = "phoneNumber", nullable = false, length = 15)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "tagPhone", nullable = true, length = 255)
    private ETagPhone tagPhone;
    @ManyToOne
    @JoinColumn(name = "fkContact", referencedColumnName = "pkContact", nullable = false)
    private Contacts contacts;

}
