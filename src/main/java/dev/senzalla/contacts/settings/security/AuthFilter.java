package dev.senzalla.contacts.settings.security;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.service.token.TokenService;
import dev.senzalla.contacts.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        boolean isValidToken = tokenService.checkValidToken(token);
        if (isValidToken) {
            authUser(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authUser(String token) {
        Long pkUser = tokenService.getIdUser(token);
        User user = userService.findUser(pkUser);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getPermissions());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return tokenService.extractToken(token);
    }
}
