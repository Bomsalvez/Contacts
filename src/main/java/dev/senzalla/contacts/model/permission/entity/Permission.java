package dev.senzalla.contacts.model.permission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pkPermission", nullable = false)
    private Long pkPermission;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "namePermission", nullable = false)
    private EPermission namePermission;

    @Override
    public String getAuthority() {
        return namePermission.name();
    }
}
