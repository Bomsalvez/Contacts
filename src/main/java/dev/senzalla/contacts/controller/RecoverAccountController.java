package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.recoveraccount.module.ResettingPassword;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.service.recoveraccount.RecoverAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecoverAccountController {
    private final RecoverAccountService recoverAccountService;

    @GetMapping("/{mailUser}")
    public ResponseEntity<?> orderRecoverAccount(@PathVariable String mailUser) {
        recoverAccountService.orderRecoverAccount(mailUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{hash}")
    public ResponseEntity<UserCreated> changePassword(@PathVariable String hash, @RequestBody @Valid ResettingPassword resettingPassword) {
        return ResponseEntity.ok().body(recoverAccountService.changePassword(hash, resettingPassword));
    }
}
