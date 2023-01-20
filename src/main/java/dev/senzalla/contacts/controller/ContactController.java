package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.service.contact.ContactService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactController {
    private final ContactService contactService;
    private final String urlSuffix = "/{pkContact}";

    @PostMapping
    @Transactional
    public ResponseEntity<ContactsDto> addContact(@RequestBody @Valid ContactsDto contactsDto, UriComponentsBuilder builder) {
        ContactsDto dto = contactService.addContact(contactsDto);
        URI uri = builder.path("/contact/{pkContact}").buildAndExpand(dto.getPkContact()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
