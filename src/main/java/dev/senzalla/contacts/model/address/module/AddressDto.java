package dev.senzalla.contacts.model.address.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the {@link dev.senzalla.contacts.model.address.entity.Address} entity
 */
@Getter
@Setter
public class AddressDto {
    private Long pkAddress;
    @Pattern(regexp = "[0-9]{8,12}")
    private String codeAddress;
    @NotBlank
    private String streetAddress;
    @NotBlank
    private String districtAddress;
    @NotBlank
    private String cityAddress;
    @NotBlank
    private String countryAddress;
    private boolean isWorkAddress;
}