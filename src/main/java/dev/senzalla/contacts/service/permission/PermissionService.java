package dev.senzalla.contacts.service.permission;

import dev.senzalla.contacts.model.permission.entity.EPermission;
import dev.senzalla.contacts.model.permission.entity.Permission;
import dev.senzalla.contacts.model.permission.module.PromotionAuthorityUser;
import dev.senzalla.contacts.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission findPermission() {
        return permissionRepository.findByNamePermission(EPermission.READ);
    }

    public Permission findPermission(PromotionAuthorityUser promotionAuthorityUser) {
        return permissionRepository.findByNamePermission(promotionAuthorityUser.getNamePermission());
    }
}
