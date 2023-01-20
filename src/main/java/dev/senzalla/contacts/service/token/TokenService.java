package dev.senzalla.contacts.service.token;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TokenService {
    private final CreateTokenService createTokenService;
    private final SearchTokenService searchTokenService;

    public String createToken(UsernamePasswordAuthenticationToken authenticationToken) {
        return createTokenService.createToken(authenticationToken);
    }

    public boolean checkValidToken(String token) {
        return searchTokenService.checkValidToken(token);
    }

    public Long getIdUser(String token) {
        return searchTokenService.getIdUser(token);
    }

    public String extractToken(String token) {
        return searchTokenService.extractToken(token);
    }
}
