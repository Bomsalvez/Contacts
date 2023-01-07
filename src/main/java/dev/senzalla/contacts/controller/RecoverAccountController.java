package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.user.module.RecoverAccount;
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
    public ResponseEntity<String> orderRecoverAccount(@PathVariable String mailUser) {
        recoverAccountService.orderRecoverAccount(mailUser);
        return ResponseEntity.ok().body("Email Enviado");
    }

    @PostMapping
    public ResponseEntity<UserCreated> changePassword(@RequestBody @Valid RecoverAccount recoverAccount) {
        return ResponseEntity.ok().body(recoverAccountService.changePassword(recoverAccount));
    }
}
