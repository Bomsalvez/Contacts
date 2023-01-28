package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import dev.senzalla.contacts.service.phonenumber.PhonenumberService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/phone/{pkContact}")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhonenumberController {
    private final PhonenumberService phonenumberService;

    @Transactional
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<ContactsCreated> addPhonenumberToContact(@PathVariable Long pkContact, @RequestBody @Valid Set<PhonenumberDto> phonenumberDtos) {
        return ResponseEntity.ok().body(phonenumberService.addPhonenumberToContact(pkContact, phonenumberDtos));
    }

    @Transactional
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<?> deletePhone(@PathVariable Long pkContact, @RequestParam Long pkPhonenumber) {
        phonenumberService.deletePhoneToContact(pkContact, pkPhonenumber);
        return ResponseEntity.noContent().build();
    }
}
