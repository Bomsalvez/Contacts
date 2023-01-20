package dev.senzalla.contacts.model.contact.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
}
