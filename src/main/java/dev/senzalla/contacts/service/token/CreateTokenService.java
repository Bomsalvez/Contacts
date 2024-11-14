package dev.senzalla.contacts.service.token;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateTokenService {
    private final UserService userService;
    @Value("${server.servlet.application-display-name}")
    private String nameApp;
    @Value("${jwt.api.secret}")
    private String authKey;
    @Value("${jwt.api.expiration}")
    private Long timeToExpiry;

    @Autowired
    public CreateTokenService(UserService userService) {
        this.userService = userService;
    }

    public String createToken(UsernamePasswordAuthenticationToken authenticationToken) {
        final Date today = new Date();
        final Date timeExpiry = new Date(today.getTime() + timeToExpiry);
        User user = userService.findUserByMail(authenticationToken.getPrincipal().toString());

        return Jwts.builder().setIssuer(nameApp)
                .setSubject(user.getPkUser().toString())
                .setIssuedAt(today)
                .setExpiration(timeExpiry)
                .signWith(SignatureAlgorithm.HS512, authKey)
                .compact();
    }
}
