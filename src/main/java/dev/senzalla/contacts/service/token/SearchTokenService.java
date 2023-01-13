package dev.senzalla.contacts.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class SearchTokenService {
    @Value("${jwt.api.secret}")
    private String authKey;

    public boolean checkValidToken(String token) {
        if (token != null ) {
            Jwts.parser().setSigningKey(authKey).parseClaimsJws(token);
            return true;
        }
        return false;
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

    public String extractToken(String token) {
        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }
        return null;
    }
}
