package dev.senzalla.contacts.model.phonenumber.module;

import dev.senzalla.contacts.model.phonenumber.entity.TagPhone;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhonenumberDto {
    private Long pkPhoneNumber;
    @NotBlank
    private String phoneNumber;
    private TagPhone tagPhone;
}