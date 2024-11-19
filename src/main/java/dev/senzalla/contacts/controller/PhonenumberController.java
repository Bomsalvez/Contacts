package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import dev.senzalla.contacts.service.phonenumber.PhonenumberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<ContactsCreated> addPhonenumberToContact(@PathVariable Long pkContact, @RequestBody @Valid Set<PhoneNumberDto> phoneNumberDtos) {
        return ResponseEntity.ok().body(phonenumberService.addPhonenumberToContact(pkContact, phoneNumberDtos));
    }

    @Transactional
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CREATE')")
    public ResponseEntity<?> deletePhone(@PathVariable Long pkContact, @RequestParam Long pkPhonenumber) {
        phonenumberService.deletePhoneToContact(pkContact, pkPhonenumber);
        return ResponseEntity.noContent().build();
    }
}
