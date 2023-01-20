package dev.senzalla.contacts.model.user.entity;

import dev.senzalla.contacts.model.mail.template.TemplateMail;
import dev.senzalla.contacts.model.permission.entity.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
public class User implements UserDetails, TemplateMail {
    @Id
    @Column(name = "pkUser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkUser;

    @NotBlank
    @Column(name = "nameUser", nullable = false)
    private String nameUser;

    @Email
    @Column(name = "mailUser", nullable = false)
    private String mailUser;

    @NotBlank
    @Column(name = "passwordUser", nullable = false)
    private String passwordUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "fkUser"),
            inverseJoinColumns = @JoinColumn(name = "fkPermission"))
    private Set<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return nameUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
