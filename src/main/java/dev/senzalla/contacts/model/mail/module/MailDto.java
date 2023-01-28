package dev.senzalla.contacts.model.mail.module;

import dev.senzalla.contacts.model.mail.entity.TagMail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the {@link dev.senzalla.contacts.model.mail.entity.Mail} entity
 */
@Getter
@Setter
public class MailDto {
    @NotBlank
    private String mail;
    private TagMail tagMail;
}