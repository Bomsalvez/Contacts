package dev.senzalla.contacts.model.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Token {
    private final String HEADER = "Bearer";
    private String token;
}
