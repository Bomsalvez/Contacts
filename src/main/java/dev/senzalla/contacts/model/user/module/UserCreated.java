package dev.senzalla.contacts.model.user.module;

import dev.senzalla.contacts.model.permission.module.PermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserCreated {
    private Long pkUser;
    private String nameUser;
    private String mailUser;
    private Set<PermissionDto> permissions;
}
