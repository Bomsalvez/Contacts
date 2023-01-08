package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.entity.Permission;
import dev.senzalla.contacts.model.recoveraccount.module.ResettingPassword;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserToBeCreated;
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
class CreateUserService {
    private final PasswordEncoder passwordEncoder;
    private final PermissionService permissionService;
    private final UserRepository userRepository;

    public UserCreated createUser(UserToBeCreated userToBeCreated) {
        try {
            User user = UserMapper.toUser(userToBeCreated);
            definePermissions(user);
            encodePassword(user);
            user = userRepository.save(user);
            return UserMapper.toUserCreated(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }

    public UserCreated editUser(Long pkUser, UserToBeCreated userToBeCreated) {
        try {
            User user = userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
            encodePassword(user);
            user.setNameUser(userToBeCreated.getNameUser());
            user.setMailUser(userToBeCreated.getMailUser());
            user = userRepository.save(user);
            return UserMapper.toUserCreated(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }

    public UserCreated changePassword(ResettingPassword resettingPassword) {
        User user = userRepository.findUserByMailUser(resettingPassword.mail()).orElseThrow(() -> new NotFoundException("User Not Found"));
        user.setPasswordUser(resettingPassword.password());
        encodePassword(user);
        user = userRepository.save(user);
        return UserMapper.toUserCreated(user);
    }

    private void encodePassword(User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPasswordUser(pass);
    }

    private void definePermissions(User user) {
        Set<Permission> permissions = Set.of(permissionService.findPermission());
        user.setPermissions(permissions);
    }

}
