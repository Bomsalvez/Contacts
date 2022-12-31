package dev.senzalla.contacts.service.auth;

import dev.senzalla.contacts.model.auth.Login;
import dev.senzalla.contacts.model.auth.Token;
import dev.senzalla.contacts.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public Token loginUser(Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.mail(), login.password());
        authenticationManager.authenticate(authenticationToken);
        String token = tokenService.createToken(authenticationToken);
        return new Token(token);
    }
}
