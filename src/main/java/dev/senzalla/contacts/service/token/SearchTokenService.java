package dev.senzalla.contacts.service.token;

import dev.senzalla.contacts.settings.exception.FailureAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchTokenService {
    @Value("${jwt.api.secret}")
    private String authKey;

    public boolean checkValidToken(String token) {
        if (token != null) {
            Jwts.parser().setSigningKey(authKey).parseClaimsJws(token);
            return true;
        }
        return false;
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

    public void checkUserAuthorization(Long pkUser, String token) {
        Long pkUserAuth = getIdUser(token.substring(7));
        if (!pkUserAuth.equals(pkUser)) {
            throw new FailureAuthenticationException();
        }
    }
}
