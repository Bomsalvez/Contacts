package dev.senzalla.contacts.model.permission.module;

import dev.senzalla.contacts.model.permission.entity.EPermission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto {
    private Long pkPermission;
    private EPermission namePermission;

}
