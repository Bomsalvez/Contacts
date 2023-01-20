package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contact.module.ContactList;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.service.contact.ContactService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<ContactsDto> addContact(@RequestBody @Valid ContactsDto contactsDto, UriComponentsBuilder builder) {
        ContactsDto dto = contactService.addContact(contactsDto);
        URI uri = builder.path("/contact/{pkContact}").buildAndExpand(dto.getPkContact()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(urlSuffix)
    public ResponseEntity<ContactsDto> findContact(@PathVariable Long pkContact) {
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
