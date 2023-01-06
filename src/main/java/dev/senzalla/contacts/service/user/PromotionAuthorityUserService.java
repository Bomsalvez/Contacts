package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.entity.Permission;
import dev.senzalla.contacts.model.permission.module.PromotionAuthorityUser;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.service.permission.PermissionService;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PromotionAuthorityUserService {
    private final PermissionService permissionService;
    private final UserRepository userRepository;

    public UserCreated promotionAuthorityUser(Long pkUser, PromotionAuthorityUser promotionAuthorityUser) {
        User user = userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
        definePermissions(user, promotionAuthorityUser);
        userRepository.save(user);
        return UserMapper.toUserCreated(user);
    }

    private void definePermissions(User user, PromotionAuthorityUser promotionAuthorityUser) {
        Set<Permission> permissions = user.getPermissions();
        Permission permission = permissionService.findPermission(promotionAuthorityUser);
        permissions.add(permission);
        user.setPermissions(permissions);
    }
}
