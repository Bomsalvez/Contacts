package dev.senzalla.contacts.model.phonenumber.module;

import dev.senzalla.contacts.model.phonenumber.entity.ETagPhone;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberDto {
    private Long pkPhoneNumber;
    @NotBlank
    private String phoneNumber;
    private ETagPhone tagPhone;

}
