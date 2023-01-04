package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.entity.Permission;
import dev.senzalla.contacts.model.permission.module.PermissionPromotion;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.service.permission.PermissionService;
import dev.senzalla.contacts.settings.exception.DuplicateException;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SaveUserService {
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserCreated createUser(UserDto userDto) {
        try {
            User user = UserMapper.toUser(userDto);
            definePermissions(user);
            encodePassword(user);
            user = userRepository.save(user);
            return UserMapper.toUserCreated(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }

    public UserCreated editUser(Long pkUser, UserDto userDto) {
        try {
            User user = findUser(pkUser);
            encodePassword(user);
            user.setNameUser(userDto.getNameUser());
            user.setMailUser(userDto.getMailUser());
            user = userRepository.save(user);
            return UserMapper.toUserCreated(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }

    public UserCreated promotionUser(Long pkUser, PermissionPromotion permissionPromotion) {
        User user = findUser(pkUser);
        definePermissions(user, permissionPromotion);
        userRepository.save(user);
        return UserMapper.toUserCreated(user);
    }


    public void deleteUser(Long pkUser) {
        User user = findUser(pkUser);
        userRepository.delete(user);
    }

    private void encodePassword(User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPasswordUser(pass);
    }

    private void definePermissions(User user) {
        Set<Permission> permissions = Set.of(permissionService.findPermission());
        user.setPermissions(permissions);
    }

    private void definePermissions(User user, PermissionPromotion permissionPromotion) {
        Set<Permission> permissions = user.getPermissions();
        Permission permission = permissionService.findPermission(permissionPromotion);
        permissions.add(permission);
        user.setPermissions(permissions);
    }

    private User findUser(Long pkUser) {
        return userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
    }
}
