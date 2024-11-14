package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.permission.module.PromotionAuthorityUser;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import dev.senzalla.contacts.model.user.module.UserToBeCreated;
import dev.senzalla.contacts.service.user.UserService;
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
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final String urlSuffix = "/{pkUser}";

    @PostMapping
    @Transactional
    public ResponseEntity<UserCreated> createUser(@RequestBody @Valid UserToBeCreated userToBeCreated, UriComponentsBuilder uriComponentsBuilder) {
        UserCreated newUser = userService.createUser(userToBeCreated);
        URI uri = uriComponentsBuilder.path("/user/{pkUser}").buildAndExpand(newUser.getPkUser()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping
    public ResponseEntity<UserCreated> findUserByToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(userService.findUserCreated(token));
    }



    @GetMapping(urlSuffix)
    @PreAuthorize("hasAuthority('ADMIN') or #pkUser == authentication.principal.pkUser")
    public ResponseEntity<UserCreated> findUser(@PathVariable Long pkUser) {
        return ResponseEntity.ok().body(userService.findUserCreated(pkUser));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<UserSummarize>> findListUser(
            @SortDefault(sort = "nameUser", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "nameUser", required = false) String nameUser,
            @RequestParam(value = "mailUser", required = false) String mailUser
    ) {
        return ResponseEntity.ok().body(userService.findListUser(pageable, nameUser, mailUser));
    }

    @Transactional
    @PutMapping(urlSuffix)
    @PreAuthorize("#pkUser == authentication.principal.pkUser")
    public ResponseEntity<UserCreated> editUser(@PathVariable Long pkUser, @RequestBody @Valid UserToBeCreated userToBeCreated) {
        return ResponseEntity.ok().body(userService.editUser(pkUser, userToBeCreated));
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(urlSuffix)
    public ResponseEntity<UserCreated> promotionUser(@PathVariable Long pkUser, @RequestBody PromotionAuthorityUser promotionAuthorityUser) {
        return ResponseEntity.ok().body(userService.promotionUser(pkUser, promotionAuthorityUser));
    }

    @Transactional
    @DeleteMapping(urlSuffix)
    @PreAuthorize("hasAuthority('ADMIN') or #pkUser == authentication.principal.pkUser")
    public ResponseEntity<?> deleteUser(@PathVariable Long pkUser) {
        userService.deleteUser(pkUser);
        return ResponseEntity.noContent().build();
    }
}

