package dev.senzalla.contacts.model.phonenumber.module;

import dev.senzalla.contacts.model.phonenumber.entity.TagPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberDto {
    private Long pkPhoneNumber;
    @NotBlank
    @Pattern(regexp = "[0-9]{11,15}")
    private String phoneNumber;
    private TagPhone tagPhone;
}