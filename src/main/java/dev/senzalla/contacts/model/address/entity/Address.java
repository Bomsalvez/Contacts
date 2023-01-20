package dev.senzalla.contacts.model.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @Column(name = "pkAddress", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkAddress;

    @Pattern(regexp = "[0-9]{8,12}")
    @Column(name = "codeAddress", nullable = false, length = 12)
    private String codeAddress;

    @NotBlank
    @Column(name = "streetAddress", nullable = false)
    private String streetAddress;

    @NotBlank
    @Column(name = "districtAddress", nullable = false)
    private String districtAddress;

    @NotBlank
    @Column(name = "cityAddress", nullable = false)
    private String cityAddress;

    @NotBlank
    @Column(name = "countryAddress", nullable = false)
    private String countryAddress;

    @Basic
    @Column(name = "isWorkAddress", nullable = false)
    private boolean isWorkAddress;
}
