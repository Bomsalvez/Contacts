package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.service.mail.MailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/mail/{pkContact}")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailController {
    private final MailService mailService;

    @Transactional
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<ContactsCreated> addMailToContact(@PathVariable Long pkContact, @RequestBody @Valid Set<MailDto> mailDto) {
        return ResponseEntity.ok().body(mailService.addMailToContact(pkContact, mailDto));
    }

    @Transactional
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<?> deletePhone(@PathVariable Long pkContact, @RequestParam Long pkMail) {
        mailService.deleteMailToContact(pkContact, pkMail);
        return ResponseEntity.noContent().build();
    }
}
