package dev.senzalla.contacts.settings.security;

import dev.senzalla.contacts.service.token.TokenService;
import dev.senzalla.contacts.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurity {

    private final UserService userService;
    private final TokenService tokenService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(this::configureCors);
        http.csrf(AbstractHttpConfigurer::disable);

        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

//        http.authorizeHttpRequests().anyRequest().permitAll();
        http.authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.POST, "/user").permitAll()
                .requestMatchers(HttpMethod.GET, "/contact").permitAll()
                .requestMatchers("/account/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(
                        new AuthFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    private void configureCors(CorsConfigurer<HttpSecurity> c) {
        c.configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("/**", "http://localhost:4200")); // Adicione as origens permitidas aqui
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setAllowCredentials(true);
            return cors;
        });
    }
}
