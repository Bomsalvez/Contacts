package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contacts.module.ContactList;
import dev.senzalla.contacts.model.contacts.module.ContactsCreated;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import dev.senzalla.contacts.service.contacts.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<ContactsCreated> createUser(@RequestBody @Valid ContactsCreating contactsCreating, UriComponentsBuilder uriComponentsBuilder) {
        ContactsCreated contactsCreated = contactService.addContact(contactsCreating);
        URI uri = uriComponentsBuilder.path("/contact/{pkContact}").buildAndExpand(contactsCreated.getPkContact()).toUri();
        return ResponseEntity.created(uri).body(contactsCreated);
    }

    @GetMapping("/{pkContact}")
    public ResponseEntity<ContactsCreated> findContact(@PathVariable Long pkContact) {
        return ResponseEntity.ok().body(contactService.findContact(pkContact));
    }

    @GetMapping
    public ResponseEntity<Page<ContactList>> findMultipleContact(
            @SortDefault(sort = "nameContact", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "nameContact", required = false) String nameContact,
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok().body(contactService.findMultipleContact(pageable, nameContact, token));
    }
}
