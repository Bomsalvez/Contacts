package dev.senzalla.contacts.controller;

import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import dev.senzalla.contacts.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserCreated> createUser(@RequestBody @Valid UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {
        UserCreated newUser = userService.createUser(userDto);
        URI uri = uriComponentsBuilder.path("/user/{pkUser}").buildAndExpand(newUser.getPkUser()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping("/{pkUser}")
    public ResponseEntity<UserCreated> findUser(@PathVariable Long pkUser) {
        return ResponseEntity.ok().body(userService.findUserCreated(pkUser));
    }

    @GetMapping
    public ResponseEntity<Page<UserSummarize>> findListUser(
            Pageable pageable,
            @RequestParam(value = "nameUser", required = false) String nameUser,
            @RequestParam(value = "mailUser", required = false) String mailUser
    ) {
        return ResponseEntity.ok().body(userService.findListUser(pageable, nameUser, mailUser));
    }
}

