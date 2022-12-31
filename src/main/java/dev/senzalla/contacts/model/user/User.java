package dev.senzalla.contacts.model.user;

import dev.senzalla.contacts.model.permission.Permission;
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
public class User implements UserDetails {
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

    @Basic
    @Column(name = "passwordUser", nullable = false, length = 255)
    private String passwordUser;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "loginOrigin", nullable = false, length = 255)
    private ELoginOrigin loginOrigin;

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
