package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.entity.Permission;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.service.permission.PermissionService;
import dev.senzalla.contacts.service.token.SearchTokenService;
import dev.senzalla.contacts.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CreateUserService {
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SearchTokenService searchTokenService;

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

    private void encodePassword(User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPasswordUser(pass);
    }

    private void definePermissions(User user) {
        Set<Permission> permissions = Set.of(permissionService.findPermission());
        user.setPermissions(permissions);
    }

    public UserCreated editUser(Long pkUser, UserDto userDto, String token) {
        try {
            searchTokenService.checkUserAuthorization(pkUser, token);
            User user = UserMapper.toUser(userDto);
            encodePassword(user);
            user.setPkUser(pkUser);
            user = userRepository.save(user);
            return UserMapper.toUserCreated(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }
}
